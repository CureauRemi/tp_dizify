import React, { useState } from 'react'
import { useLocation, Switch, Route, Link } from 'react-router-dom'
import clsx from 'clsx'
import { makeStyles } from '@material-ui/core/styles'
import CssBaseline from '@material-ui/core/CssBaseline'
import AppBar from '@material-ui/core/AppBar'
import Divider from '@material-ui/core/Divider'
import Drawer from '@material-ui/core/Drawer'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import ListItemIcon from '@material-ui/core/ListItemIcon'
import ListItemText from '@material-ui/core/ListItemText'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import button from '@material-ui/core/Button'
import { Album, Person, Home, Star, QueueMusic, LockOpen } from '@material-ui/icons'
import Artists from './Artists/Artists'
import Albums from './Albums/Albums'
import Users from './Users/Users'
import Button from '@material-ui/core/Button'
const drawerWidth = 240

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex'
  },
  drawer: {
    [theme.breakpoints.up('sm')]: {
      width: drawerWidth,
      flexShrink: 0
    }
  },
  appBar: {
    marginLeft: drawerWidth,
    [theme.breakpoints.up('sm')]: {
      width: `calc(100% - ${drawerWidth}px)`
    }
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      display: 'none'
    }
  },
  toolbar: theme.mixins.toolbar,
  drawerPaper: {
    width: drawerWidth
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3)
  }
}))

const MyAppBar = () => {
  const classes = useStyles()
  const location = useLocation()
  let title = ''
  switch (location.pathname) {
    default:
    case '/':
      title = "Page d'accueil"
      break
    case '/auteurs':
      title = 'Auteurs'
      break
    case '/livres':
      title = 'Livres'
      break
  }
  return (
    <AppBar position="absolute" className={clsx(classes.appBar, classes.appBarShift)}>
      <Toolbar className={classes.toolbar}>
        <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
          {title}
        </Typography>
        <Button component={Link} to="/users" style={{left: "85%"}}>Connexion</Button>
      </Toolbar>
    </AppBar>
  )
}

const Homes = () => {
  return (
    <>
      <div>Sélectionner une page</div>
      <img src={process.env.PUBLIC_URL + 'logo512.png'} alt="Logo Ynov" />
    </>
  )
}

export default function App() {
  const classes = useStyles()
  const [open, setOpen] = useState(true)
  return (
    <>
      <div className={classes.root}>
        <CssBaseline />
        <MyAppBar />
        <nav className={classes.drawer} aria-label="mailbox folders">
          <Drawer
            variant="persistent"
            open={open}
            onClose={() => setOpen(false)}
            classes={{
              paper: classes.drawerPaper
            }}
          >
            <div>
              <ListItem component={Link} to="/">
                <ListItemIcon>
                  <Home />
                </ListItemIcon>
              </ListItem>
              {/* <div className={classes.toolbar} /> */}
              <Divider />
              <List>
                <ListItem>
                  <ListItemIcon>
                    <Star />
                  </ListItemIcon>
                  <ListItemText primary="Mes favoris" />
                </ListItem>
                <ListItem>
                  <ListItemIcon>
                    <QueueMusic />
                  </ListItemIcon>
                  <ListItemText primary="Mes playlists" />
                </ListItem>
                <ListItem component={Link} to="/artists">
                  <ListItemIcon> 
                    <Person />
                  </ListItemIcon>
                  <ListItemText primary="Artistes" />
                </ListItem>
                <ListItem component={Link} to="/albums">
                  <ListItemIcon>
                    <Album />
                  </ListItemIcon>
                  <ListItemText primary="Albums" />
                </ListItem>
              </List>
            </div>
          </Drawer>
        </nav>
        <main className={classes.content}>
          <div className={classes.toolbar} />
          <Switch>
            <Route exact path="/" render={() => <Homes />} />
            <Route path="/artists" render={() => <Artists />} />
            <Route path="/albums" render={() => <Albums />} />
            <Route path="/users" render={() => <Users />} />
          </Switch>
        </main>
      </div>
    </>
  )
}
