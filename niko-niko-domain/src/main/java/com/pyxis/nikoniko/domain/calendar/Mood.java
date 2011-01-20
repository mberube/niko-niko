package com.pyxis.nikoniko.domain.calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.pyxis.nikoniko.domain.User;

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
    
    private MoodType value;
    
    @SuppressWarnings("unused")
    private Mood() {
	// for hibernate
    }
    
    public Mood(User user, CalendarDate calendarDay, MoodType value) {
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

    public MoodType getValue() {
        return value;
    }

    public void setValue(MoodType moodType) {
	this.value = moodType;
    }
}
