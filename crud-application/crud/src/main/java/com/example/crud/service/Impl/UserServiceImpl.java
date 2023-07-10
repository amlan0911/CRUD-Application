package com.example.crud.service.Impl;

import com.example.crud.dto.UserDto;
import com.example.crud.entity.Department;
import com.example.crud.entity.Employee;
import com.example.crud.entity.Role;
import com.example.crud.entity.User;
import com.example.crud.repository.DepartmentRepository;
import com.example.crud.repository.EmployeeRepository;
//import com.example.crud.repository.LoginRepository;
import com.example.crud.repository.RoleRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository,UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        super();
        this.employeeRepository = employeeRepository;
        this.departmentRepository=departmentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }
    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).get();
    }
    @Override
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }








        @Override
        public void saveUser(UserDto userDto) {
            User user = new User();
            user.setName(userDto.getFirstName() + " " + userDto.getLastName());
            user.setEmail(userDto.getEmail());
            // encrypt the password using spring security
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            Role role = roleRepository.findByName("ROLE_USER");
            if(role == null){
                role = checkRoleExist();
            }
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
        }

        @Override
        public User findUserByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        @Override
        public List<UserDto> findAllUsers() {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map((user) -> mapToUserDto(user))
                    .collect(Collectors.toList());
        }


        private UserDto mapToUserDto(User user){
            UserDto userDto = new UserDto();
            String[] str = user.getName().split(" ");
            userDto.setFirstName(str[0]);
            userDto.setLastName(str[1]);
            userDto.setEmail(user.getEmail());
            return userDto;
        }


        private Role checkRoleExist(){
            Role role = new Role();
            role.setName("ROLE_USER");
            return roleRepository.save(role);
        }






}