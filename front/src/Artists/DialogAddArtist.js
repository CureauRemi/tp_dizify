import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import webservice from '../lib/webservice'

export default function DialogAddArtist({ open, handleClose, reload }) {
  const [alias, setAlias] = useState('')
  const [image_artist, setImage_Artist] = useState('')

  const addArtist = async () => {
    if (alias === '') {
      return
    } else if (image_artist === ''){
      setImage_Artist(null)
    }
    
    await webservice.add('/album', {alias: alias, image_artist: image_artist})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouvel artiste</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter un artiste, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="alias" label="Alias" fullWidth onChange={(e) => setAlias(e.target.value)} required />
        <TextField margin="dense" id="image_artist" label="Image" fullWidth onChange={(e) => setImage_Artist(e.target.value)} />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addArtist} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
