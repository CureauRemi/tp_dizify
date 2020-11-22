import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow, Grid, Typography } from '@material-ui/core'
import { Add, Delete, Edit, Star } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'
import { useParams, Link } from "react-router-dom";

import Title from '../components/Title'
import DialogAddSong from './DialogAddSong'
import DialogUpdateSong from './DialogUpdateSong'
import DialogDeleteSong from './DialogDeleteSong'
import AlbumService from '../lib/albumService'
import favoryService from '../lib/favoryService'


export default function Album() {
  const [loading, setLoading] = useState(true)
  const [artist, setArtist] = useState({})
  const [album, setAlbum] = useState({})
  const [songs, setSongs] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false)
  const [songDeleted, setSongDeleted] = useState({})
  const [openUpdateDialog, setOpenUpdateDialog] = useState(false)
  const [songUpdate, setSongUpdate] = useState({})
  const [openAlert, setOpenAlert] = useState(false)
  let { id } = useParams();

  useEffect(() => {
    init()
  }, [])

  const init = async () => {

    try {
      let album = await AlbumService.getOne(id);
      setArtist(album.artist)
      setAlbum(album)
      setSongs(album.songs)
      setTimeout(function () {
        setLoading(false)
      }, 1500)
    } catch (error) {
      setLoading(false)
      setOpenAlert(true)
    }
  }

  return (
    <>
      <Grid container>
        <Grid item>
          {album.image_album != null &&
            <img
                src={process.env.PUBLIC_URL + '/img/'+ album.image_album}
                alt={album.name}
                style={{ height: '200px', width: '200px', 'border-radius': '100px', margin: '5px auto 40px auto'}}
              />
          }
          {album.image_album == null &&
            <img
                src={process.env.PUBLIC_URL + '/img/default_album.jpg'}
                alt={album.name}
                style={{ height: '200px', width: '200px', 'border-radius': '100px', margin: '5px auto 40px auto'}}
              />
          }
        </Grid>
        <Grid item style={{padding: '0 0 0 15px'}}>
          <div>
            <Typography component="h1" variant="h4" color="black" gutterBottom>{album.name}</Typography>
            <Typography component="h1" variant="h6" color="black" gutterBottom component={Link} to={"/artist/"+artist.id}>{artist.alias}</Typography>
            <Typography component="h1" color="black" gutterBottom>{album.release_year}</Typography>
          </div>
        </Grid>
      </Grid>
      <Title>Liste des Chansons</Title>
      {loading ? (
        <CircularProgress />
      ) : (
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>Nom</TableCell>
              <TableCell>durée</TableCell>
              <TableCell />
            </TableRow>
          </TableHead>
          <TableBody>
            {songs.map((song) => (
              <TableRow key={song.title}>
                <TableCell>{song.title}</TableCell>
                <TableCell>{song.duration}</TableCell>
                <TableCell>
                  <IconButton aria-label="Modifier un song" onClick={() => {
                    setSongUpdate(song)
                    setOpenUpdateDialog(true)
                    }}>
                    <Edit />
                  </IconButton>
                  <IconButton aria-label="Supprimer un song" onClick={() => {
                    console.log(song)
                    setSongDeleted(song)
                    setOpenDeleteDialog(true)
                    }}>
                    <Delete />
                  </IconButton>
                  <IconButton aria-label="ajout au favori" onClick={() => {favoryService.addFavorite("song", song.id)}}>
                    <Star />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      )}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '100%' }}>
        <Fab color="primary" aria-label="add" onClick={() => setOpenAddDialog(true)}>
          <Add />
        </Fab>
      </Box>
      {/* Add */}
      {openAddDialog && (
        <DialogAddSong
          open={openAddDialog}
          handleClose={() => setOpenAddDialog(false)}
          reload={() => {
            setOpenAddDialog(false)
            init()
          }}
        />
      )}
      {/* Update */}
      {openUpdateDialog && (
        <DialogUpdateSong
          open={openUpdateDialog}
          song={songUpdate}
          handleClose={() => setOpenUpdateDialog(false)}
          reload={() => {
            setOpenUpdateDialog(false)
            init()
          }}
        />
      )}
      {/* Delete */}
      {openDeleteDialog && (
        <DialogDeleteSong
          open={openDeleteDialog}
          song={songDeleted}
          handleClose={() => setOpenDeleteDialog(false)}
          reload={() => {
            setOpenDeleteDialog(false)
            init()
          }}
        />
      )}
      <Snackbar open={openAlert} autoHideDuration={5000} onClose={() => setOpenAlert(false)}>
        <Alert onClose={() => setOpenAlert(false)} severity="error">
          Une erreur réseau est survenue
        </Alert>
      </Snackbar>
    </>
  )
}
