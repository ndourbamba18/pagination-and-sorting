package com.saraya.repository;

import com.saraya.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // PAGING AND SORTING DATA
    Page<Employee> findByGender(Character gender, Pageable pageable);
    Page<Employee> findByActive(boolean active, Pageable pageable);
    Page<Employee> findByNameContaining(String name, Pageable pageable);

    List<Employee> findByNameContaining(String name, Sort sort);

    // VALIDATION DATA
    Optional<Employee> findByEmailContaining(String email);
    Optional<Employee> findByPhoneContaining(String phone);

    // CHECK DATA
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    // CUSTOM QUERY
    @Query(value = "select * from employee e where e.name like %:keyword% or e.email like %:keyword% " +
            "or e.phone like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);
}
