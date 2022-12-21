package com.example.androidexc2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidexc2.databinding.ActivityShowStudentBinding;
import com.example.androidexc2.model.Student;

public class StudentShow extends Fragment {

    private ActivityShowStudentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = ActivityShowStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Student s = getArguments().getParcelable("Student");

        TextView name = getView().findViewById(R.id.showstudent_name);
        TextView id = getView().findViewById(R.id.showstudent_id);
        TextView phone = getView().findViewById(R.id.showstudent_phone);
        TextView address = getView().findViewById(R.id.showstudent_address);
        CheckBox cb = getView().findViewById(R.id.showstudent_cb);
        Button edit = getView().findViewById(R.id.showstudent_edit_btn);

        name.setText(s.name);
        id.setText(s.id);
        phone.setText(s.phone);
        address.setText(s.address);
        cb.setChecked(s.cb);

        edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", s);
                NavHostFragment.findNavController(StudentShow.this)
                        .navigate(R.id.action_StudentShow_to_StudentEdit, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
