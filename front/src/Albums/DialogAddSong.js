import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import SongService from '../lib/songService'

export default function DialogAddSong({ open, handleClose, reload }) {
  const [title, setTitle] = useState('')
  const [duration, setDuration] = useState('')
  const [artistId, setArtistId] = useState('')
  const [albumId, setAlbumId] = useState('')

  const addSong = async () => {
    if (title === '' || duration === '') {
      return
    }

    await SongService.add({title: title, duration: duration, artistId:artistId, albumId:albumId})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter une nouvelle chanson</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter une chanson, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="title" label="Titre" fullWidth onChange={(e) => setTitle(e.target.value)} required />
        <TextField margin="dense" id="duration" label="Durée" fullWidth onChange={(e) => setDuration(e.target.value)} required/>
        <TextField margin="dense" id="artistId" label="Artist ID" type="number" fullWidth onChange={(e) => setArtistId(e.target.value)} required />
        <TextField margin="dense" id="albumId" label="Album Id" type="number" fullWidth onChange={(e) => setAlbumId(e.target.value)} required />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addSong} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
