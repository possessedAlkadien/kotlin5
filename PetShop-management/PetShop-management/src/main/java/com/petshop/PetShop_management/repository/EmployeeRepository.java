package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastnameContainingIgnoreCase(String lastname);

    List<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);

    List<Employee> findByPhoneNumber(String phoneNumber);

    @Query("SELECT e FROM Employee e WHERE e.firstname LIKE %:firstname% AND e.lastname LIKE %:lastname%")
    List<Employee> findByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);

    @Query("SELECT DISTINCT e FROM Employee e JOIN e.order o")
    List<Employee> findEmployeesWithOrders();
}
