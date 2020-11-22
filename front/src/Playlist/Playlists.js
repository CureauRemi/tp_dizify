import React, { useEffect, useState } from 'react'
import { CircularProgress, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow, Box, Fab } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'
import DialogAddPlaylist from './DialogAddPlaylist'

import Title from '../components/Title'
import PlaylistService from '../lib/playlistService'

export default function Playlist() {
  const [loading, setLoading] = useState(true)
  const [playlists, setPlaylists] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openAlert, setOpenAlert] = useState(false)

  
    useEffect(() => {
      init()
    }, [])
  
    const init = async () => {
  
      try {
        let playlists = await PlaylistService.getAll();
        setPlaylists(playlists)
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
        <Title>Mes Playlists :</Title>
        {loading ? (
          <CircularProgress />
        ) : (
          playlists.map((playlist) => (
          <Table size="small">
            <TableHead>
              <h1>Playlists {playlist.name} :</h1>
              <TableRow>
                <TableCell>Nom de la Musique :</TableCell>
                <TableCell>Durée : </TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
       
            </TableBody>
          </Table>
          )))}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '100%' }}>
        <Fab color="primary" aria-label="add" onClick={() => setOpenAddDialog(true)}>
          <Add />
        </Fab>
      </Box>
      {/* Add */}
      {openAddDialog && (
        <DialogAddPlaylist
          open={openAddDialog}
          handleClose={() => setOpenAddDialog(false)}
          reload={() => {
            setOpenAddDialog(false)
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