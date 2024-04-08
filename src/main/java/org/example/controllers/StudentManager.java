package org.example.controllers;

import org.example.models.Address;
import org.example.models.Gender;
import org.example.models.Student;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StudentManager {
    private List<Student> students;

    public boolean addStudent(String name, int age, Address address, Gender gender) {
        if (age >= 100 || age <= 0) throw new InvalidParameterException("Age is invalid");
        if (Objects.equals(name, "") || name == null) throw new InvalidParameterException("Name is invalid");
        if (this.students == null) {
            this.students = new ArrayList<>();
        }
        this.students.add(new Student(name, age, address, gender));
        return true;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> getStudentsByProvince(String province, int length) {
        List<Student> result = new ArrayList<>();
        if (this.students == null) return result;

        for (Student s : this.students) {
            if (s.getAddress() != null && Objects.equals(s.getAddress().getProvince().toLowerCase(), province.toLowerCase())) {
                result.add(s);
            }
            if (result.size() == length) break;
        }
        return result;
    }

    public List<Student> getStudentsByProvince(String province) {
        return this.getStudentsByProvince(province, 10);
    }

    public int countStudentByGenderAndProvince(String province, Gender gender) {
        AtomicInteger count_students = new AtomicInteger();
        this.students.forEach((s) -> {
            if (s.getGender() == gender && s.getAddress() != null && Objects.equals(s.getAddress().getProvince().toLowerCase(), province.toLowerCase()))
                count_students.getAndIncrement();
        });
        return count_students.get();
    }

    public int getAverageAgeByGender(Gender gender) {
        AtomicInteger sum_of_age = new AtomicInteger();
        AtomicInteger count_students = new AtomicInteger(0);
        this.students.forEach((s) -> {
            if (s.getGender() == gender) {
                sum_of_age.addAndGet(s.getAge());
                count_students.getAndIncrement();
            }
        });
        if (count_students.get() == 0) return 0;
        else return sum_of_age.get() / count_students.get();
    }

    public boolean checkStudentIsExisted(String name, int age, Gender gender, String province) {
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        this.students.forEach((s) -> {
            if (Objects.equals(s.getName().toLowerCase(), name.toLowerCase())
                    && s.getAge() == age
                    && s.getGender() == gender
                    && s.getAddress() != null
                    && Objects.equals(s.getAddress().getProvince().toLowerCase(), province.toLowerCase())
            ) {
                result.set(true);
                return;
            }
        });
        return result.get();
    }

    public int countWithoutAddress(Gender gender) {
        AtomicInteger result = new AtomicInteger(0);
        this.students.forEach((s) -> {
            if (s.getGender() == gender && s.getAddress() == null) {
                result.getAndIncrement();
            }
        });
        return result.get();
    }

    public List<Integer> getAgeByPrefixNameAndGender(String prefixName, Gender gender) {
        List<Integer> ageList = new ArrayList<>();
        this.students.forEach(s -> {
            if (s.getGender() == gender && s.getName().startsWith(prefixName)) {
                ageList.add(s.getAge());
            }
        });
        return ageList;
    }

    public List<Student> getStudentsByAddress(String province, String district, String village) {
        List<Student> studentList = new ArrayList<>();
        this.students.forEach(s -> {
            if (s.getAddress() != null
                    && Objects.equals(s.getAddress().getProvince().toLowerCase(), province.toLowerCase())
                    && Objects.equals(s.getAddress().getDistrict().toLowerCase(), district.toLowerCase())
                    && Objects.equals(s.getAddress().getVillage().toLowerCase(), village.toLowerCase())
            ) {
                studentList.add(s);
            }
        });
        studentList.sort(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getAge).reversed());
        return studentList;
    }
}
