package com.example.androidexc2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidexc2.databinding.ActivityAddStudentBinding;
import com.example.androidexc2.model.Model;
import com.example.androidexc2.model.Student;


public class AddStudentActivity extends Fragment {

    private ActivityAddStudentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = ActivityAddStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        Model m = Model.instance();

        EditText nameEt = getView().findViewById(R.id.addstudent_name_et);
        EditText idEt = getView().findViewById(R.id.addstudent_id_et);
        EditText phone = getView().findViewById(R.id.addstudent_phone_et);
        EditText address = getView().findViewById(R.id.addstudent_address_et);
        Button saveBtn = getView().findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = getView().findViewById(R.id.addstudent_cancell_btn);
        CheckBox cb = getView().findViewById(R.id.addstudent_cb);

        saveBtn.setOnClickListener(view1 -> {
            Student s = new Student(nameEt.getText().toString(),
                    idEt.getText().toString(),
                    "",
                    phone.getText().toString(),
                    address.getText().toString(),
                    cb.isChecked());

            m.addStudent(s);
            for (Student student:
                    m.getAllStudents()) {
                Log.d("Student",student.id);
            }
            NavHostFragment.findNavController(AddStudentActivity.this)
                    .navigate(R.id.action_AddStudent_to_StudentList);
        });

        cancelBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}