package com.pyxis.nikoniko.system.web;

import org.hibernate.SessionFactory;

import com.pyxis.nikoniko.domain.builder.Builder;
import com.pyxis.nikoniko.persistence.testutils.Database;
import static com.pyxis.nikoniko.persistence.testutils.PersistenceContext.get;


public class DatabaseDriver {

	private Database database = new Database(get(SessionFactory.class));

	public void start() {
		database.openConnection();
		database.clean();
	}
	
	public void stop() {
		database.disconnect();
	}
	
    public <T> void given(T... entities) throws Exception {
        database.persist(entities);
    }

    public void given(Builder<?>... builders) throws Exception {
        database.persist(builders);
    }
}
