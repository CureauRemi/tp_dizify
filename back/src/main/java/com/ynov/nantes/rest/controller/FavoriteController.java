package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.service.FavoriteService;
import com.ynov.nantes.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;
    @Autowired
    UserService userService;



    @ResponseBody
    @GetMapping
    public Favorite getFavoriteByUserId() {
        return favoriteService.getAllFavoriteByUser();
    }

//    @ResponseBody
//    @PostMapping("add/song")
//    public Favorite addFavoriteSong(@Param("idSong") Integer idSong) {
//        Utilisateur user =  userService.getCurrentUser();
//        Favorite favToUpdate = favoriteService.addFavoriteToUser();
//        Set<Song> songs  = favToUpdate.getFavoriteSongs();
//        Boolean isAlreadyInTheList = false;
//        for(Song s : songs) {
//            if(s.getId()  == idSong && !isAlreadyInTheList) {
//                isAlreadyInTheList = true;
//            }
//        }
//        if(!isAlreadyInTheList) {
//            Optional<Song> songFound = songRepository.findById(idSong);
//            Song songToAdd = songFound.get();
//            songs.add(songToAdd);
//            favToUpdate.setFavoriteSongs(songs);
//        }
//
//        Favorite saved = favoriteRepository.save(favToUpdate);
//        return saved;
//    }

    @ResponseBody
    @PostMapping("add/album")
    public Favorite addFavoriteAlbum(@RequestParam("id") Integer albumId) {
        return favoriteService.addFavoriteAlbumToUser(albumId);
    }
//
//
//    @ResponseBody
//    @PostMapping("add/playlist")
//    public Favorite addFavoriteArtist(@RequestBody Playlist playlist) {
//        Utilisateur user =  userService.getCurrentUser();
//        Favorite favToUpdate = favoriteRepository.findByUserId(user.getId());
//        Set<Playlist> playlists  = favToUpdate.getFavoritePlaylists();
//        Boolean isAlreadyInTheList = false;
//        for(Playlist p : playlists) {
//            if(p.getId()  == playlist.getId() && !isAlreadyInTheList) {
//                isAlreadyInTheList = true;
//            }
//        }
//        if(!isAlreadyInTheList) {
//            Optional<Playlist> playlistFound = playlistRepository.findById(playlist.getId());
//            Playlist playlistToAdd = playlistFound.get();
//            playlists.add(playlistToAdd);
//            favToUpdate.setFavoritePlaylists(playlists);
//        }
//
//        Favorite saved = favoriteRepository.save(favToUpdate);
//        return saved;
//    }
//
//
//    @ResponseBody
//    @PutMapping("{id}")
//    public Favorite updateAlbum(@RequestBody Favorite favoriteToUpdate) {
//        Favorite updated = favoriteRepository.save(favoriteToUpdate);
//        return updated;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping("{id}")
//    public HttpStatus deleteAlbum(@PathVariable("id") Favorite favorite) {
//        try{
//            favoriteRepository.deleteById(favorite.getId());
//            return HttpStatus.OK;
//        } catch (Exception e) {
//            return HttpStatus.BAD_REQUEST;
//        }
//    }
}
