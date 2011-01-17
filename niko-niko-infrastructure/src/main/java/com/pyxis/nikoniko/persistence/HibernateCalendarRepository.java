package com.pyxis.nikoniko.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pyxis.nikoniko.domain.CalendarDate;
import com.pyxis.nikoniko.domain.CalendarRepository;
import com.pyxis.nikoniko.domain.Mood;
import com.pyxis.nikoniko.domain.Smiley;
import com.pyxis.nikoniko.domain.User;

@Repository
public class HibernateCalendarRepository implements CalendarRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateCalendarRepository(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    @Transactional
    public void setMood(User user, CalendarDate date, Smiley mood) {
	currentSession().saveOrUpdate(new Mood(user, date, mood));
    }
    
    private Session currentSession() {
	return sessionFactory.getCurrentSession();
    }
}
