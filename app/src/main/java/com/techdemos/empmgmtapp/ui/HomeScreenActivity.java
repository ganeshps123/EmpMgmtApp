package com.techdemos.empmgmtapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.techdemos.empmgmtapp.EmpListActivity;
import com.techdemos.empmgmtapp.R;

import org.w3c.dom.Text;

public class HomeScreenActivity extends AppCompatActivity {

    Button btnEmpList, btnAddEmp, btnEditEmp, btnDelEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnEmpList=(Button)findViewById(R.id.button);
        btnAddEmp=(Button)findViewById(R.id.button2);
        btnEditEmp=(Button)findViewById(R.id.button3);
        btnDelEmp=(Button)findViewById(R.id.button4);

        btnEmpList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreenActivity.this, EmpListActivity.class);
                startActivity(intent);
            }
        });

        btnAddEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreenActivity.this, AddEmpActivity.class);
                startActivity(intent);
            }
        });

        btnEditEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreenActivity.this, EditEmpActivity.class);
                startActivity(intent);
            }
        });

        btnDelEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreenActivity.this, DeleteEmpActivity.class);
                startActivity(intent);
            }
        });
    }
}
