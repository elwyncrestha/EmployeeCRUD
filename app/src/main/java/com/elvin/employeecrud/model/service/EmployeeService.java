package com.elvin.employeecrud.model.service;

import com.elvin.employeecrud.model.entity.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Elvin Shrestha on 12/23/19
 */
public interface EmployeeService {
    String URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<List<Employee>> findAll();

    @GET("employee/{id}")
    Call<Employee> findOne(@Path("id") long id);

    @POST("create")
    Call<Void> save(@Body Employee employee);

    @DELETE("delete/{id}")
    Call<Void> delete(@Path("id") long id);

}
