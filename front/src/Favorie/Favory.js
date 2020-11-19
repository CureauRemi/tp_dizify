import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import favoryService from '../lib/favoryService'
import userService from '../lib/userService'

export default function Favory() {
    const [loading, setLoading] = useState(true)
    const [favorites, setFavorites] = useState([])
    const [openAlert, setOpenAlert] = useState(false)
  
    useEffect(() => {
      init()
    }, [])
  
    const init = async () => {
  
      try {
        
          let userIdLocal = localStorage.getItem('id');
          console.log(userIdLocal)
          let favorites = await favoryService.getAll(userIdLocal);
        
       
        
        setFavorites(favorites)
        setTimeout(function () {
          setLoading(false)
        }, 1500)
      } catch (error) {
        setLoading(false)
        setOpenAlert(true)
      }
    }
  
    return (
      <>
        <Title>Liste des favoris</Title>
        {loading ? (
          <CircularProgress />
        ) : (
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>Nom</TableCell>
                <TableCell>Artiste</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {favorites.map((favory) => (
                <TableRow key={favory.name}>
                  <TableCell>{favory.name}</TableCell>
                  <TableCell>{favory.artist}</TableCell>
                  <TableCell>
                    <IconButton aria-label="Supprimer un favori" onClick={() => console.log('todo')}>
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
        <Snackbar open={openAlert} autoHideDuration={5000} onClose={() => setOpenAlert(false)}>
          <Alert onClose={() => setOpenAlert(false)} severity="error">
            Une erreur réseau est survenue
          </Alert>
        </Snackbar>
      </>
    )
  }