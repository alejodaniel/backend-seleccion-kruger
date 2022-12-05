package com.kruger.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import static com.kruger.util.Constants.*;
import com.kruger.model.Employee;

public interface IEmployeeDAO extends JpaRepository<Employee, Integer> {

        @Query(value = SELECT_QUERY
                        + " employee where is_deleted = 0", nativeQuery = true)
        Page<Employee> getAllEmployee(Pageable pageable);

        @Query(value = SELECT_QUERY
                        + " employee where employee.is_deleted = 0 and employee.vaccine_type_id = :typeId", nativeQuery = true)
        List<Employee> getByVaccinateType(@Param("typeId") Integer typeId);

        @Query(value = SELECT_QUERY
                        + " employee where employee.is_deleted = 0 and vaccination_status = :stateId", nativeQuery = true)
        List<Employee> getByVaccinateStatus(@Param("stateId") Integer stateId);

        // @Query(value = SELECT_QUERY
        // + " employee where vaccine_date between :dateInit AND :dateEnd", nativeQuery
        // = true)
        // List<Employee> getByVaccineDate(@Param("dateInit") LocalDate dateInit,
        // @Param("dateEnd") LocalDate dateEnd);

        @Query(value = SELECT_QUERY
                        + " employee where employee.is_deleted = 0 and vaccine_date between :dateInit AND :dateEnd", nativeQuery = true)
        List<Employee> getByVaccineDate(@Param("dateInit") String dateInit,
                        @Param("dateEnd") String dateEnd);

        @Query(value = SELECT_QUERY
                        + " employee where employee.is_deleted = 0 and email = :emailUser", nativeQuery = true)
        Employee getByEmail(@Param("emailUser") String emailUser);

        @Modifying()
        @Transactional
        @Query(value = "UPDATE employee SET is_deleted = 1 WHERE id_employee = :idEmployee", nativeQuery = true)
        public void updateLogicalDelete(@Param("idEmployee") Integer idEmployee);

}
