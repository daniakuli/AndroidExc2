package com.example.androidexc2.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }
    private Model(){
        for(int i=0;i<20;i++){
            addStudent(new Student("name " + i,""+i,"", "", "", false));
        }
    }

    List<Student> data = new LinkedList<>();
    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void updateStudent(Student sGet, Student sExist) {
        for(int pos = 0; pos < data.size(); pos++){
            Student s = data.get(pos);
            if(checkEqual(sExist,s)){
                Log.d("Test", "Check: " + pos );
                data.set(pos, sGet);
                break;
            }
        }
    }

    public void removeStudent(Student st){
        for(Student s : data){
            if(checkEqual(st, s)){
                data.remove(s);
                break;
            }
        }
    }

    public boolean checkEqual(Student sGet, Student sExist){
        return sGet.name.equals(sExist.name) &&
                sGet.id.equals(sExist.id) &&
                sGet.phone.equals(sExist.phone) &&
                sGet.address.equals(sExist.address) &&
                sGet.cb.booleanValue() == sExist.cb.booleanValue();
    }
}
