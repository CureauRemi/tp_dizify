package com.ynov.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/artist")
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/artist/{id}/album")
    public List<Album> getArtists(Artist artist) {

        return albumRepository.findByArtist(artist);
    }

    @PostMapping("/artist")
    public Artist addArtist(@RequestBody Artist artist) {
        Artist saved = artistRepository.save(artist);
        return saved;
    }

    @ResponseBody
    @PutMapping("/artist/{id}")
    public Artist editArtist(@RequestBody Artist artist) {
        Artist updated = artistRepository.save(artist);
        return updated;
    }

    @DeleteMapping("/artist")
    public String deleteArtist(@RequestBody Artist artist) {
        try{
            artistRepository.delete(artist);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Artist deleted successfully !";
    }



}
