import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import webservice from '../lib/webservice'

export default function DialogUpdateArtist({ open, handleClose, reload, artist }) {
  const [alias, setAlias] = useState('')
  const [image_artist, setImage_Artist] = useState('')

  const addArtist = async () => {
    if (alias === '') {
      return
    } else if (image_artist === ''){
      setImage_Artist(null)
    }
    
    await webservice.addArtist({id: artist.id, alias: alias, image_artist: image_artist})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Modifier un nouvel artiste</DialogTitle>
      <DialogContent>
        <TextField autoFocus id="alias_edit" label="Alias" value={artist.alias} fullWidth onChange={(e) => setAlias(e.target.value)} required/>
        <TextField id="image_artist_edit" label="Image" value={artist.image_artist} fullWidth onChange={(e) => setImage_Artist(e.target.value)}/>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addArtist} color="primary">
          Modifier
        </Button>
      </DialogActions>
    </Dialog>
  )
}
