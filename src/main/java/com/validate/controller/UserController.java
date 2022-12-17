package com.validate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validate.dto.UserDto;
import com.validate.entity.User;
import com.validate.exception.UserNotFoundException;
import com.validate.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@PostMapping("/register")
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid User user){
		String password  = user.getPassword();
		password = this.passwordEncoder.encode(password);
		user.setPassword(password);
		return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable int id) throws UserNotFoundException{
		return ResponseEntity.ok(userService.getUser(id));
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int id,@RequestBody UserDto userDto) throws UserNotFoundException{
		return ResponseEntity.ok(userService.updateUser(id,userDto));
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException{
		userService.deleteUser(id);
		return ResponseEntity.ok().body("User Deleted");
	} 
	
}
