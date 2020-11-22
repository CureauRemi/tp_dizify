package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.song.AddSongDto;
import com.ynov.nantes.rest.entity.dto.song.SongDto;
import com.ynov.nantes.rest.exception.song.SongErrorException;
import com.ynov.nantes.rest.exception.song.SongNotFoundException;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.repository.ArtistRepository;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;



    public List<SongDto> getSongByTitle(String name) {
        try {
            List<Song> songsFound =  songRepository.findByTitle(name);
            List<SongDto> songsToReturn = new ArrayList<>();
            for(Song s: songsFound) {
                SongDto song = new SongDto(s);
                songsToReturn.add(song);
            }
            return songsToReturn;

        } catch (Exception e) {
            throw new SongErrorException(e.getMessage());
        }
    }

    public Page<SongDto> getAllSongs(Integer page, Integer limit) {
        try {
            PageRequest paginationSize = PageRequest.of(page, limit);
            return songRepository.findAll(paginationSize).map(SongDto::new);
        } catch (Exception e) {
            throw new SongErrorException(e.getMessage());
        }
    }

    public SongDto getSongById(Integer id) {
        try {
            return new SongDto(songRepository.getById(id));
        } catch (Exception e) {
            throw  new SongNotFoundException();
        }
    }

    public Song addSong(AddSongDto song) {
        try {
            Song newSong = new Song();
            Album albumToInsert = albumRepository.getByName(song.getAlbum_name());
            Artist artistToInsert = artistRepository.findOneByName(song.getArtist_name());
            System.out.println(albumToInsert);
            System.out.println(artistToInsert);
            if(albumToInsert != null && artistToInsert != null) {
                newSong.setAlbum(albumToInsert);
                newSong.setArtist(artistToInsert);
                newSong.setTitle(song.getTitle());
                newSong.setDuration(song.getDuration());
                newSong.setImage_song(song.getImage_song());
                Song inserted = songRepository.save(newSong);
                List<Song> songsInAlbum = albumToInsert.getSongs();
                songsInAlbum.add(inserted);
                albumToInsert.setSongs(songsInAlbum);
                albumRepository.save(albumToInsert);
                return inserted;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw  new SongErrorException(e.getMessage());
        }
    }

    public Song updateSong(Song song) {
        try {
            return songRepository.save(song);
        } catch (Exception e) {
            throw  new SongNotFoundException();
        }
    }

    public HttpStatus deleteSong(Integer id) {
        try {
            songRepository.deleteById(id);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
