package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Artist;
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
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;


    public Artist addArtist(ArtistDto artist) {
        try {
            Artist artistToAdd = new Artist();
            artistToAdd.setAlias(artist.getAlias());
            artistToAdd.setImage_artist(artist.getImage_artist());
            artistToAdd.setAlbums(null);
            return artistRepository.save(artistToAdd);
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

    public Artist getById(Integer id) {
        try {
            Optional<Artist> artist = artistRepository.findById(Integer.valueOf(id));
            return artist.get();
        } catch (Exception e) {
            throw new ArtistNotFoundException();
        }
    }

    public Page<getArtistDto> getAllArtists(Integer page, Integer limit) {
        try {
            PageRequest paginationSize = PageRequest.of(page, limit);
            return artistRepository.findAll(paginationSize).map(artist -> new getArtistDto(artist));

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
