package com.example.androidexc2;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidexc2.databinding.ActivityEditStudentBinding;
import com.example.androidexc2.model.Model;
import com.example.androidexc2.model.Student;

public class StudentEdit extends Fragment {

    private ActivityEditStudentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ActivityEditStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        Student s = getArguments().getParcelable("Student");

        Model m = Model.instance();

        EditText nameEt = getView().findViewById(R.id.editstudent_name_et);
        EditText idEt = getView().findViewById(R.id.editstudent_id_et);
        EditText phone = getView().findViewById(R.id.editstudent_phone_et);
        EditText address = getView().findViewById(R.id.editstudent_address_et);
        Button saveBtn = getView().findViewById(R.id.editstudent_save_btn);
        Button deleteBtn = getView().findViewById(R.id.editstudent_delete_btn);
        Button cancelBtn = getView().findViewById(R.id.editstudent_cancell_btn);
        CheckBox cb = getView().findViewById(R.id.editstudent_cb);

        nameEt.setText(s.name);
        idEt.setText(s.id);
        phone.setText(s.phone);
        address.setText(s.address);
        cb.setChecked(s.cb);

        saveBtn.setOnClickListener(view1 ->{
            Student st = new Student(nameEt.getText().toString(),
                                     idEt.getText().toString(),
                             "", phone.getText().toString(),
                                     address.getText().toString(),
                                     cb.isChecked());
            m.updateStudent(st,s);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Student", s);
            NavHostFragment.findNavController(StudentEdit.this)
                    .navigate(R.id.action_StudentEdit_to_StudentList, bundle);
        });

        deleteBtn.setOnClickListener(view1 -> {
            m.removeStudent(s);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Student", s);
            NavHostFragment.findNavController(StudentEdit.this)
                    .navigate(R.id.action_StudentEdit_to_StudentList, bundle);
        });

        cancelBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
