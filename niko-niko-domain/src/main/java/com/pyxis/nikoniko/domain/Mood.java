package com.pyxis.nikoniko.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Mood {
    @SuppressWarnings("unused")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @Embedded
    private CalendarDate calendarDay;
    
    private Smiley value;
    
    public Mood(User user, CalendarDate calendarDay, Smiley value) {
	this.user = user;
	this.calendarDay = calendarDay;
	this.value = value;
    }

    public User getUser() {
        return user;
    }

    public CalendarDate getDate() {
        return calendarDay;
    }

    public Smiley getValue() {
        return value;
    }
}
