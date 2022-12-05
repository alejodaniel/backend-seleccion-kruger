package com.kruger.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kruger.dto.FilterDTO;
import com.kruger.model.Employee;

public interface IEmployeeService extends ICRUD<Employee> {

    Employee registerTransactional(Employee employee);

    Page<Employee> getAllEmployee(Pageable pageable);

    List<Employee> getByVaccinateStatus(FilterDTO filtro);

    List<Employee> getByVaccinateType(FilterDTO filtro);

    List<Employee> getByVaccineDate(FilterDTO filtro);

    Integer getByIdentification(Integer identificationNumber);

    Employee getByEmail(String emailUser);

    void updateLogicalDelete(Integer idEmployee);

}
