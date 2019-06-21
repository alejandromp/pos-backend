package com.pos.posbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pos.posbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings("unchecked")
	User save(User user);
   
    @Query(value="SELECT u.usu_password FROM users u WHERE u.usu_usuario=?1", nativeQuery=true)
    String getUsuInfoByUsuario(String usuario);
}
