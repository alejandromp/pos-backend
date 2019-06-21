package com.pos.posbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.posbackend.model.User;
import com.pos.posbackend.service.UserService;
import com.pos.posbackend.util.RestResponse;

@RestController
public class UserController {
	@Autowired
    protected UserService userService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String userJson) 
			throws JsonParseException, JsonMappingException, IOException {
		
		this.mapper = new ObjectMapper();
		User user = this.mapper.readValue(userJson, User.class);
		
		if(!this.validate(user)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					"Los campos obligatorios no fueron diligenciados");
		}
		
		this.userService.save(user);
		
		return new RestResponse(HttpStatus.OK.value(), "Operación exitosa");
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsers() {
		return this.userService.findAll();
	}
	
	@RequestMapping(value = "/autenticaUsuario", method = RequestMethod.POST)
	public RestResponse autenticarUsuario(@RequestBody String autenticaUsuarioJson) throws Exception  {
		this.mapper = new ObjectMapper();
		User user = this.mapper.readValue(autenticaUsuarioJson, User.class);
		
	
		/**
		 * Decodificar el password de usuario recuperado ó codificar el enviado
		 * luego hacer la comparación 
		 */
		
		if(StringUtils.trimToNull(user.getPassword()).equals(this.userService.autenticaUsuario(user.getUsuario()))) {
			//return true;
			return new RestResponse(HttpStatus.OK.value(), "Operación exitosa");
		}else {
			//return false;
			return new RestResponse(HttpStatus.BAD_REQUEST.value(), "Error");
		}
	}
	
	private boolean validate(User user) {
		boolean isValid = true;
		
		if(StringUtils.trimToNull(user.getUsuario()) == null) {
			isValid = false;
		}
		
		if(StringUtils.trimToNull(user.getPassword()) == null) {
			isValid = false;
		}
		
		return isValid;
	}
	

}

