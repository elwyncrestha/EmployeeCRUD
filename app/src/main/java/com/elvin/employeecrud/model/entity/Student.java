package com.elvin.employeecrud.model.entity;

import com.elvin.employeecrud.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Elvin Shrestha on 13/12/19
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String fullName;
    private Integer age;
    private String address;
    private Gender gender;

}
