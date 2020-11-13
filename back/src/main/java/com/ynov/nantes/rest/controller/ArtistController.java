package com.ynov.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.repository.ArtistRepository;

@RestController
public class ArtistController {

    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;

    @Autowired
    public ArtistController(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }


    @ResponseBody
    @GetMapping("/artist/{id}")
    public Artist getArtistById(final @PathVariable("id") String artistId) {
        try {
            Optional<Artist> artist = artistRepository.findById(Integer.valueOf(artistId));
            return artist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/artists")
    public Page<Artist> getArtists(@Param("page") Integer page, @Param("limit") Integer nb) {
        PageRequest paginationSize = PageRequest.of(page, nb);
        return artistRepository.findAll(paginationSize);
    }

    @GetMapping("/artist/album")
    public List<Album> getArtists(Artist artist) {
        return albumRepository.findByArtist(artist);
    }

    @PostMapping("/artist")
    public Artist addArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @ResponseBody
    @PutMapping("/artist")
    public Artist editArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @DeleteMapping("/artist/{id}")
    public HttpStatus deleteArtist(final @PathVariable("id") Integer artistId) {
        try {
            albumRepository.deleteById(artistId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }



}
