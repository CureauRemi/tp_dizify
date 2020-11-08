package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f FROM Favorite f WHERE f.userId = %:userId%")
    public Optional<Favorite> findByUserId(@Param("id") Integer userId);
}
