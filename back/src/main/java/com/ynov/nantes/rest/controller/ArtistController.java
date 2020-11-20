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
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private final ArtistRepository artistRepository;
    @Autowired
    private final AlbumRepository albumRepository;


    public ArtistController(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }



    @ResponseBody
    @GetMapping("SearchByName")
    public List<Artist> getArtistsByTitle(@Param("name") String name) {
        List<Artist> artists = artistRepository.findByName(name);
        return artists;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Artist getArtistById(final @PathVariable("id") String artistId) {
        try {
            Optional<Artist>  artist = artistRepository.findById(Integer.valueOf(artistId));
            return artist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @GetMapping
    public Page<Artist> getArtists(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        PageRequest paginationSize = PageRequest.of(page, limit);
        return artistRepository.findAll(paginationSize);
    }
    @ResponseBody
    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @ResponseBody
    @PutMapping
    public Artist editArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public HttpStatus deleteArtist(final @PathVariable("id") Integer artistId) {
        try{
            artistRepository.deleteById(artistId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

    }



}
