import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle } from '@material-ui/core'
import artistService from '../lib/artistService'

export default function DialogUpdateArtist({ open, handleClose, reload, artist }) {
  const [alias, setAlias] = useState('');
  const [image_artist, setImage_Artist] = useState('');


  const updateArtist = async () => {
    if (alias === '') {
      return
    } else if (image_artist === ''){
      setImage_Artist(null)
    }
    
    await artistService.update({id: artist.id, alias: alias, image_artist: image_artist})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Modifier un nouvel artiste</DialogTitle>
      <DialogContent>
        <TextField autoFocus id="alias_edit" label="Alias" defaultValue={artist.alias} fullWidth onChange={(e) => setAlias(e.target.value)} required></TextField>
        <TextField id="image_artist_edit" label="Image" defaultValue={artist.image_artist} fullWidth onChange={(e) => setImage_Artist(e.target.value)}></TextField>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={updateArtist} color="primary">
          Modifier
        </Button>
      </DialogActions>
    </Dialog>
  )
}
