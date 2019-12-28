package com.elvin.employeecrud.ui.fragment.employee;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elvin.employeecrud.R;
import com.elvin.employeecrud.core.RetrofitUtils;
import com.elvin.employeecrud.model.entity.Employee;
import com.elvin.employeecrud.model.service.EmployeeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeFragment extends Fragment implements View.OnClickListener {

    private EditText etFullName, etAge, etSalary;
    private Button btnSaveEmployee;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_employee, container, false);
        // bind fragment controls
        this.etFullName = root.findViewById(R.id.etFullName);
        this.etAge = root.findViewById(R.id.etAge);
        this.etSalary = root.findViewById(R.id.etSalary);
        this.btnSaveEmployee = root.findViewById(R.id.btnSaveEmployee);

        this.btnSaveEmployee.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        String fullName = this.etFullName.getText().toString().trim();
        String age = this.etAge.getText().toString().trim();
        String salary = this.etSalary.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            this.etFullName.setError("Field full name is required!!!");
            return;
        } else if (TextUtils.isEmpty(age)) {
            this.etAge.setError("Field age is required!!!");
            return;
        } else if (TextUtils.isEmpty(salary)) {
            this.etSalary.setError("Field salary is required!!!");
            return;
        }

        RetrofitUtils.getInstance().create(EmployeeService.class).save(new Employee(
                null, fullName, Float.parseFloat(salary), Integer.parseInt(age), null
        )).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() != 200) {
                    Toast.makeText(getContext(), "Some Error Occurred!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getContext(), "Employee added!!!", Toast.LENGTH_SHORT).show();
                etFullName.setText("");
                etAge.setText("");
                etSalary.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(AddEmployeeFragment.class.getName(), "Error: ", t);
                Toast.makeText(getContext(), "Some Error Occurred!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}