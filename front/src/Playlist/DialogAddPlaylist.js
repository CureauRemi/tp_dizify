import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import playlistService from '../lib/playlistService'

export default function DialogAddPlaylist({ open, handleClose, reload }) {
  const [name, setName] = useState('')

  const addPlaylist = async () => {
    if (name === '') {
      return
    }
    
    await playlistService.add({name: name, })
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Nouvelle playlist: </DialogTitle>
      <DialogContent>
        <TextField autoFocus margin="dense" id="name" label="nom :" fullWidth onChange={(e) => setName(e.target.value)} required />
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
