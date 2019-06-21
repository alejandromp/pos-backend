package com.pos.posbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.posbackend.dao.UserRepository;
import com.pos.posbackend.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	protected UserRepository userRepository;

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	/*@Override
	public void deleteUser(Long usuario) {
		//this.userRepository.delete(id);
		//this.userRepository.deleteById(usuario);
	}*/

	@Override
	public String recuperaPassword(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String autenticaUsuario(String usuario) {
		// TODO Auto-generated method stub
		//this.userRepository.exists(user);
		return this.userRepository.getUsuInfoByUsuario(usuario);
		
		//return false;
	}

}
