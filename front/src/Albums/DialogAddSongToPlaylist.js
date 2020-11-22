import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import SongService from '../lib/songService'
import PlaylistService from '../lib/playlistService'

export default function DialogAddSongToPlaylist({ open, handleClose, reload }) {

  const addPlaylist = async () => {

  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter le son dans la playlist :</DialogTitle>
      <DialogContent>
  
      
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addPlaylist} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
