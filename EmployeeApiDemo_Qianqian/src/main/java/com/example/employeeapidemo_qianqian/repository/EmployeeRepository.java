package com.example.employeeapidemo_qianqian.repository;

import com.example.employeeapidemo_qianqian.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select e from Employee e where e.firstName like '%' || :s || '%' or e.lastName like '%' || :s || '%'")
    Collection<Employee> findByFirstNameOrLastName(String s);
}