package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.dto.artist.ArtistBasicDto;
import com.ynov.nantes.rest.entity.dto.artist.ArtistDto;
import com.ynov.nantes.rest.entity.dto.artist.getArtistDto;
import com.ynov.nantes.rest.exception.artist.ArtistErrorException;
import com.ynov.nantes.rest.exception.artist.ArtistNotFoundException;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;


    public Artist addArtist(Artist artist) {
        try {
            return artistRepository.save(artist);
        } catch (Exception e) {
            throw new ArtistErrorException(e.getMessage());
        }
    }

    public List<Artist> getArtistsByTitle(String name) {
        try {
            return artistRepository.findByName(name);
        } catch(Exception e) {
            throw new ArtistErrorException(e.getMessage());
        }
    }

    public ArtistDto getById(Integer id) {
        try {
             return new ArtistDto(artistRepository.getById(id));
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage() + " : " + e.getCause());
            throw new ArtistNotFoundException();
        }
    }

    public Page<getArtistDto> getAllArtists(Integer page, Integer limit) {
        try {
            PageRequest paginationSize = PageRequest.of(page, limit);
            return artistRepository.findAll(paginationSize).map(getArtistDto::new);

        } catch (Exception e) {
            throw new ArtistErrorException(e.getMessage() + " - " + e.getCause());
        }
    }

    public Artist updateArtist(Artist artist) {
        try {
            return artistRepository.save(artist);
        } catch (Exception e) {
            throw new ArtistErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteArtist(Integer id) {
        try{
            artistRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ArtistErrorException(e.getMessage());
        }
    }



}
