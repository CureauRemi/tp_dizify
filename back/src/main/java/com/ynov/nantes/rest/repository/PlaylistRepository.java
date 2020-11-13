package com.ynov.nantes.rest.repository;


import com.ynov.nantes.rest.entity.Playlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Integer> {

    @Query("SELECT p FROM Playlist p WHERE p.name LIKE %:name%")
    Optional<Playlist> findByName(@Param("name") String name);

}