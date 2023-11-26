package com.example.scams_ood;

import java.util.Date;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private String gender;
    private String Gmail;
    private Date DOB;
    private String Username;
    private String password;
    private List<Club> clubsJoined;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Club> getClubsJoined() {
        return clubsJoined;
    }

    public void setClubsJoined(List<Club> clubsJoined) {
        this.clubsJoined = clubsJoined;
    }

    public Student(String studentId, String studentName, String gender, String gmail, Date DOB, String username, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        Gmail = gmail;
        this.DOB = DOB;
        Username = username;
        this.password = password;
    }

    public Student(String studentId, String studentName, String username, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        Username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", Gmail='" + Gmail + '\'' +
                ", DOB=" + DOB +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", clubsJoined=" + clubsJoined +
                '}';
    }
}
