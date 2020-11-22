import React, { useEffect, useState } from 'react'
import { CircularProgress, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
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
        let favoritesAlbums = favorites.favoriteAlbums;
        let favoritesSongs = favorites.favoriteSongs;
        let favoritesArtists = favorites.favoriteArtists;
        console.log(favoritesSongs)
       
        setFavoritesAlbums(favoritesAlbums)
        setFavoritesSongs(favoritesSongs)
        setFavoritesArtists(favoritesArtists)

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
                    <IconButton aria-label="Supprimer un favori" onClick={() => favoryService.deleteFavorite("album",favory.id)}>
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
                <TableCell>Artiste</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritesSongs.map((favory) => (
                <TableRow key={favory.title}>
                  <TableCell>{favory.title}</TableCell>
                  <TableCell>{favory.duration}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={() =>favoryService.deleteFavorite("song",favory.id)}>
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
                <TableCell>Nom</TableCell>
                <TableCell>Artiste</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritesArtists.map((favory) => (
                <TableRow key={favory.name}>
                  <TableCell>{favory.name}</TableCell>
                  <TableCell>{favory.artist}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={() => favoryService.deleteFavorite("artist",favory.id)}>
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