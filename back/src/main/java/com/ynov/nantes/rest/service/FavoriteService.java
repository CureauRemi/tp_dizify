package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.exception.favorite.FavoriteErrorException;
import com.ynov.nantes.rest.exception.favorite.FavoriteNotFoundException;
import com.ynov.nantes.rest.exception.playlist.PlaylistErrorException;
import com.ynov.nantes.rest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    private ArtistRepository artistRepository;
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

    public Favorite addFavoriteArtistToUser(Integer artistId) {
        try {
            Artist artistToAdd = artistRepository.getById(artistId);
            Favorite myFavorite = getAllFavoriteByUser();
            boolean isAlreadyInTheList = false;
            for (Artist p : myFavorite.getFavoriteArtists()) {
                if (p.getId().equals(artistToAdd.getId())) {
                    isAlreadyInTheList = true;
                    break;
                }
            }
            if (!isAlreadyInTheList) {
                Set<Artist> myFavoriteArtists = myFavorite.getFavoriteArtists();
                myFavoriteArtists.add(artistToAdd);
                myFavorite.setFavoriteArtists(myFavoriteArtists);
                return favoriteRepository.save(myFavorite);
            } else {
                return myFavorite;
            }
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteFavorite(Integer Id) {
        try{
            favoriteRepository.deleteById(Id);
            return HttpStatus.OK;
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteFavoriteSongToUser(Integer songId) {
        try {
            Song songToAdd = songRepository.getById(songId);
            Favorite myFavorite = getAllFavoriteByUser();
            for(Song a: myFavorite.getFavoriteSongs()){
                if (a.getId().equals(songToAdd.getId())) {
                    myFavorite.getFavoriteSongs().remove(a);
                    break;
                }
            }
            favoriteRepository.save(myFavorite);
            return HttpStatus.OK;
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteFavoriteAlbumToUser(Integer albumId) {
        try {
            Album albumToAdd = albumRepository.getById(albumId);
            Favorite myFavorite = getAllFavoriteByUser();
            for(Album a: myFavorite.getFavoriteAlbums()){
                if (a.getId().equals(albumToAdd.getId())) {
                    myFavorite.getFavoriteAlbums().remove(a);
                    break;
                }
            }
            favoriteRepository.save(myFavorite);
            return HttpStatus.OK;
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteFavoriteArtistToUser(Integer artistId) {
        try {
            Artist artistToRemove = artistRepository.getById(artistId);
            Favorite myFavorite = getAllFavoriteByUser();
            for (Artist a : myFavorite.getFavoriteArtists()) {
                if (a.getId().equals(artistToRemove.getId())) {
                    myFavorite.getFavoriteArtists().remove(a);
                    break;
                }
            }
            favoriteRepository.save(myFavorite);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage());
        }
    }





}
