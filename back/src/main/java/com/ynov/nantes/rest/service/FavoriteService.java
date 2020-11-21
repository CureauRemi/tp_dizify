package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.exception.favorite.FavoriteErrorException;
import com.ynov.nantes.rest.exception.favorite.FavoriteNotFoundException;
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

    public Favorite addFavoriteToUser() {
        try {
            return null;
        } catch(Exception e) {
            throw new FavoriteErrorException(e.getMessage());
        }
    }

    public Favorite addFavoriteAlbumToUser(Integer albumId) {
        try {
            Album albumToAdd = albumRepository.getById(albumId);
            Favorite myFavorite = getAllFavoriteByUser();
            Boolean isAlreadyInTheList = false;
            for(Album a: myFavorite.getFavoriteAlbums()){
                if(a.getId() == albumToAdd.getId()) {
                    isAlreadyInTheList = true;
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



}
