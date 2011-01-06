package com.pyxis.nikoniko.persistence.testutils;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pyxis.nikoniko.domain.User;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
            User.class
    };
    private static final String[] SEQUENCE_NAMES = {
    };

    private final Session session;

    public DatabaseCleaner(Session session) {
        this.session = session;
    }

    public void clean() {
        Transaction transaction = session.beginTransaction();
        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        for (String sequenceName : SEQUENCE_NAMES) {
            resetSequence(sequenceName);
        }
        transaction.commit();
    }

    private void resetSequence(String sequenceName) {
        session.createSQLQuery("truncate " + sequenceName).executeUpdate();
    }

    private void deleteEntities(Class<?> entityType) {
        session.createQuery("delete from " + entityNameOf(entityType)).executeUpdate();
    }

    private String entityNameOf(Class<?> entityType) {
        return entityType.getName();
    }
}
