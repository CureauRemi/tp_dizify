package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("SELECT u FROM User u WHERE u.pseudo LIKE %:pseudo%")
    User findByPseudo(@Param("pseudo") String pseudo);
    
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassWord(@Param("email") String email, @Param("password") String password);
}
