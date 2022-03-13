package com.example.springsecurityormauth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsecurityormauth.model.Employee;
import com.example.springsecurityormauth.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
	private EmployeeRepository userRepository;
    
	@Override
	public Long saveUser(Employee user) {
		// TODO Auto-generated method stub
		
		 user = userRepository.save(user);
		
		return user.getUserId();
	}

}
