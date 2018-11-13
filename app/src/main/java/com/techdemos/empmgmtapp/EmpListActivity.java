package com.techdemos.empmgmtapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techdemos.empmgmtapp.ui.BaseAppCompatActivity;
import com.techdemos.empmgmtapp.db.Employee;
import com.techdemos.empmgmtapp.util.Resource;
import com.techdemos.empmgmtapp.vm.EmployeeViewModel;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import static com.techdemos.empmgmtapp.util.Status.ERROR;
import static com.techdemos.empmgmtapp.util.Status.LOADING;
import static com.techdemos.empmgmtapp.util.Status.SUCCESS;

public class EmpListActivity extends BaseAppCompatActivity {
    private static final String TAG=EmpListActivity.class.getSimpleName();
    @Inject ViewModelProvider.Factory viewModelFactory;
    private RecyclerView mRecyclerView;
    private EmpListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);

        getControllerComponent().inject(this);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mRecyclerView=(RecyclerView)findViewById(R.id.emplist_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new EmpListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

        employeeViewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeViewModel.class);
        Random rand = new Random();
        employeeViewModel.setEmpListApiArgs("abc"+rand.nextInt());
        employeeViewModel.getEmpList().observe(EmpListActivity.this, new Observer<Resource<List<Employee>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Employee>> listResource) {
                Log.d(TAG, "getEmpList status: "+listResource.status);
                if (LOADING == listResource.status) {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                } else {
                    mLoadingIndicator.setVisibility(View.INVISIBLE);
                    if (ERROR==listResource.status){
                        mRecyclerView.setVisibility(View.INVISIBLE);
                        mErrorMessageDisplay.setVisibility(View.VISIBLE);
                    }
                }

                if (SUCCESS == listResource.status) {
                    mErrorMessageDisplay.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
//                    List<Employee> empList=listResource.data;
//                    Employee emp=empList.get(1);
//                    Log.d(TAG, "i: "+1);
//                    Log.d(TAG, "id: "+emp.getId());
//                    Log.d(TAG, "name: "+emp.getName());
                }
                if (listResource.data!=null)
                    mAdapter.setEmployeesData(listResource.data);
            }
        });
    }
}
