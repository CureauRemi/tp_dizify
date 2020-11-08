package com.ynov.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.repository.ArtistRepository;

@RestController
public class ArtistController {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
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
