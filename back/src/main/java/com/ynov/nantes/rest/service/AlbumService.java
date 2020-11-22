package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.dto.album.*;
import com.ynov.nantes.rest.entity.mapper.AlbumMapper;
import com.ynov.nantes.rest.exception.album.AlbumErrorException;
import com.ynov.nantes.rest.exception.album.AlbumNotFoundException;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumMapper albumMapper;


    public Album addAlbum(AddAlbumDto newAlbum) {
        try{
            Album albumToAdd = new Album();
            Artist artistToInsert = artistRepository.findOneByName(newAlbum.getArtist_name());
            if(artistToInsert != null) {
                albumToAdd.setName(newAlbum.getName());
                albumToAdd.setRelease_year(newAlbum.getRelease_year());
                albumToAdd.setImage_album(newAlbum.getImage_album());
                albumToAdd.setArtist(artistToInsert);

                Album inserted = albumRepository.save(albumToAdd);
                Set<Album> toInsert = new HashSet<>();
                toInsert.add(inserted);
                artistToInsert.setAlbums(toInsert);
                artistRepository.save(artistToInsert);
                return inserted;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new AlbumErrorException("There was an error during the creation of the album");
        }
    }

    public AlbumDto updateAlbum(UpdateAlbumDto updateAlbum) {
        Artist artistFound = artistRepository.findOneByName(updateAlbum.getArtist_name());
        //Artist
        if (true) {
        } else {
            throw new AlbumErrorException("There was an error during the creation of the album");
        }
        return null;
    }

    public Set<Album> getAlbumByArtist(Integer artistId){
        try {
            Artist artistByIdFound = artistRepository.getById(artistId);
            if(artistByIdFound != null) {
                return artistByIdFound.getAlbums();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new AlbumErrorException(e.getMessage());
        }
    }

    public List<Album> getAlbumsByTitle(String name) {
        return albumRepository.findByName(name);
    }

    public Page<getAlbumDto> getAllAlbums(Integer page, Integer limit) {
        PageRequest paginationSize = PageRequest.of(page, limit);
        return albumRepository.findAll(paginationSize).map(getAlbumDto::new);
    }

    public AlbumNonBasicDto getById(Integer id) {
        try {
            return new AlbumNonBasicDto(albumRepository.getById(id));
        } catch (Exception e) {
            throw new AlbumErrorException(e.getMessage());
        }
    }

    public HttpStatus deleteAlbum(Integer id) {
        try {
            albumRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new AlbumErrorException(e.getMessage());
        }

    }


}
