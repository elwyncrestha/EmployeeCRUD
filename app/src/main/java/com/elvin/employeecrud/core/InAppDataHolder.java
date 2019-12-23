package com.elvin.employeecrud.core;

import com.elvin.employeecrud.model.entity.Student;
import com.elvin.employeecrud.model.enums.Gender;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Elvin Shrestha on 13/12/19
 */
public class InAppDataHolder {

    public static List<Student> students;

    static {
        students = new LinkedList<Student>() {{
            add(new Student("Elvin Shrestha", 20, "Tahachal", Gender.MALE));
            add(new Student("Evana Shrestha", 16, "Jhochhen", Gender.FEMALE));
            add(new Student("John Doe", 25, "Kathmandu", Gender.OTHER));
        }};
    }

}
