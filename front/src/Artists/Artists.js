import React, { useEffect, useState } from 'react'
import soapRequest from 'easy-soap-request'
import XMLParser from 'react-xml-parser'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import DialogAddArtist from './DialogAddArtist'
import { endpointArtist, defaultHeaders } from '../config'

export default function Artists() {
  const [loading, setLoading] = useState(true)
  const [authors, setArtists] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openAlert, setOpenAlert] = useState(false)

  useEffect(() => { 
    init()
  }, [])

  const init = async () => {
    let xml = `
    <soapenv:Envelope
      xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
      xmlns:ynov="http://nantes.ynov.com/soap/author">
      <soapenv:Body>
        <ynov:getArtistsRequest />
      </soapenv:Body>
    </soapenv:Envelope>
    `
    try {
      const { response } = await soapRequest({ url: endpointArtist, headers: defaultHeaders, xml })
      const { body } = response
      let xmlParser = new XMLParser().parseFromString(body)
      let authors = []
      for (const author of xmlParser.getElementsByTagName('ns2:author')) {
        let id = '',
          firstname = '',
          lastname = '',
          birth = ''
        for (const c of author.children) {
          switch (c.name) {
            default:
              break
            case 'ns2:id':
              id = c.value
              break
            case 'ns2:firstname':
              firstname = c.value
              break
            case 'ns2:lastname':
              lastname = c.value
              break
            case 'ns2:birth':
              birth = c.value
              break
          }
        }
        authors.push({ id, firstname, lastname, birth })
      }
      setArtists(authors)
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
      <Title>Liste des artistes</Title>
      {loading ? (
        <CircularProgress />
      ) : (
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Prénom</TableCell>
              <TableCell>NOM</TableCell>
              <TableCell align="right">Année de naissance</TableCell>
              <TableCell />
            </TableRow>
          </TableHead>
          <TableBody>
            {authors.map((author) => (
              <TableRow key={author.id}>
                <TableCell>{author.id}</TableCell>
                <TableCell>{author.firstname}</TableCell>
                <TableCell>{author.lastname}</TableCell>
                <TableCell align="right">{author.birth}</TableCell>
                <TableCell>
                  <IconButton aria-label="Supprimer un artiste" onClick={() => console.log('todo')}>
                    <Delete />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      )}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '100%' }}>
        <Fab color="primary" aria-label="add" onClick={() => setOpenAddDialog(true)}>
          <Add />
        </Fab>
      </Box>
      {openAddDialog && (
        <DialogAddArtist
          open={openAddDialog}
          handleClose={() => setOpenAddDialog(false)}
          reload={() => {
            setOpenAddDialog(false)
            init()
          }}
        />
      )}
      <Snackbar open={openAlert} autoHideDuration={5000} onClose={() => setOpenAlert(false)}>
        <Alert onClose={() => setOpenAlert(false)} severity="error">
          Une erreur réseau est survenue
        </Alert>
      </Snackbar>
    </>
  )
}
