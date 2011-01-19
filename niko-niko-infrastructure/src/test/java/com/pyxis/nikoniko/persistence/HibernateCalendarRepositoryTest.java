package com.pyxis.nikoniko.persistence;

import static com.pyxis.nikoniko.persistence.testutils.PersistenceContext.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pyxis.nikoniko.domain.CalendarDate;
import com.pyxis.nikoniko.domain.CalendarRepository;
import com.pyxis.nikoniko.domain.MoodType;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;
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
