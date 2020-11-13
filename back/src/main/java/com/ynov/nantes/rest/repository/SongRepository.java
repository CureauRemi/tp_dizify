package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {

    @Query("SELECT s FROM Song s WHERE s.title LIKE %:title%")
    List<Song> findByTitle(@Param("title") String title);
}
