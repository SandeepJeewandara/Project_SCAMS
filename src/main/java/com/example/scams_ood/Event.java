package com.example.scams_ood;

import java.util.Date;

public class Event {

    private int eventId;
    private String eventName;
    private Date eventDate;
    private String eventDescription;
    private Club club;

    public Event(int eventId, String eventName, Date eventDate, String eventDescription, Club club) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.club = club;
    }
}
