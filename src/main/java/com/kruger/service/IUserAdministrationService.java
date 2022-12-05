package com.kruger.service;

import com.kruger.model.User;

public interface IUserAdministrationService extends ICRUD<User> {

	User registerByAdmin(User user);
}
