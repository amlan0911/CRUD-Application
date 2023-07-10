package com.example.crud.service;

import com.example.crud.dto.UserDto;
import com.example.crud.entity.Department;
import com.example.crud.entity.Employee;
import com.example.crud.entity.Role;
import com.example.crud.entity.User;

import java.util.List;

public interface UserService {
    List<Employee> getAllEmployees();
    List<Department> getAllDepartment();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);


    // save new user to database
    void saveUser(UserDto userDto);

    //for login check
    User findUserByEmail(String email);

    //to show all registered users
    List<UserDto> findAllUsers();






}