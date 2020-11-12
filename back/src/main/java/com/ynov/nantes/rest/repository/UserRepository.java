package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.pseudo LIKE %:pseudo%")
    public User findByPseudo(@Param("pseudo") String pseudo);
}
