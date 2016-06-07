package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.entity.User;

public interface UserService {

    User getByLogin(String login);

    User add(User user);

    int delete(Long id);

}
