package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("SELECT u FROM Utilisateur u WHERE u.pseudo LIKE %:pseudo%")
    Utilisateur findByPseudo(@Param("pseudo") String pseudo);
    
    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email AND u.password = :password")
    Utilisateur findByEmailAndPassWord(@Param("email") String email, @Param("password") String password);

    Utilisateur findByEmail(String email);

}
