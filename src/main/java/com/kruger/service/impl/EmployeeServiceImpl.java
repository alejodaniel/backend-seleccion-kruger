package com.kruger.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruger.dao.IEmployeeDAO;
import com.kruger.dao.IUserAdministrationDAO;
import com.kruger.dto.FilterDTO;
import com.kruger.model.Employee;
import com.kruger.model.Rol;
import com.kruger.model.User;
import com.kruger.resp.BaseResp;
import com.kruger.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private LocalDateTime localDateTime = LocalDateTime.now();
	private Timestamp date = Timestamp.valueOf(localDateTime);

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private IUserAdministrationDAO userAdminDao;

	@Override
	public Employee register(Employee t) {

		return employeeDAO.save(t);
	}

	@Override
	public Employee update(Employee t) {

		return employeeDAO.save(t);
	}

	@Override
	public void remove(int id) {
		employeeDAO.deleteById(id);

	}

	@Override
	public Employee getOne(int id) {
		return employeeDAO.findById(id).get();
	}

	@Override
	public List<Employee> getAll() {
		return employeeDAO.findAll();
	}

	@Override
	public List<Employee> getByVaccinateStatus(FilterDTO filtro) {
		return employeeDAO.getByVaccinateStatus(filtro.getVaccinateState());
	}

	@Override
	public List<Employee> getByVaccinateType(FilterDTO filtro) {
		return employeeDAO.getByVaccinateType(filtro.getVaccinateType());
	}

	@Override
	public Page<Employee> getAllEmployee(Pageable pageable) {

		return employeeDAO.getAllEmployee(pageable);
	}

	@Override
	public Employee registerTransactional(Employee employee) {
		BaseResp baseResp = new BaseResp();
		Rol rol = new Rol();
		User user = new User();
		rol.setIdRol(2);
		user.setRoles(new ArrayList());
		user.getRoles().add(rol);
		user.setUsername(employee.getEmail());
		user.setPassword(employee.getIdentification().toString());
		user = userAdminDao.save(user);

		Employee employeeNew = new Employee();
		employeeNew.setIdentification(employee.getIdentification());
		employeeNew.setNames(employee.getNames());
		employeeNew.setLastNames(employee.getLastNames());
		employeeNew.setEmail(user.getUsername());
		employeeNew.setCreatedAt(date);
		employeeNew.setCreatedBy("ADMIN");
		employeeNew.setIsDeleted(0);
		employeeNew.setIdUser(user.getIdUser());
		employeeDAO.save(employeeNew);

		user.setPassword(bcrypt.encode(user.getPassword()));
		userAdminDao.save(user);

		baseResp.setData(user);

		return employeeNew;
	}

	@Override
	public List<Employee> getByVaccineDate(FilterDTO filtro) {
		return employeeDAO.getByVaccineDate(filtro.getDateInit(), filtro.getDateEnd());
	}

	@Override
	public Integer getByIdentification(Integer identificationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getByEmail(String emailUser) {
		return employeeDAO.getByEmail(emailUser);
	}

	@Override
	public void updateLogicalDelete(Integer idEmployee) {
		employeeDAO.updateLogicalDelete(idEmployee);

	}

}
