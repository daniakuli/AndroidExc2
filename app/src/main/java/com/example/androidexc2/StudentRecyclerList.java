package com.example.androidexc2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexc2.databinding.ActivityStudentRecyclerListBinding;
import com.example.androidexc2.model.Model;
import com.example.androidexc2.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentRecyclerList extends Fragment {
    List<Student> data;
    private ActivityStudentRecyclerListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = ActivityStudentRecyclerListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data = Model.instance().getAllStudents();
        RecyclerView list = view.findViewById(R.id.studentrecycler_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", data.get(pos));
                NavHostFragment.findNavController(StudentRecyclerList.this)
                        .navigate(R.id.action_StudentList_to_StudentShow, bundle);

            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(StudentRecyclerList.this)
                        .navigate(R.id.action_StudentList_to_AddStudent);
            }
        });
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int)cb.getTag();
                    Student st = data.get(pos);
                    st.cb = cb.isChecked();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            return new StudentViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st,position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}