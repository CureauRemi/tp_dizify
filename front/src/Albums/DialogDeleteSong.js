import React from 'react'
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import SongService from '../lib/songService'

export default function DialogDeleteSong({ open, handleClose, reload, song}) {

  const deleteSong = async () => {
    await SongService.delete(song.id)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Suppression d'une Chanson</DialogTitle>
      <DialogContent>
        <DialogContentText>Voulez-vous vraiment supprimer {song.title} ?</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Non
        </Button>
        <Button variant="outlined" onClick={deleteSong} color="primary">
          Oui
        </Button>
      </DialogActions>
    </Dialog>
  )
}
