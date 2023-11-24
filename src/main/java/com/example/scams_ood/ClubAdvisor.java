package com.example.scams_ood;

import java.util.List;

public class ClubAdvisor {
    private int advisorId;
    private String advisorName;
    private String advisorGmail;
    private List<Club> managedClubs; // Bi-directional association

    public ClubAdvisor(int advisorId, String advisorName, String advisorGmail, List<Club> managedClubs) {
        this.advisorId = advisorId;
        this.advisorName = advisorName;
        this.advisorGmail = advisorGmail;
        this.managedClubs = managedClubs;
    }


}
