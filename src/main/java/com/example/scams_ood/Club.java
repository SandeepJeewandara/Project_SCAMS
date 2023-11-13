package com.example.scams_ood;

import java.util.List;

public class Club {

    private int clubId;
    private String clubName;
    private  String ClubDescription;
    private ClubAdvisor clubAdvisor; // Bi-directional association
    private List<Student> members;
    private List<Event> events;

    public Club(int clubId, String clubName, String clubDescription, ClubAdvisor clubAdvisor, List<Student> members, List<Event> events) {
        this.clubId = clubId;
        this.clubName = clubName;
        ClubDescription = clubDescription;
        this.clubAdvisor = clubAdvisor;
        this.members = members;
        this.events = events;
    }
}
