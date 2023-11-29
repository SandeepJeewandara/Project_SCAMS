package com.example.scams_ood;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    //Attributes of the Event Class
    private String eventId;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventDescription;
    private Club clubID;
    private List<Student> members;


    //Main Constructor of Club Class
    public Event(String eventId, String eventName, Date eventDate, Time eventTime, String eventDescription, Club clubID) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.clubID = clubID;
    }

    //Getters and Setters
    public Event() {
        this.members = new ArrayList<>();
    }

    public Event(String eventID, String eventName, LocalDate eventDate, String eventdescription, String eventTime) {
    }

    public Event(String eventID, String eventName, String eventdescription) {
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

    public Date getEventTime() {
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
