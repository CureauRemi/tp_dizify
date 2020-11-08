package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    @Query("SELECT a FROM artist a WHERE a.alias LIKE %:alias%")
    public Artist findByName(@Param("alias") String alias);

}