package com.techdemos.empmgmtapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.techdemos.empmgmtapp.R;

public class AddEmpActivity extends AppCompatActivity {
    public EditText name;
    public EditText dept;
    public EditText salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);

        name=(EditText)findViewById(R.id.empName);
        dept=(EditText)findViewById(R.id.empDept);
        salary=(EditText)findViewById(R.id.empSalary);
    }
}
