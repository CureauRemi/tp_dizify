package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final SongRepository songRepository;


    @Autowired
    public FavoriteController(FavoriteRepository favoriteRepository, SongRepository songRepository) {
        this.favoriteRepository = favoriteRepository;
        this.songRepository = songRepository;
    }

    @ResponseBody
    @GetMapping("/user/{id}/favorite")
    public Favorite getFavoriteByUserId(final @PathVariable("id") String favoriteId) {
        try {
            return favoriteRepository.findByUserId(Integer.valueOf(favoriteId));
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/user/{userid}/favorite/song/{id}")
    public Favorite addFavoriteSong(@PathVariable("userid") Integer userId, @PathVariable("id") Integer idSong) {
        Favorite favToUpdate = favoriteRepository.findByUserId(userId);
        Set<Song> songs  = favToUpdate.getFavoriteSongs();
        Boolean isAlreadyInTheList = false;
        for(Song s : songs) {
            if(s.getId()  == idSong && !isAlreadyInTheList) {
                isAlreadyInTheList = true;
            }
        }
        if(!isAlreadyInTheList) {
            Optional<Song> song = songRepository.findById(idSong);
            Song songToAdd = song.get();
            songs.add(songToAdd);
            favToUpdate.setFavoriteSongs(songs);
        }

        Favorite saved = favoriteRepository.save(favToUpdate);
        return saved;
    }

    @PostMapping("/user/{id}/favorite/album")
    public Favorite addFavoriteAlbum(@RequestBody Favorite favorite) {
        Favorite saved = favoriteRepository.save(favorite);
        return saved;
    }

    @PostMapping("/user/{id}/favorite/artist")
    public Favorite addFavoriteArtist(@RequestBody Favorite favorite) {
        Favorite saved = favoriteRepository.save(favorite);
        return saved;
    }

    @PutMapping("/user/{id}/favorite")
    public Favorite updateAlbum(final @PathVariable("id") String albumId, @RequestBody Favorite album) {
        Favorite toUpdate = favoriteRepository.getOne(Integer.valueOf(albumId));
        if(toUpdate.getId() == Integer.valueOf(albumId)) {
            toUpdate = favoriteRepository.save(album);
        }
        return toUpdate;
    }

    @DeleteMapping("/user/{id}/favorite")
    public String deleteAlbum(@RequestBody Favorite favorite) {
        try{
            favoriteRepository.delete(favorite);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Favorite Deleted Successfully !";
    }
}
