package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    @Query("SELECT a FROM Artist a WHERE a.alias LIKE %:alias%")
    Artist findByName(@Param("alias") String alias);



}