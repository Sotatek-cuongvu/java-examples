package org.example.models;

import java.util.UUID;

public class Student {
    private String id;
    private String name;
    private int age;
    private Address address;
    private Gender gender;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public Student(String name, int age, Address address, Gender gender) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", gender=" + gender +
                '}';
    }
}
