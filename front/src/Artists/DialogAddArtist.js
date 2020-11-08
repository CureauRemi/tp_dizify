import React, { useState } from 'react'
import soapRequest from 'easy-soap-request'
import XMLParser from 'react-xml-parser'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'

import { endpointArtist, defaultHeaders } from '../config'

export default function DialogAddArtist({ open, handleClose, reload }) {
  const [firstname, setFirstname] = useState('')
  const [lastname, setLastname] = useState('')
  const [birth, setBirth] = useState('')

  const addArtist = async () => {
    if (firstname === '' || lastname === '' || birth === '') {
      return
    }
    let xml = `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ynov="http://nantes.ynov.com/soap/author">
      <soapenv:Body>
        <ynov:addArtistRequest>
          <ynov:firstname>${firstname}</ynov:firstname>
          <ynov:lastname>${lastname}</ynov:lastname>
          <ynov:birth>${birth}</ynov:birth>
        </ynov:addArtistRequest>
      </soapenv:Body>
    </soapenv:Envelope>`

    const { response } = await soapRequest({ url: endpointArtist, headers: defaultHeaders, xml })
    const { body } = response
    let xmlParser = new XMLParser().parseFromString(body)
    console.log(xmlParser)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouvel artiste</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter un artiste, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="lastname" label="Nom" fullWidth onChange={(e) => setFirstname(e.target.value)} required />
        <TextField margin="dense" id="firstname" label="Prénom" fullWidth onChange={(e) => setLastname(e.target.value)} required />
        <TextField
          margin="dense"
          id="birth"
          label="Date de naissance"
          type="number"
          fullWidth
          onChange={(e) => setBirth(e.target.value)}
          required
        />
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
