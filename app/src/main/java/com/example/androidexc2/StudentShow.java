package com.example.androidexc2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexc2.model.Student;

public class StudentShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        Student s = getIntent().getParcelableExtra("Student");


        TextView name = findViewById(R.id.showstudent_name);
        TextView id = findViewById(R.id.showstudent_id);
        TextView phone = findViewById(R.id.showstudent_phone);
        TextView address = findViewById(R.id.showstudent_address);
        CheckBox cb = findViewById(R.id.showstudent_cb);
        Button edit = findViewById(R.id.showstudent_edit_btn);

        name.setText(s.name);
        id.setText(s.id);
        phone.setText(s.phone);
        address.setText(s.address);
        cb.setChecked(s.cb);

        edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentShow.this,StudentEdit.class);
                intent.putExtra("Student", s);
                startActivity(intent);
            }
        });
    }
}
