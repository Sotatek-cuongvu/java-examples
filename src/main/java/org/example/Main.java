package org.example;


import org.example.controllers.StudentManager;
import org.example.models.Address;
import org.example.models.Gender;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
//        1. Add student
        for (int i = 0; i < 10; i++) {
            try {
                manager.addStudent(
                        "test name",
                        i * 10,
                        new Address("Hải Dương", "Nam Dương", "An Bình"),
                        Gender.MALE
                );
            } catch (InvalidParameterException e) {
                System.out.printf("%s is error %s\n", i, e.toString());
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                manager.addStudent(
                        "Nguyễn Thị" + i,
                        i * 10,
                        new Address("Hà Nội", "Cầu Giấy", ""),
                        Gender.FEMALE
                );
            } catch (InvalidParameterException e) {
                System.out.printf("%s is error %s\n", i, e.toString());
            }
        }
//        2
        System.out.println("2. get list student\n");
        manager.getStudents().forEach(s -> System.out.println(s.toString()));
//        3.
        System.out.println("\n3. Get 10 Student have province is Hải Dương");
        manager.getStudentsByProvince("Hải Dương", 10).forEach(s -> System.out.println(s.toString()));
//        4.
        System.out.println("\n4. Count FEMALE student live in HANOI:\t" + manager.countStudentByGenderAndProvince("Hà Nội", Gender.FEMALE));
//        5.
        System.out.println("\n5. Get average of male student's age:\t" + manager.getAverageAgeByGender(Gender.MALE));
//        6.
        System.out.println("\n6. Check user is existed:\t" + manager.checkStudentIsExisted(
                "Nguyễn Văn A", 16, Gender.MALE, "Hải Dương"
        ));
        manager.addStudent("Nguyễn Văn A", 16, new Address("Hải Dương", "Nam Dương", "An Bình"),
                Gender.MALE
        );
        System.out.println("\n6. Check user is existed:\t" + manager.checkStudentIsExisted(
                "Nguyễn Văn A", 16, Gender.MALE, "Hải Dương"
        ));
//        7.
        System.out.println("\n7. Count MALE without address: \t" + manager.countWithoutAddress(Gender.MALE));
        manager.addStudent("Nguyễn Văn A", 16, null, Gender.MALE);
        System.out.println("\n7. Count MALE without address: \t" + manager.countWithoutAddress(Gender.MALE));
//        8.
        System.out.println("\n8. Get age list:\t" + manager.getAgeByPrefixNameAndGender("Nguyễn Thị", Gender.FEMALE));
//        9.
        System.out.println("\n9. Get student by address:");
        manager.addStudent(
                "a",
                15,
                new Address("Hải Dương", "Nam Dương", "An Bình"),
                Gender.MALE
        );
        manager.getStudentsByAddress("Hải Dương", "Nam Dương", "An Bình").forEach(System.out::println);
    }
}