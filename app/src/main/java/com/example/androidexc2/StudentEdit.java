package com.example.androidexc2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexc2.model.Model;
import com.example.androidexc2.model.Student;

public class StudentEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Student s = getIntent().getParcelableExtra("Student");

        Model m = Model.instance();

        EditText nameEt = findViewById(R.id.editstudent_name_et);
        EditText idEt = findViewById(R.id.editstudent_id_et);
        EditText phone = findViewById(R.id.editstudent_phone_et);
        EditText address = findViewById(R.id.editstudent_address_et);
        Button saveBtn = findViewById(R.id.editstudent_save_btn);
        Button deleteBtn = findViewById(R.id.editstudent_delete_btn);
        Button cancelBtn = findViewById(R.id.editstudent_cancell_btn);
        CheckBox cb = findViewById(R.id.editstudent_cb);

        nameEt.setText(s.name);
        idEt.setText(s.id);
        phone.setText(s.phone);
        address.setText(s.address);
        cb.setChecked(s.cb);

        saveBtn.setOnClickListener(view ->{
            Student st = new Student(nameEt.getText().toString(),
                                     idEt.getText().toString(),
                             "", phone.getText().toString(),
                                     address.getText().toString(),
                                     cb.isChecked());
            m.updateStudent(st,s.id);
            Intent intent = new Intent(StudentEdit.this,StudentRecyclerList.class);
            startActivity(intent);
        });

        deleteBtn.setOnClickListener(view -> {
            m.removeStudent(s);
            Intent intent = new Intent(StudentEdit.this,StudentRecyclerList.class);
            startActivity(intent);
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}
