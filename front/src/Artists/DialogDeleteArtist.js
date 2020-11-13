import React from 'react'
import PropTypes from 'prop-types'
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'
import webservice from '../lib/webservice'

export default function DialogDeleteArtist({ open, handleClose, reload, artist}) {

  const deleteArtist = async () => {
    await webservice.deleteArtist(artist.id)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Suppression d'un artiste</DialogTitle>
      <DialogContent>
        <DialogContentText>Voulez-vous vraiment supprimer {artist.alias} ?</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Non
        </Button>
        <Button variant="outlined" onClick={deleteArtist} color="primary">
          Oui
        </Button>
      </DialogActions>
    </Dialog>
  )
}
