import React, { useState } from 'react'
import soapRequest from 'easy-soap-request'
import XMLParser from 'react-xml-parser'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'

import { endpointUser, defaultHeaders } from '../config'

export default function DialogAddUser({ open, handleClose, reload }) {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [pseudo, setPseudo] = useState('')

  const addUser = async () => {
    if (email === '' || password === '' || pseudo === '') {
      return
    }
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouvelle utilisateur </DialogTitle>
      <DialogContent>
        <DialogContentText>Créez un compte utilisateur</DialogContentText>
        <TextField autoFocus margin="dense" id="pseudo" label="Pseudo" fullWidth onChange={(e) => setPseudo(e.target.value)} required />
        <TextField margin="dense" id="email" label="email" fullWidth onChange={(e) => setEmail(e.target.value)} required />
        <TextField margin="dense" id="password" label="Mot de passe" fullWidth onChange={(e) => setPassword(e.target.value)} required />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addUser} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
