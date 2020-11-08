import React, { useState } from 'react'
import soapRequest from 'easy-soap-request'
import XMLParser from 'react-xml-parser'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'

import { endpointAlbum, defaultHeaders } from '../config'

export default function DialogAddAlbum({ open, handleClose, reload }) {
  const [title, setTitle] = useState('')
  const [isbn, setIsbn] = useState('')
  const [year, setYear] = useState('')
  const [artistId, setArtistId] = useState('')

  const addAlbum = async () => {
    if (title === '' || isbn === '' || year === '' || artistId === '') {
      return
    }
    let xml = `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ynov="http://nantes.ynov.com/soap/album">
	    <soapenv:Header />
	    <soapenv:Body>
        <ynov:addAlbumRequest>
          <ynov:title>${title}</ynov:title>
          <ynov:isbn>${isbn}</ynov:isbn>
          <ynov:year>${year}</ynov:year>
          <ynov:artistId>${artistId}</ynov:artistId>
        </ynov:addAlbumRequest>
	    </soapenv:Body>
    </soapenv:Envelope>`

    const { response } = await soapRequest({ url: endpointAlbum, headers: defaultHeaders, xml })
    const { body } = response
    let xmlParser = new XMLParser().parseFromString(body)
    console.log(xmlParser)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouveau album</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter un album, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="title" label="Titre" fullWidth onChange={(e) => setTitle(e.target.value)} required />
        <TextField margin="dense" id="isbn" label="ISBN" fullWidth onChange={(e) => setIsbn(e.target.value)} required />
        <TextField
          margin="dense"
          id="year"
          label="Date de publication"
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
