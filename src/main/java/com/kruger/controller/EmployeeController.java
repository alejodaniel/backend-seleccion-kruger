package com.kruger.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.dto.FilterDTO;
import com.kruger.exception.ModeloNotFoundException;
import com.kruger.model.Employee;
import com.kruger.resp.BaseResp;
import com.kruger.service.IEmployeeService;
import com.kruger.util.Validate;

import static com.kruger.util.Constants.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private LocalDateTime localDateTime = LocalDateTime.now();
	private Timestamp date = Timestamp.valueOf(localDateTime);

	@Autowired
	private IEmployeeService service;

	@PreAuthorize(AUTHORITY_ADMIN)
	@GetMapping(produces = TYPES_RESPONSE)
	public List<Employee> getAllList() {
		return service.getAll();
	}

	@PreAuthorize(AUTHORITY_ADMIN)
	@GetMapping(value = "/pageable", produces = TYPES_RESPONSE)
	public BaseResp getAll(Pageable pageable) {
		BaseResp baseResp = new BaseResp();
		Page<Employee> employee;
		employee = service.getAllEmployee(pageable);
		baseResp.setStatusCode(CODE_OK);
		baseResp.setMessage(OK);
		baseResp.setData(employee);
		return baseResp;
	}

	/* MÉTODO ---> eliminado lógico de catálogos */
	@PreAuthorize(AUTHORITY_ADMIN)
	@PostMapping(value = "/{id}")
	public BaseResp deleted(@PathVariable("id") Integer id) {
		System.out.println(id);
		BaseResp baseResp = new BaseResp();
		baseResp.setStatusCode(CODE_OK);
		baseResp.setMessage(OK_DELETED);
		baseResp.setData("");
		service.updateLogicalDelete(id);
		return baseResp;
	}
	/* FIN_MÉTODO ---> eliminado lógico de catálogos */

	@PreAuthorize(AUTHORITY_ADMIN)
	@GetMapping(value = "/{id}", produces = TYPES_RESPONSE)
	public BaseResp getById(@PathVariable(ID) Integer id) {
		Employee employee;
		BaseResp baseResp = new BaseResp();
		employee = service.getOne(id);
		baseResp.setStatusCode(CODE_OK);
		baseResp.setMessage(OK);
		baseResp.setData(employee);
		if (employee == null) {

			throw new ModeloNotFoundException("ID: " + id);
		}
		return baseResp;
	}

	@PreAuthorize(AUTHORITY_ADMIN)
	@PostMapping(value = "/admin", produces = TYPES_RESPONSE, consumes = TYPES_RESPONSE)
	public BaseResp registerByAdmin(@RequestBody Employee employee) {
		Validate validate = new Validate();
		BaseResp baseResp = new BaseResp();
		boolean empty = validate.validateCreatinEmploye(employee.getIdentification(), employee.getNames(),
				employee.getLastNames(), employee.getEmail());

		boolean idNumber = validate.validateIdentEc(employee.getIdentification().toString());
		boolean name = validate.isAlpha(employee.getNames());
		boolean lastNames = validate.isAlpha(employee.getLastNames());

		if (empty) {
			if (idNumber) {
				if (name) {
					if (lastNames) {
						baseResp.setStatusCode(CODE_CREATED);
						baseResp.setMessage(EMPLOYED_CREATED);
						Employee data = service.registerTransactional(employee);
						baseResp.setData(data);
						return baseResp;
					} else {
						baseResp.setStatusCode(500);
						baseResp.setMessage("lasname erronea");
						baseResp.setData(null);
						return baseResp;
					}
				} else {
					baseResp.setStatusCode(500);
					baseResp.setMessage("name erronea");
					baseResp.setData(null);
					return baseResp;
				}

			} else {
				baseResp.setStatusCode(500);
				baseResp.setMessage("Cédula erronea");
				baseResp.setData(null);
				return baseResp;
			}

		} else {
			baseResp.setStatusCode(500);
			baseResp.setMessage("faltan campos");

			baseResp.setData(null);
			return baseResp;
		}

	}

	@PreAuthorize(AUTHORITY_ADMIN)
	@PutMapping(consumes = TYPES_RESPONSE, produces = TYPES_RESPONSE)
	public BaseResp update(@RequestBody Employee employee) {
		Validate validate = new Validate();
		BaseResp baseResp = new BaseResp();
		boolean empty = validate.validateCreatinEmploye(employee.getIdentification(), employee.getNames(),
				employee.getLastNames(), employee.getEmail());

		boolean idNumber = validate.validateIdentEc(employee.getIdentification().toString());
		boolean name = validate.isAlpha(employee.getNames());
		boolean lastNames = validate.isAlpha(employee.getLastNames());

		if (empty) {
			if (idNumber) {
				if (name) {
					if (lastNames) {
						baseResp.setStatusCode(CODE_CREATED);
						baseResp.setMessage("Empleado Actualizado");
						// Employee data = ser;
						employee.setUpdatedAt(date);
						employee.setUpdatedBy("ADMIN");
						employee.setIsDeleted(0);
						Employee data = service.update(employee);

						baseResp.setData(data);
						return baseResp;
					} else {
						baseResp.setStatusCode(500);
						baseResp.setMessage("lasname erronea");
						baseResp.setData(null);
						return baseResp;
					}
				} else {
					baseResp.setStatusCode(500);
					baseResp.setMessage("name erronea");
					baseResp.setData(null);
					return baseResp;
				}

			} else {
				baseResp.setStatusCode(500);
				baseResp.setMessage("Cédula erronea");
				baseResp.setData(null);
				return baseResp;
			}

		} else {
			baseResp.setStatusCode(500);
			baseResp.setMessage("faltan campos");

			baseResp.setData(null);
			return baseResp;
		}
	}

	@PreAuthorize(AUTHORITY_ADMIN)
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable(ID) Integer id) {
		service.remove(id);
	}

	@PreAuthorize(AUTHORITY_ADMIN)
	@PostMapping(value = "/find", produces = TYPES_RESPONSE, consumes = TYPES_RESPONSE)
	public BaseResp find(@RequestBody FilterDTO filter) {
		BaseResp baseResp = new BaseResp();
		List<Employee> employee = new ArrayList<>();

		if (filter != null) {
			if (filter.getVaccinateState() != null) {
				employee = service.getByVaccinateStatus(filter);
			} else {
				if (filter.getVaccinateType() != null) {
					employee = service.getByVaccinateType(filter);
				} else {
					if (filter.getDateInit() != null && filter.getDateEnd() != null) {
						System.out.println(filter.getDateInit().getClass().getSimpleName());

						employee = service.getByVaccineDate(filter);
					}
				}
			}
		}
		baseResp.setStatusCode(CODE_OK);
		baseResp.setMessage(OK);
		baseResp.setData(employee);
		return baseResp;

	}

	/* AUTHORITY EMPLOYEE */

	@PreAuthorize(AUTHORITY_EMPLOYEE)
	@GetMapping(value = "/getByEmail", produces = TYPES_RESPONSE, consumes = TYPES_RESPONSE)
	public BaseResp getByEmail(@RequestBody Employee employee) {
		BaseResp baseResp = new BaseResp();
		employee = service.getByEmail(employee.getEmail());
		baseResp.setStatusCode(CODE_OK);
		baseResp.setMessage(OK);
		baseResp.setData(employee);
		return baseResp;
	}

	@PreAuthorize(AUTHORITY_EMPLOYEE)
	@PutMapping(value = "/update", consumes = TYPES_RESPONSE, produces = TYPES_RESPONSE)
	public BaseResp updateEmployee(@RequestBody Employee employee) {
		BaseResp baseResp = new BaseResp();
		Validate validate = new Validate();
		boolean idNumber = validate.validateIdentEc(employee.getIdentification().toString());
		boolean name = validate.isAlpha(employee.getNames());
		boolean lastNames = validate.isAlpha(employee.getLastNames());
		boolean email = validate.validateEmail(employee.getEmail());
		boolean date = validate.validateDate(employee.getVaccineDate());
		if (idNumber) {
			if (name) {
				if (lastNames) {
					if (email) {
						if (employee.getVaccinationstatus().toString().equals("VACUNADO")) {
							System.out.println(employee.getDoseNumber());
							if (employee.getVaccineTypeId().equals(null) || employee.getVaccineDate().equals(null)
									|| employee.getDoseNumber().equals(null)) {
								baseResp.setData(null);
								baseResp.setStatusCode(CODE_ERROR);
								baseResp.setMessage("Faltan campos");
								return baseResp;
							} else {
								employee.setVaccineDate(employee.getVaccineDate());
								if (date) {
									System.out.println(date);
									employee.setVaccineTypeId(employee.getVaccineTypeId());
									employee.setDoseNumber(employee.getDoseNumber());
									service.update(employee);
								} else {
									baseResp.setData(null);
									baseResp.setStatusCode(CODE_ERROR);
									baseResp.setMessage("Fecha Errónea");
									return baseResp;
								}

							}
						} else {
							employee.setVaccineDate(null);
							employee.setVaccineTypeId(null);
							employee.setDoseNumber(null);
							service.update(employee);
						}

						baseResp.setStatusCode(CODE_OK);
						baseResp.setMessage(OK);
						baseResp.setData(employee);
						return baseResp;
					} else {
						baseResp.setStatusCode(500);
						baseResp.setMessage("Email Erróneo");
						baseResp.setData(null);
						return baseResp;
					}

				} else {
					baseResp.setStatusCode(500);
					baseResp.setMessage("Apellido Erróneo");
					baseResp.setData(null);
					return baseResp;
				}
			} else {
				baseResp.setStatusCode(500);
				baseResp.setMessage("Nombre Erróneo");
				baseResp.setData(null);
				return baseResp;
			}

		} else {
			baseResp.setStatusCode(500);
			baseResp.setMessage("Cédula Errónea");
			baseResp.setData(null);
			return baseResp;
		}

	}

}
