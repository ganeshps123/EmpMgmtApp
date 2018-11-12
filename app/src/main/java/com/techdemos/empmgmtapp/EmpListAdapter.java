package com.techdemos.empmgmtapp;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.techdemos.empmgmtapp.db.Employee;
import com.techdemos.empmgmtapp.ui.HomeScreenActivity;
import com.techdemos.empmgmtapp.ui.MapsActivity;

import java.util.HashMap;
import java.util.List;

public class EmpListAdapter extends RecyclerView.Adapter<EmpListAdapter.ViewHolder>{
    List<Employee> empList;
    Context context;
    HashMap<View.OnClickListener, Integer> hashmap=new HashMap<>();

    public EmpListAdapter(Context context){
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView empId;
        public TextView empName;
        public TextView empDept;
        public ImageButton locBtn;

        public ViewHolder(@NonNull View itemView, final int i) {
            super(itemView);
            layout=itemView;
            empId=(TextView)itemView.findViewById(R.id.empid);
            empName=(TextView)itemView.findViewById(R.id.empName);
            empDept=(TextView)itemView.findViewById(R.id.empDept);
            locBtn=(ImageButton)itemView.findViewById(R.id.map);
            Log.d("EmpListAdapter", "i1 is "+i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View v=inflater.inflate(R.layout.emp_list_item, viewGroup, false);
        return new ViewHolder(v,i);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (empList!=null){
            Employee emp=empList.get(i);
            viewHolder.empId.setText(""+emp.getId());
            viewHolder.empName.setText(emp.getName());
            viewHolder.empDept.setText(emp.getDepartment());
            View.OnClickListener listener= new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MapsActivity.class);

                    Log.d("EmpListAdapter", "i2 is "+i);
                    intent.putExtra("latitude", empList.get(i).getLatitude());
                    intent.putExtra("longitude", empList.get(i).getLongitude());
                    context.startActivity(intent);
                }
            };
            viewHolder.locBtn.setOnClickListener(listener);
//            hashmap.put(listener,i);
        }
    }

    @Override
    public int getItemCount() {
        if (empList==null)
            return 0;
        return empList.size();
    }

    public void setEmployeesData(List<Employee> employees){
        this.empList=employees;
        if (empList!=null){
            notifyDataSetChanged();
        }
    }
}
