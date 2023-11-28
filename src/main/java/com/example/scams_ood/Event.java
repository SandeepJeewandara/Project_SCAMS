package com.example.scams_ood;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private String eventId;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventDescription;
    private Club clubID;
    private List<Student> members;


    public Event() {
        this.members = new ArrayList<>();
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

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Club getClubID() {
        return clubID;
    }

    public void setClubID(Club clubID) {
        this.clubID = clubID;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", eventDescription='" + eventDescription + '\'' +
                ", clubID=" + (clubID != null ? clubID.getClubId() : "null") +
                '}';
    }


}
