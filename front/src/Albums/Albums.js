import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import { Add, Delete, Edit } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import DialogAddAlbum from './DialogAddAlbum'
import DialogUpdateAlbum from './DialogUpdateAlbum'
import DialogDeleteAlbum from './DialogDeleteAlbum'
import AlbumService from '../lib/albumService'


export default function Albums() {
  const [loading, setLoading] = useState(true)
  const [albums, setAlbums] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false)
  const [albumDeleted, setAlbumDeleted] = useState({})
  const [openUpdateDialog, setOpenUpdateDialog] = useState(false)
  const [albumUpdate, setAlbumUpdate] = useState({})
  const [openAlert, setOpenAlert] = useState(false)

  useEffect(() => {
    init()
  }, [])

  const init = async () => {

    try {
      let albums = await AlbumService.getAll();
      
      setAlbums(albums)
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
      <Title>Liste des albums</Title>
      {loading ? (
        <CircularProgress />
      ) : (
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>Couverture</TableCell>
              <TableCell>Nom</TableCell>
              {/* <TableCell>Artiste</TableCell> */}
              <TableCell align="right">Année de publication</TableCell>
              <TableCell />
            </TableRow>
          </TableHead>
          <TableBody>
            {albums.map((album) => (
              <TableRow key={album.name}>
                <TableCell><img src={album.image_album} /></TableCell>
                <TableCell>{album.name}</TableCell>
                {/* <TableCell>{album.artist.alias}</TableCell> */}
                <TableCell align="right">{album.release_year}</TableCell>
                <TableCell>
                  <IconButton aria-label="Modifier un album" onClick={() => {
                    setAlbumUpdate(album)
                    setOpenUpdateDialog(true)
                    }}>
                    <Edit />
                  </IconButton>
                  <IconButton aria-label="Supprimer un album" onClick={() => {
                    setAlbumDeleted(album)
                    setOpenDeleteDialog(true)
                    }}>
                    <Delete />
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
        <DialogAddAlbum
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
        <DialogUpdateAlbum
          open={openUpdateDialog}
          album={albumUpdate}
          handleClose={() => setOpenUpdateDialog(false)}
          reload={() => {
            setOpenUpdateDialog(false)
            init()
          }}
        />
      )}
      {/* Delete */}
      {openDeleteDialog && (
        <DialogDeleteAlbum
          open={openDeleteDialog}
          album={albumDeleted}
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
