package com.ynov.nantes.rest.repository;

import java.util.List;

import com.ynov.nantes.rest.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT a FROM album a WHERE a.name LIKE %:name%")
    public List<Album> findByName(@Param("name") String name);

}