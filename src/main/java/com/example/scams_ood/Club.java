package com.example.scams_ood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Club {

    private String clubId;
    private String clubName;
    private String clubType;
    private LocalDate startedDate;
    private String clubDescription;
    private String clubLogoPath;
    private ClubAdvisor clubAdvisor;
    private List<Student> members;
    private List<Event> events;

    public Club(String clubId, String clubName, String clubType, LocalDate startedDate, String clubDescription, String clubLogoPath, ClubAdvisor clubAdvisor) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubType = clubType;
        this.startedDate = startedDate;
        this.clubDescription = clubDescription;
        this.clubLogoPath = clubLogoPath;
        this.clubAdvisor = clubAdvisor;

        this.members = new ArrayList<>();
        this.events = new ArrayList<>();
    }



    public String getClubId() {
        return clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubType() {
        return clubType;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public String getClubLogoPath() {
        return clubLogoPath;
    }

    public ClubAdvisor getClubAdvisor() {
        return clubAdvisor;
    }

    public List<Student> getMembers() {
        return members;
    }

    public List<Event> getEvents() {
        return events;
    }



    public void setClubAdvisor(ClubAdvisor clubAdvisor) {
        if (this.clubAdvisor == null) {
            this.clubAdvisor = clubAdvisor;
            clubAdvisor.addManagedClub(this); // Add this club to the advisor's managed clubs
        }
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addMember(Student student) {
        members.add(student);
        student.getClubsJoined().add(this);
    }


    @Override
    public String toString() {
        return "Club{" +
                "clubId='" + clubId + '\'' +
                ", clubName='" + clubName + '\'' +
                ", clubType='" + clubType + '\'' +
                ", startedDate=" + startedDate +
                ", clubDescription='" + clubDescription + '\'' +
                ", clubLogoPath='" + clubLogoPath + '\'' +
                ", clubAdvisor=" + (clubAdvisor != null ? clubAdvisor.getAdvisorId() : "null") +
                ", events=" + events +
                '}';
    }
}
