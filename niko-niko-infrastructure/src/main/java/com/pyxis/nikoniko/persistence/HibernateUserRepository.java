package com.pyxis.nikoniko.persistence;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pyxis.nikoniko.domain.Maybe;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;

@Repository
public class HibernateUserRepository implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void add(User user) {
	currentSession().saveOrUpdate(user);
    }

    @Transactional(readOnly = true)
    public Maybe<User> findByName(String username) {
	return Maybe.possibly((User) currentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult());
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<User> list() {
	return currentSession().createCriteria(User.class).list();
    }

    private Session currentSession() {
	return sessionFactory.getCurrentSession();
    }
}
