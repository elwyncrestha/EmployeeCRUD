package com.elvin.employeecrud.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elvin.employeecrud.R;
import com.elvin.employeecrud.core.RetrofitUtils;
import com.elvin.employeecrud.model.entity.Employee;
import com.elvin.employeecrud.model.service.EmployeeService;
import com.elvin.employeecrud.ui.adapter.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rvEmployees = root.findViewById(R.id.rvEmployees);

        List<Employee> employeeList = new ArrayList<>();
        RetrofitUtils.getInstance().create(EmployeeService.class).findAll().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                response.body().forEach(employeeList::add);
                EmployeeAdapter employeeAdapter = new EmployeeAdapter(getContext(), employeeList);
                rvEmployees.setAdapter(employeeAdapter);
                rvEmployees.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Failed to load employee", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}