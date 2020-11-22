import React from 'react'
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import AlbumService from '../lib/albumService'

export default function DialogDeleteAlbum({ open, handleClose, reload, album}) {

  const deleteAlbum = async () => {
    await AlbumService.delete(album.id)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Suppression d'un album</DialogTitle>
      <DialogContent>
        <DialogContentText>Voulez-vous vraiment supprimer {album.name} ?</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Non
        </Button>
        <Button variant="outlined" onClick={deleteAlbum} color="primary">
          Oui
        </Button>
      </DialogActions>
    </Dialog>
  )
}
