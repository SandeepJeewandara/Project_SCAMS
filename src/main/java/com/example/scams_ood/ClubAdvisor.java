package com.example.scams_ood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ClubAdvisor {

    //Attributes of the Club Advisor Class
    private String advisorId;
    private String name;
    private String gender;
    private String Gmail;
    private Date DOB;
    private String Username;
    private String password;
    private List<Club> managedClubs;


    //Main Constructor of ClubAdvisor Class
    public ClubAdvisor(String advisorId, String name, String gender, String gmail, Date DOB, String username, String password) {
        this.advisorId = advisorId;
        this.name = name;
        this.gender = gender;
        Gmail = gmail;
        this.DOB = DOB;
        Username = username;
        this.password = password;
        this.managedClubs = new ArrayList<>();
    }

    public ClubAdvisor(String advisorId, String name, String username, String password) {
        this.advisorId = advisorId;
        this.name = name;
        Username = username;
        this.password = password;
    }



    //Getters and Setters
    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Club> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(List<Club> managedClubs) {
        this.managedClubs = managedClubs;
    }




    //Method for add Managers to the Club
    public void addManagedClub(Club club) {
        if (!managedClubs.contains(club)) {
            managedClubs.add(club);
            club.setClubAdvisor(this);
        }
    }

    @Override
    public String toString() {
        return "ClubAdvisor{" +
                "advisorId='" + advisorId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", Gmail='" + Gmail + '\'' +
                ", DOB=" + DOB +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

