import React, { useEffect, useState } from 'react'
// import soapRequest from 'easy-soap-request'
// import XMLParser from 'react-xml-parser'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import DialogAddAlbum from './DialogAddAlbum'
// import { endpointAlbum, defaultHeaders } from '../config'

export default function Albums() {
  const [loading, setLoading] = useState(true)
  const [albums, setAlbums] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openAlert, setOpenAlert] = useState(false)

  useEffect(() => {
    init()
  }, [])

  const init = async () => {
  //   let xml = `<soapenv:Envelope
  //   xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  //   xmlns:ynov="http://nantes.ynov.com/soap/album">
  //   <soapenv:Header />
  //   <soapenv:Body>
  //     <ynov:getAlbumsRequest />
  //   </soapenv:Body>
  // </soapenv:Envelope>`

    try {
      // const { response } = await soapRequest({ url: endpointAlbum, headers: defaultHeaders, xml })
      // const { body } = response
      // let xmlParser = new XMLParser().parseFromString(body)
      let albums = [{id: 1, image_album: '../Machine_Gun_Kelly_Tickets_to_My_Downfall.png', name: "Tickets to my downfall", release_year: 2020}]

      // for (const album of xmlParser.getElementsByTagName('ns2:album')) {
      //   let id = '',
      //     title = '',
      //     isbn = '',
      //     year = ''
      //   for (const c of album.children) {
      //     switch (c.name) {
      //       default:
      //         break
      //       case 'ns2:id':
      //         id = c.value
      //         break
      //       case 'ns2:title':
      //         title = c.value
      //         break
      //       case 'ns2:isbn':
      //         isbn = c.value
      //         break
      //       case 'ns2:year':
      //         year = c.value
      //         break
      //     }
      //   }
      //   albums.push({ id, title, isbn, year })
      // }
      setAlbums(albums)
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
      <Title>Liste des albums</Title>
      {loading ? (
        <CircularProgress />
      ) : (
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>Cover</TableCell>
              <TableCell>Name</TableCell>
              <TableCell align="right">Année de publication</TableCell>
              <TableCell />
            </TableRow>
          </TableHead>
          <TableBody>
            {albums.map((album) => (
              <TableRow key={album.name}>
                <TableCell><img src={album.image_album} /></TableCell>
                <TableCell>{album.name}</TableCell>
                <TableCell align="right">{album.release_year}</TableCell>
                <TableCell>
                  <IconButton aria-label="Supprimer un album" onClick={() => console.log('todo')}>
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
        <DialogAddAlbum
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
