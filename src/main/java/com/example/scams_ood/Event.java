package com.example.scams_ood;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Event {

    private String eventId;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventDescription;
    private Club club;
    private ClubAdvisor clubAdvisor;

    public Event(String eventId, String eventName, Date eventDate,Time eventTime, String eventDescription, Club club) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime =eventTime;
        this.eventDescription = eventDescription;
        this.club = club;
    }

    public Event() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public ClubAdvisor getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(ClubAdvisor clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }
}


