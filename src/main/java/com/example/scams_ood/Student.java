package com.example.scams_ood;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

import java.util.Date;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private Date studentDob;
    private String studentGmail;
    private List<Club> clubsJoined;

    //private BooleanProperty attendance;

    public Student(String studentId, String studentName, Date studentDob, String studentGmail, List<Club> clubsJoined) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentGmail = studentGmail;
        this.clubsJoined = clubsJoined;
    }

    public Student() {
    }

    /*
    public BooleanProperty attendanceProperty(){
        if(attendance == null){
            attendance = new SimpleBooleanProperty(this,"attendance",false);
        }
        return attendance;
    }

     */

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

    public Date getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(Date studentDob) {
        this.studentDob = studentDob;
    }

    public String getStudentGmail() {
        return studentGmail;
    }

    public void setStudentGmail(String studentGmail) {
        this.studentGmail = studentGmail;
    }

    public List<Club> getClubsJoined() {
        return clubsJoined;
    }

    public void setClubsJoined(List<Club> clubsJoined) {
        this.clubsJoined = clubsJoined;
    }


}
