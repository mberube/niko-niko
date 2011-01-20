package com.pyxis.nikoniko.persistence;

import static com.pyxis.nikoniko.persistence.testutils.PersistenceContext.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

import java.util.List;

import org.hibernate.SessionFactory;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;
import com.pyxis.nikoniko.domain.calendar.CalendarDate;
import com.pyxis.nikoniko.domain.calendar.CalendarRepository;
import com.pyxis.nikoniko.domain.calendar.DayOfWeek;
import com.pyxis.nikoniko.domain.calendar.MoodType;
import com.pyxis.nikoniko.persistence.testutils.Database;

public class HibernateCalendarRepositoryTest {
    private Database database = Database.connect(get(SessionFactory.class));
    private UserRepository userRepository = get(UserRepository.class);
    private CalendarRepository repository = get(CalendarRepository.class);
    private CalendarDate today = new CalendarDate();

    @Before
    public void cleanDatabase() {
	database.clean();
    }
    
    @Before
    public void resetTime() {
	DateTimeUtils.setCurrentMillisSystem();
    }

    @After
    public void closeDatabase() {
	database.disconnect();
    }
    
    @Test
    public void moodCanBeSaved() {
	User bob = createBob();
	repository.setMood(bob, new CalendarDate(), MoodType.MAD);
    }
    
    @Test
    public void defaultMoodsAreAllUnknown() {
	User bob = createBob();
	List<MoodType> moods = repository.getMoodsFor(bob);
	
	assertThat("mood size should be 7", iterableWithSize(7).matches(moods));
	for(MoodType type : moods) {
	    assertThat("mood should be unknown", MoodType.UNKNOWN, equalTo(type));    
	}
    }
    
    @Test
    public void defaultMoodsIsReturnedEvenIfOldMoodIsSet() {
	User bob = createBob();
	CalendarDate aWeekAgo = new CalendarDate().aWeekAgo();
	repository.setMood(bob, aWeekAgo, MoodType.HAPPY);
	
	List<MoodType> moods = repository.getMoodsFor(bob);
	
	assertThat("mood size should be 7", iterableWithSize(7).matches(moods));
	for(MoodType type : moods) {
	    assertThat("mood should be unknown", MoodType.UNKNOWN, equalTo(type));    
	}
    }
    
    @Test
    public void moodCanBeSet() {
	User bob = createBob();
	repository.setMood(bob, today, MoodType.HAPPY);
	List<MoodType> moods = repository.getMoodsFor(bob);
	
	assertThat("mood size should be 7", iterableWithSize(7).matches(moods));
	assertThat(MoodType.HAPPY, equalTo(moods.get(0)));
    }
    
    @Test
    public void duplicateMoodsOverrideValue() {
	User bob = createBob();
	repository.setMood(bob, today, MoodType.HAPPY);
	repository.setMood(bob, today, MoodType.MAD);
	List<MoodType> moods = repository.getMoodsFor(bob);
	
	assertThat("mood size should be 7", iterableWithSize(7).matches(moods));
	assertThat(MoodType.MAD, equalTo(moods.get(0)));
    }
    
    @Test
    public void moodCanBeSetAndRetrievedUpToAWeekAgo() {
	User bob = createBob();
	CalendarDate sixDaysAgo = sixDaysAgo();
	repository.setMood(bob, sixDaysAgo, MoodType.HAPPY);
	List<MoodType> moods = repository.getMoodsFor(bob);
	
	assertThat("mood size should be 7", iterableWithSize(7).matches(moods));
	assertThat(MoodType.HAPPY, equalTo(moods.get(6)));
    }
    
    @Test
    public void retrieveDaysForLastWeek() {
	long expectedTimestamp = new LocalDate(2011, 1, 19).toDateTimeAtStartOfDay().getMillis();
	DateTimeUtils.setCurrentMillisFixed(expectedTimestamp);
	List<CalendarDate> daysForLastWeek = repository.getDaysForLastWeek();
	assertThat(7, equalTo(daysForLastWeek.size()));
	assertThat(DayOfWeek.WEDNESDAY.localized(), equalTo(daysForLastWeek.get(0).getDayOfTheWeek()));
	assertThat(DayOfWeek.TUESDAY.localized(), equalTo(daysForLastWeek.get(1).getDayOfTheWeek()));
	assertThat(DayOfWeek.MONDAY.localized(), equalTo(daysForLastWeek.get(2).getDayOfTheWeek()));
	assertThat(DayOfWeek.SUNDAY.localized(), equalTo(daysForLastWeek.get(3).getDayOfTheWeek()));
	assertThat(DayOfWeek.SATURDAY.localized(), equalTo(daysForLastWeek.get(4).getDayOfTheWeek()));
	assertThat(DayOfWeek.FRIDAY.localized(), equalTo(daysForLastWeek.get(5).getDayOfTheWeek()));
	assertThat(DayOfWeek.THURSDAY.localized(), equalTo(daysForLastWeek.get(6).getDayOfTheWeek()));
    }
    
    private CalendarDate sixDaysAgo() {
	CalendarDate date = new CalendarDate();
	for(int i=0; i<6; i++) {
	    date = date.yesterday();
	}
	return date;
    }

    private User createBob() {
	User bob = new User("bob");
	userRepository.add(bob);
	return bob;
    }
}
