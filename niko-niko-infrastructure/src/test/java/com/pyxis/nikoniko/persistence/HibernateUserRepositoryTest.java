package com.pyxis.nikoniko.persistence;

import static com.pyxis.nikoniko.persistence.testutils.PersistenceContext.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintViolationException;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pyxis.nikoniko.domain.Maybe;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;
import com.pyxis.nikoniko.persistence.testutils.Database;

public class HibernateUserRepositoryTest {

    private Database database = Database.connect(get(SessionFactory.class));
    private UserRepository repository = get(UserRepository.class);

    @Before
    public void cleanDatabase() {
        database.clean();
    }

    @After
    public void closeDatabase() {
        database.disconnect();
    }

	@Test
	public void userCanBeFound() {
		repository.add(new User("bob"));
		
		Maybe<User> retrievedUser = repository.findByName("bob");
		assertEquals("bob", retrievedUser.bare().getUsername());
	}

	@Test(expected=ConstraintViolationException.class)
	public void usernameCannotBeNull() {
		repository.add(new User(null));
	}

	@Test(expected=ConstraintViolationException.class)
	public void userCannotBeEmpty() {
		repository.add(new User(""));
	}

	@Test(expected=org.hibernate.exception.ConstraintViolationException.class)
	public void userIsUnique() {
		repository.add(new User("bob"));
		repository.add(new User("bob"));
	}

	@Test 
	public void emptyUserList() {
		assertTrue("user repo should be empty", repository.list().isEmpty());
	}
	
	@Test 
	public void listSingleUser() {
		repository.add(new User("bob"));
		assertEquals(1, repository.list().size());
	}
	
	@Test 
	public void listTwoUsers() {
		repository.add(new User("alice"));
		repository.add(new User("bob"));
		assertEquals(2, repository.list().size());
	}

}
