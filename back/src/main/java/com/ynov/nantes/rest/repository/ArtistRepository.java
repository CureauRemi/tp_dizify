package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    @Query("SELECT a FROM Artist a WHERE a.alias LIKE %:alias%")
    List<Artist> findByName(@Param("alias") String alias);

    Artist getById(Integer Id);

    @Query("SELECT a FROM Artist a WHERE a.alias LIKE %:alias%")
    Artist findOneByName(@Param("alias") String alias);

}