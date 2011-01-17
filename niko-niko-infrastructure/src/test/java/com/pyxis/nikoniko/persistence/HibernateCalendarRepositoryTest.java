package com.pyxis.nikoniko.persistence;

import static com.pyxis.nikoniko.persistence.testutils.PersistenceContext.get;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pyxis.nikoniko.domain.CalendarDate;
import com.pyxis.nikoniko.domain.CalendarRepository;
import com.pyxis.nikoniko.domain.Smiley;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;
import com.pyxis.nikoniko.persistence.testutils.Database;

public class HibernateCalendarRepositoryTest {
    private Database database = Database.connect(get(SessionFactory.class));
    private UserRepository userRepository = get(UserRepository.class);
    private CalendarRepository repository = get(CalendarRepository.class);

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
	User bob = new User("bob");
	userRepository.add(bob);
	repository.setMood(bob, new CalendarDate(), Smiley.MAD);
    }
}
