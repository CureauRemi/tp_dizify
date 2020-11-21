package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.song.SongDto;
import com.ynov.nantes.rest.exception.song.SongErrorException;
import com.ynov.nantes.rest.exception.song.SongNotFoundException;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    public List<SongDto> getSongByTitle(String name) {
        try {
            List<Song> songsFound =  songRepository.findByTitle(name);
            List<SongDto> songsToReturn = new ArrayList<>();
            for(Song s: songsFound) {
                SongDto song = new SongDto(s);
                songsToReturn.add(song);
            }
            return songsToReturn;

        } catch (Exception e) {
            throw new SongErrorException(e.getMessage());
        }
    }

    public Page<SongDto> getAllSongs(Integer page, Integer limit) {
        try {
            PageRequest paginationSize = PageRequest.of(page, limit);
            return songRepository.findAll(paginationSize).map(song -> new SongDto(song));
        } catch (Exception e) {
            throw new SongErrorException(e.getMessage());
        }
    }

    public SongDto getSongById(Integer id) {
        try {
            return new SongDto(songRepository.getById(id));
        } catch (Exception e) {
            throw  new SongNotFoundException();
        }
    }

    public Song addSong(Song song) {
        try {
            return songRepository.save(song);
        } catch (Exception e) {
            throw  new SongNotFoundException();
        }
    }

    public Song updateSong(Song song) {
        try {
            return songRepository.save(song);
        } catch (Exception e) {
            throw  new SongNotFoundException();
        }
    }

    public HttpStatus deleteSong(Integer id) {
        try {
            songRepository.deleteById(id);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
