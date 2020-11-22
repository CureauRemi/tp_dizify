import React, { useState } from 'react'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle } from '@material-ui/core'
import AlbumService from '../lib/albumService'
import { ContactsOutlined } from '@material-ui/icons'

export default function DialogUpdateAlbum({ open, handleClose, reload, album }) {
  const [name, setName] = useState('')
  const [image_album, setImage_Album] = useState('')
  const [year, setYear] = useState('') 
  const [artistName, setArtistName] = useState('')


  const updateAlbum = async () => {
    // if (album.name === '' || album.year === '' || album.artist.alias === '') {
    //   return
    // } else if (image_album === ''){
    //   setImage_Album(null)
    // }
    let res = {}
    res.id = album.id
    if(name ===''){
      res.name = album.name
    }else{
      res.name = name
    }
    if(image_album === ""){
      res.image_album = album.image_album
    }else{
      res.image_album = image_album
    }
    if(year === null){
      res.year = album.release_year
    }else{
      res.year = year
    }
    if(artistName === ""){
      res.artistName = album.artist.alias
    }else{
      res.artistName = artistName
    }
    await AlbumService.update(res)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Modifier un nouveau album</DialogTitle>
      <DialogContent>
        <TextField autoFocus margin="dense" id="name" label="Nom" defaultValue={album.name} fullWidth onChange={(e) => setName(e.target.value)} required />
        <TextField margin="dense" id="image_album" label="Couverture" fullWidth defaultValue={album.image_album} onChange={(e) => setImage_Album(e.target.value)}/>
        <TextField margin="dense" id="year" label="Année de sortie" type="number" fullWidth defaultValue={album.release_year} onChange={(e) => setYear(e.target.value)} required/>
        <TextField margin="dense" id="artistName" label="Nom de l'artiste" type="string" fullWidth defaultValue={album.artist.alias} onChange={(e) => setArtistName(e.target.value)} required/>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={updateAlbum} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
