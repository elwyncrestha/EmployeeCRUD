package com.elvin.employeecrud.model.enums;

/**
 * @author Elvin Shrestha on 13/12/19
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static Gender getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (Gender v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }
}
