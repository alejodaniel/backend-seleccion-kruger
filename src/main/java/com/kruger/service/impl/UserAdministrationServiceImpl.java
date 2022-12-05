package com.kruger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruger.dao.IEmployeeDAO;
import com.kruger.dao.IUserAdministrationDAO;
import com.kruger.model.Employee;
import com.kruger.model.Rol;
import com.kruger.model.User;
import com.kruger.resp.BaseResp;
import com.kruger.service.IUserAdministrationService;
import com.kruger.util.Validate;

@Service
public class UserAdministrationServiceImpl implements IUserAdministrationService {

	@Autowired
	private IUserAdministrationDAO userAdminDao;

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public User register(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerByAdmin(User user) {
		Rol rol = new Rol();
		rol.setIdRol(2);
		user.setRoles(new ArrayList());
		user.getRoles().add(rol);
		user.setPassword(bcrypt.encode("pruebas.2021"));
		userAdminDao.save(user);

		Employee employee = new Employee();
		employee.setIdentification(175141447);
		employee.setEmail(user.getUsername());
		employeeDAO.save(employee);

		return user;
	}

}
