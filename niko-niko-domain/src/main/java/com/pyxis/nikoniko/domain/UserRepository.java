package com.pyxis.nikoniko.domain;

import java.util.List;

public interface UserRepository {
    void add(User user);
    List<User> list();
    Maybe<User> findByName(String username);
}
