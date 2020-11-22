import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, IconButton, Snackbar } from '@material-ui/core'
import { Card, CardContent, CardMedia, Typography, Grid, makeStyles, Avatar} from '@material-ui/core'
import { Add, Delete, Edit, Star } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'
import { Link } from "react-router-dom";

import Title from '../components/Title'
import DialogAddArtist from './DialogAddArtist'
import DialogDeleteArtist from './DialogDeleteArtist'
import DialogUpdateArtist from './DialogUpdateArtist'
import artistService from '../lib/artistService'
import favoryService from '../lib/favoryService'

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 0,
    padding: theme.spacing(2)
  },
  media: {
    height: 80,
    width: 80
    
  },
  card: {
    display: 'block',
    height: '100%',
    width: 300,
    'padding-top': 25,
    'text-decoration': 'none',
    color: 'black',
  },
  avatar: {
    'margin-left': 'auto',
    'margin-right': 'auto',
    'margin-top': '5px',
    color: theme.palette.getContrastText('#42ab9e'),
    backgroundColor: '#42ab9e',
    width: 200,
    height: 200
  }
}));

export default function Artists() {
  const [loading, setLoading] = useState(true)
  const [artists, setArtists] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false)
  const [artistDeleted, setArtistDeleted] = useState({})
  const [openUpdateDialog, setOpenUpdateDialog] = useState(false)
  const [artistUpdate, setArtistUpdate] = useState({})
  const [openAlert, setOpenAlert] = useState(false)
  const classes = useStyles();
  const isAdmin = false;

  useEffect(() => { 
    init()
  }, [])

  const init = async () => {
    try {
      let artists = []
      artists = await artistService.getAll();
      console.log(artists)
      setArtists(artists)
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
        <Grid
          container
          spacing={2}
          direction="row"
          justify="flex-start"
          alignItems="flex-start"
          className={classes.root}
        >
          {artists.map((artist) => (
            <Grid item xs={12} sm={6} md={3} key={artists.indexOf(artist)}>
              <Card className={classes.card} key={artist.alias}>
                    {artist.image_artist !== null &&
                  <CardMedia component={Link} to={"/artist/"+artist.id}
                    image={process.env.PUBLIC_URL + '/img/'+ artist.image_artist}        
                    title={artist.alias}
                    style={{ height: '200px', width: '200px', 'border-radius': '100px', 'margin-top': '5px', 'margin-left': 'auto', 'margin-right': 'auto'}}
                  />
                }
                {artist.image_artist == null &&
                  <Avatar component={Link} to={"/artist/"+artist.id} className={classes.avatar}>{artist.alias.substring(0,1)}</Avatar>
                }
                <div>
                  <CardContent>
                    <Typography component={Link} to={"/artist/"+artist.id} style={{'text-align': 'center', 'font-weight': 'bold'}}>
                      {artist.alias}
                    </Typography>
                  </CardContent>
                    <div>
                      <IconButton aria-label="Modifier un artiste" onClick={() => {
                        setArtistUpdate(artist)
                        setOpenUpdateDialog(true)
                        }}>
                        <Edit />
                      </IconButton>
                      <IconButton aria-label="Supprimer un artiste" onClick={() => {
                        setArtistDeleted(artist)
                        setOpenDeleteDialog(true)
                        }}>
                        <Delete />
                      </IconButton>
                      <IconButton aria-label="favori artist" onClick={() => {
                        favoryService.addFavorite("artist", artist.id)
                      }}>
                      <Star />
                      </IconButton>
                    </div>
                </div>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '35%' }}>
        <Fab color="primary" aria-label="add" onClick={() => setOpenAddDialog(true)}>
          <Add />
        </Fab>
      </Box>
      {/* Add */}
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
      {/* Update */}
      {openUpdateDialog && (
        <DialogUpdateArtist
          open={openUpdateDialog}
          artist={artistUpdate}
          handleClose={() => setOpenUpdateDialog(false)}
          reload={() => {
            setOpenUpdateDialog(false)
            init()
          }}
        />
      )}
      {/* Delete */}
      {openDeleteDialog && (
        <DialogDeleteArtist
          open={openDeleteDialog}
          artist={artistDeleted}
          handleClose={() => setOpenDeleteDialog(false)}
          reload={() => {
            setOpenDeleteDialog(false)
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
