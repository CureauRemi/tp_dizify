package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.exception.favorite.FavoriteErrorException;
import com.ynov.nantes.rest.exception.favorite.FavoriteNotFoundException;
import com.ynov.nantes.rest.exception.playlist.PlaylistErrorException;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserService userService;

    public Favorite getAllFavoriteByUser() {
        try {
            Utilisateur Currentuser = userService.getCurrentUser();
            return favoriteRepository.getByUser(Currentuser);
        } catch (Exception e) {
            throw new FavoriteNotFoundException(e.getMessage());
        }
    }

    public Favorite addFavoriteSongToUser(Integer songId) {
        try {
            Song songToAdd = songRepository.getById(songId);
            Favorite myFavorite = getAllFavoriteByUser();
            boolean isAlreadyInTheList = false;
            for(Song a: myFavorite.getFavoriteSongs()){
                if (a.getId().equals(songToAdd.getId())) {
                    isAlreadyInTheList = true;
                    break;
                }
            }
            if(!isAlreadyInTheList) {
                Set<Song> myFavoriteSongs = myFavorite.getFavoriteSongs();
                myFavoriteSongs.add(songToAdd);
                myFavorite.setFavoriteSongs(myFavoriteSongs);
                return favoriteRepository.save(myFavorite);
            } else {
                return null;
            }
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public Favorite addFavoriteAlbumToUser(Integer albumId) {
        try {
            Album albumToAdd = albumRepository.getById(albumId);
            Favorite myFavorite = getAllFavoriteByUser();
            boolean isAlreadyInTheList = false;
            for(Album a: myFavorite.getFavoriteAlbums()){
                if (a.getId().equals(albumToAdd.getId())) {
                    isAlreadyInTheList = true;
                    break;
                }
            }
            if(!isAlreadyInTheList) {
                Set<Album> myFavoriteAlbums = myFavorite.getFavoriteAlbums();
                myFavoriteAlbums.add(albumToAdd);
                myFavorite.setFavoriteAlbums(myFavoriteAlbums);
                return favoriteRepository.save(myFavorite);
            } else {
                return null;
            }
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public Favorite addFavoritePlaylistToUser(Integer playlistId) {
        try {
            Playlist playlistToAdd = playlistRepository.getById(playlistId);
            Favorite myFavorite = getAllFavoriteByUser();
            boolean isAlreadyInTheList = false;
            for (Playlist p : myFavorite.getFavoritePlaylists()) {
                if (p.getId().equals(playlistToAdd.getId())) {
                    isAlreadyInTheList = true;
                    break;
                }
            }
            if (!isAlreadyInTheList) {
                Set<Playlist> myFavoritePlaylists = myFavorite.getFavoritePlaylists();
                myFavoritePlaylists.add(playlistToAdd);
                myFavorite.setFavoritePlaylists(myFavoritePlaylists);
                return favoriteRepository.save(myFavorite);
            } else {
                return myFavorite;
            }
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage());
        }
    }




}
