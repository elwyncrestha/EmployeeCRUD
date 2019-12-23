package com.elvin.employeecrud.core;

import com.elvin.employeecrud.model.service.EmployeeService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Elvin Shrestha on 12/23/19
 */
public class AppRequestBuilder {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(EmployeeService.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
