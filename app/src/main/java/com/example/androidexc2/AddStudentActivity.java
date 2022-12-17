package com.example.androidexc2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexc2.model.Model;
import com.example.androidexc2.model.Student;


public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Model m = Model.instance();

        EditText nameEt = findViewById(R.id.addstudent_name_et);
        EditText idEt = findViewById(R.id.addstudent_id_et);
        EditText phone = findViewById(R.id.addstudent_phone_et);
        EditText address = findViewById(R.id.addstudent_address_et);
        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = findViewById(R.id.addstudent_cancell_btn);
        CheckBox cb = findViewById(R.id.addstudent_cb);

        saveBtn.setOnClickListener(view -> {
            Student s = new Student(nameEt.getText().toString(),
                            idEt.getText().toString(),
                            "",
                            phone.getText().toString(),
                            address.getText().toString(),
                            cb.isChecked());
            String name = nameEt.getText().toString();

            m.addStudent(s);
            for (Student student:
                 m.getAllStudents()) {
                Log.d("Student",student.id);
            }
            Intent intent = new Intent(AddStudentActivity.this,StudentRecyclerList.class);
            startActivity(intent);
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}