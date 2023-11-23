package com.example.scams_ood;

import java.util.List;

public class Club {

    private String clubId;
    private String clubName;
    private  String ClubDescription;
    private ClubAdvisor clubAdvisor; // Bi-directional association
    private List<Student> members;
    private List<Event> events;

    private String ImageLogoPath;

    public Club(String clubId, String clubName, String clubDescription, ClubAdvisor clubAdvisor, List<Student> members, List<Event> events) {
        this.clubId = clubId;
        this.clubName = clubName;
        ClubDescription = clubDescription;
        this.clubAdvisor = clubAdvisor;
        this.members = members;
        this.events = events;
    }

    public Club() {
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubDescription() {
        return ClubDescription;
    }

    public void setClubDescription(String clubDescription) {
        ClubDescription = clubDescription;
    }

    public ClubAdvisor getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(ClubAdvisor clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getImageLogoPath() {
        return ImageLogoPath;
    }

    public void setImageLogoPath(String imageLogoPath) {
        ImageLogoPath = imageLogoPath;
    }
}
