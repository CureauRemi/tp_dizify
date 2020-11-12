package com.ynov.nantes.rest.repository;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("SELECT s FROM Song s WHERE s.title LIKE %:title%")
    public List<Song> findByTitle(@Param("title") String title);
}
