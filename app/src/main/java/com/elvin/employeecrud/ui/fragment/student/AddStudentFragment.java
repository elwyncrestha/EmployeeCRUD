package com.elvin.employeecrud.ui.fragment.student;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elvin.employeecrud.R;
import com.elvin.employeecrud.core.InAppDataHolder;
import com.elvin.employeecrud.model.entity.Student;
import com.elvin.employeecrud.model.enums.Gender;

public class AddStudentFragment extends Fragment implements View.OnClickListener {

    private EditText etFullName, etAge, etAddress;
    private RadioGroup rgGender;
    private Button btnSaveStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_student, container, false);
        // bind fragment controls
        this.etFullName = root.findViewById(R.id.etFullName);
        this.etAge = root.findViewById(R.id.etAge);
        this.etAddress = root.findViewById(R.id.etAddress);
        this.rgGender = root.findViewById(R.id.rgGender);
        this.btnSaveStudent = root.findViewById(R.id.btnSaveStudent);

        this.btnSaveStudent.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        String fullName = this.etFullName.getText().toString().trim();
        String age = this.etAge.getText().toString().trim();
        String address = this.etAddress.getText().toString().trim();
        RadioButton genderRadio = getView().findViewById(this.rgGender.getCheckedRadioButtonId());

        if (TextUtils.isEmpty(fullName)) {
            this.etFullName.setError("Field full name is required!!!");
            return;
        } else if (TextUtils.isEmpty(age)) {
            this.etAge.setError("Field age is required!!!");
            return;
        } else if (TextUtils.isEmpty(address)) {
            this.etAddress.setError("Field address is required!!!");
            return;
        } else if (genderRadio == null) {
            Toast.makeText(getContext(), "Field gender is required!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        InAppDataHolder.students.add(new Student(fullName, Integer.parseInt(age), address, Gender.getEnum(String.valueOf(genderRadio.getText()))));
        Toast.makeText(getContext(), "Student added!!!", Toast.LENGTH_SHORT).show();
        this.etFullName.setText("");
        this.etAge.setText("");
        this.etAddress.setText("");
        this.rgGender.clearCheck();
    }
}