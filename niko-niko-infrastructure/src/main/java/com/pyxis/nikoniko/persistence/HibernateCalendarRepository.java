package com.pyxis.nikoniko.persistence;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.calendar.CalendarDate;
import com.pyxis.nikoniko.domain.calendar.CalendarRepository;
import com.pyxis.nikoniko.domain.calendar.Mood;
import com.pyxis.nikoniko.domain.calendar.MoodType;

@Repository
public class HibernateCalendarRepository implements CalendarRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateCalendarRepository(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    private Mood getMoodFor(User user, CalendarDate date) {
	return (Mood)currentSession().createCriteria(Mood.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("calendarDay", date)).uniqueResult();
    }

    @Transactional
    public void setMood(User user, CalendarDate date, MoodType moodType) {
	Mood mood = getMoodFor(user, date);
	if(mood == null) {
	    mood = new Mood(user, date, moodType);
	}
	mood.setValue(moodType);
	currentSession().saveOrUpdate(mood);
    }

    @Transactional
    public List<MoodType> getMoodsFor(User user) {
	CalendarDate today = new CalendarDate();
	CalendarDate aWeekAgo = new CalendarDate().aWeekAgo();

	@SuppressWarnings("unchecked")
        List<Mood> moods = currentSession().createCriteria(Mood.class).add(Restrictions.gt("calendarDay", aWeekAgo)).addOrder(Order.asc("calendarDay")).list();

	CalendarDate expectedDay = today;
	for (int i = 0; i < 7; i++) {
	    boolean found = false;
	    for (Iterator<Mood> iter = moods.iterator(); iter.hasNext();) {
		Mood mood = iter.next();
		if (mood.getDate().equals(expectedDay)) {
		    found = true;
		}
	    }

	    // fill missing dates
	    if (!found) {
		moods.add(i, new Mood(user, expectedDay, MoodType.UNKNOWN));
	    }
	    expectedDay = expectedDay.yesterday();
	}

	// transform
	Iterable<MoodType> transform = Iterables.transform(moods, new Function<Mood, MoodType>() {
	    public MoodType apply(Mood mood) {
		return mood.getValue();
	    }
	});
	return Lists.newArrayList(transform);
    }

    private Session currentSession() {
	return sessionFactory.getCurrentSession();
    }

    public List<CalendarDate> getDaysForLastWeek() {
	List<CalendarDate> days = Lists.newArrayList();
	CalendarDate day = new CalendarDate();
	for(int i=0; i<7; i++) {
	    days.add(day);
	    day = day.yesterday();
	}
	return days;
    }
}
