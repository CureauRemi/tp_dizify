import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import AlbumService from '../lib/albumService'

export default function DialogAddAlbum({ open, handleClose, reload }) {
  const [name, setName] = useState('')
  const [image_album, setImage_Album] = useState('')
  const [year, setYear] = useState('')
  const [artistId, setArtistId] = useState('')

  const addAlbum = async () => {
    if (name === '' || year === '' || artistId === '') {
      return
    } else if (image_album === ''){
      setImage_Album(null)
    }

    await AlbumService.add({name: name, image_album: image_album, release_year: year, artist_id: artistId})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouveau album</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter un album, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="name" label="Nom" fullWidth onChange={(e) => setName(e.target.value)} required />
        <TextField margin="dense" id="image_album" label="Couverture" fullWidth onChange={(e) => setImage_Album(e.target.value)}/>
        <TextField
          margin="dense"
          id="year"
          label="Année de sortie"
          type="number"
          fullWidth
          onChange={(e) => setYear(e.target.value)}
          required
        />
        <TextField
          margin="dense"
          id="artistId"
          label="Artist ID"
          type="number"
          fullWidth
          onChange={(e) => setArtistId(e.target.value)}
          required
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addAlbum} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
