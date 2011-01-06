package com.pyxis.nikoniko.persistence.testutils;

import org.hibernate.Session;

public interface UnitOfWork {

    void work(Session session) throws Exception;
}
