import React, { useEffect, useState } from 'react'
import { CircularProgress, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import {  Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import favoryService from '../lib/favoryService'

export default function Favory() {
    const [loading, setLoading] = useState(true)
    const [favoritesAlbums, setFavoritesAlbums] = useState([])
    const [favoritesSongs, setFavoritesSongs] = useState([])
    const [favoritesArtists, setFavoritesArtists] = useState([])
    const [openAlert, setOpenAlert] = useState(false)
  
    useEffect(() => {
      init()
    }, [])
  
    const init = async () => {
  
      try {
        let favorites = await favoryService.getAll();
        if (favorites.favoriteArtists != null) {
          setFavoritesArtists(favorites.favoriteArtists);
        } else {
          setFavoritesArtists([]);
        }
        if(favorites.favoriteAlbums != null) {
          setFavoritesAlbums(favorites.favoriteAlbums)
        } else {
          setFavoritesAlbums([])
        }
        if(favorites.favoriteSongs != null) {
          setFavoritesSongs(favorites.favoriteSongs)
        } else {
          setFavoritesSongs([])
        }

        setTimeout(function () {
          setLoading(false)
        }, 1500)
      } catch (error) {
        setLoading(false)
        setOpenAlert(true)
      }
    }
    // const deleteFavory = async (favory) => {
    //   await favoryService.deleteFavorite(favory.id)
    //   reload()
    // }
    return (
      <>
        <Title>Liste des favoris</Title>
        {loading ? (
          <CircularProgress />
        ) : (
          <Table size="small">
            <TableHead>
              <h1>Album :</h1>
              <TableRow>
                <TableCell>Nom de l'album :</TableCell>
                <TableCell>Date de sortie : </TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritesAlbums.map((favory) => (
                <TableRow key={favory.name}>
                  <TableCell>{favory.name}</TableCell>
                  <TableCell>{favory.release_year}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={async() => {
                      await favoryService.deleteFavorite("album",favory.id)
                      init()}}>
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
          
        )}
        {loading ? (
          <CircularProgress />
        ) : (
        <Table size="small">
            <TableHead>
              <h1>Songs :</h1>
              <TableRow>
                <TableCell>Nom</TableCell>
                <TableCell>Durée</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritesSongs.map((favory) => (
                <TableRow key={favory.title}>
                  <TableCell>{favory.title}</TableCell>
                  <TableCell>{favory.duration}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={async() =>  { 
                      await favoryService.deleteFavorite("song",favory.id) 
                      init()}}>
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
        {loading ? (
          <CircularProgress />
        ) : (
          <Table size="small">
            <TableHead>
              <h1>Artistes :</h1>
              <TableRow>
                <TableCell>Alias</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritesArtists.map((favory) => (
                <TableRow key={favory.name}>
                  <TableCell>{favory.alias}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={async() => { 
                      await favoryService.deleteFavorite("artist",favory.id) 
                      init()}}>
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
        <Snackbar open={openAlert} autoHideDuration={5000} onClose={() => setOpenAlert(false)}>
          <Alert onClose={() => setOpenAlert(false)} severity="error">
            Une erreur réseau est survenue
          </Alert>
        </Snackbar>
      </>
    )
  }