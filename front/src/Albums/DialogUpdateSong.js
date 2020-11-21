import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle } from '@material-ui/core'
import SongService from '../lib/songService'

export default function DialogUpdateSong({ open, handleClose, reload, song }) {
  const [title, setTitle] = useState('')
  const [duration, setDuration] = useState('')

  const updateSong = async () => {
    if (title === '' || duration === '') {
      return
    }

    await SongService.update({id: song, title: title, duration: duration})
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Modifier une chanson</DialogTitle>
      <DialogContent>
        <TextField autoFocus margin="dense" id="title" label="Titre" defaultValue={song.title} fullWidth onChange={(e) => setTitle(e.target.value)} required/>
        <TextField margin="dense" id="duration" label="Durée" defaultValue={song.duration} fullWidth onChange={(e) => setDuration(e.target.value)} required/>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={updateSong} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
