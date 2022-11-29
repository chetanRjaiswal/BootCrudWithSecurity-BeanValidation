package com.validate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validate.dto.UserDto;
import com.validate.entity.User;
import com.validate.exception.UserNotFoundException;
import com.validate.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDto saveUser(User user) {
		User savedUser = userRepository.save(user);
		return convertUsertoUserDto(savedUser);
	}
	
	public List<UserDto> getAllUsers(){
		List<User>users = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(User user : users ) {
	      UserDto userDto = new UserDto();
	      userDto.setName(user.getName());
	      userDto.setEmail(user.getEmail());
	      userDto.setMobile(user.getMobile());
	      userDto.setGender(user.getGender());
	      userDto.setAge(user.getAge());
	      userDto.setNationality(user.getNationality());
	      userDtoList.add(userDto);
		}
		return userDtoList;
	}
	
	public UserDto getUser(int id) throws UserNotFoundException {
		User user=userRepository.findByUserId(id);
		if(user!=null) {
			return convertUsertoUserDto(user);
		}
		else {
			throw new UserNotFoundException("user not found with id:"+id);
		}
	}

	public UserDto updateUser(int id,UserDto userDto) throws UserNotFoundException {
		
		User dbUser = userRepository.findByUserId(id);
		
		if(dbUser!=null)
		{
			if (Objects.nonNull(userDto.getName()) && !"".equalsIgnoreCase(userDto.getName())) {
				dbUser.setName(userDto.getName());
			}
	
			if (Objects.nonNull(userDto.getEmail())
					&& !"".equalsIgnoreCase(userDto.getEmail())) {
				dbUser.setEmail(userDto.getEmail());
			}
	
			if (Objects.nonNull(userDto.getMobile()) && !"".equalsIgnoreCase(userDto.getMobile())) {
				dbUser.setMobile(userDto.getMobile());
			}
			
			if (Objects.nonNull(userDto.getGender()) && !"".equalsIgnoreCase(userDto.getGender())) {
				dbUser.setGender(userDto.getGender());
			}
			
			if (Objects.nonNull(userDto.getAge())) {
				dbUser.setAge(userDto.getAge());
			}
			
			if (Objects.nonNull(userDto.getNationality()) && !"".equalsIgnoreCase(userDto.getNationality())) {
				dbUser.setNationality(userDto.getNationality());
			}
			
			User usertoDb = userRepository.save(dbUser);
			return convertUsertoUserDto(usertoDb);	
		}
		
		else {
			throw new UserNotFoundException("user not found with id:"+id);
		}
		
	}
	
	public static UserDto  convertUsertoUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setMobile(user.getMobile());
		userDto.setGender(user.getGender());
		userDto.setAge(user.getAge());
		userDto.setNationality(user.getNationality());
		
		return userDto;
	}

	public void deleteUser(int id) throws UserNotFoundException {
		User user = userRepository.findByUserId(id);
		if(user!=null)
		{
		  userRepository.deleteById(id);
		}
		else {
			throw new UserNotFoundException("user not found with id:"+id);
		}
	}

	
}
