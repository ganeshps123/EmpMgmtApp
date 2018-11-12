package com.techdemos.empmgmtapp.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.techdemos.empmgmtapp.api.ApiResponse;
import com.techdemos.empmgmtapp.util.Objects;
import com.techdemos.empmgmtapp.util.Resource;


public abstract class FetchNetworkResource<T> {
//    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    FetchNetworkResource() {
//    FetchNetworkResource(AppExecutors appExecutors) {
//        this.appExecutors = appExecutors;
        result.setValue(Resource.loading((T)null));
        final LiveData<ApiResponse<T>> apiResponse = createCall();

        result.addSource(apiResponse, new Observer<ApiResponse<T>>() {
            @Override
            public void onChanged(final @Nullable ApiResponse<T> response) {
                result.removeSource(apiResponse);

                if (null!=response){
                    if (response.isSuccessful()) {
                        saveCallResult(processResponse(response));
                        setValue(Resource.success(processResponse(response)));
                    } else {
                        setValue(Resource.error(response.errorMessage, (T)null));
                    }
                } else {
                    setValue(Resource.error("Internal error", (T)null));
                }
            }
        });
    }

    @MainThread
    private void setValue(Resource<T> newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    @MainThread
    public LiveData<Resource<T>> asLiveData() {
        return result;
    }

    @MainThread
    private T processResponse(ApiResponse<T> response) {
        return response.body;
    }

    protected abstract void saveCallResult(@NonNull T item);

    @NonNull
    @MainThread
    abstract LiveData<ApiResponse<T>> createCall();
}
