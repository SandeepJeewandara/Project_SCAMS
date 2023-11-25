package com.example.scams_ood;

import java.util.Date;
import java.util.List;

public class Student {
    private int studentId;
    private String studentName;
    private Date studentDob;
    private String studentGmail;
    private List<Club> clubsJoined;


    public void joinClub(Club club) {
        clubsJoined.add(club);
    }

    public Student(int studentId, String studentName, Date studentDob, String studentGmail, List<Club> clubsJoined) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentGmail = studentGmail;
        this.clubsJoined = clubsJoined;
    }
}
