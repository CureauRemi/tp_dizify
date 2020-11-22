import React, { useState } from 'react'
import { useLocation, Switch, Route, Link } from 'react-router-dom'
import clsx from 'clsx'
import { makeStyles, useTheme } from '@material-ui/core/styles'
import Hidden from '@material-ui/core/Hidden';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

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
import { Album, Person, Home, Star, QueueMusic, LockOpen, AccountCircle } from '@material-ui/icons'
import Artists from './Artists/Artists'
import Albums from './Albums/Albums'
import Users from './Users/Users'
import Favory from './Favori/Favory'
import Playlist from './Playlist/Playlists'
import Button from '@material-ui/core/Button'
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormGroup from '@material-ui/core/FormGroup';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';

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
  },
  title: {
    flexGrow: 1,
  },
}))

const Homes = () => {
  return (
    <>
      <div>Sélectionner une page</div>
      <img src={process.env.PUBLIC_URL + 'logo512.png'} alt="Logo Ynov" />
    </>
  )
}

export default function App(props) {
  const classes = useStyles()
  const { window } = props;
  const theme = useTheme();
  const [mobileOpen, setMobileOpen] = React.useState(false);
  const container = window !== undefined ? () => window().document.body : undefined;
  const [auth, setAuth] = React.useState(true);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);

  const handleChange = (event) => {
    setAuth(event.target.checked);
  };

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };
  const location = useLocation()
  let title = ''
  switch (location.pathname) {
    default:
    case '/':
      title = "Page d'accueil"
      break
    case '/albums':
      title = 'Albums'
      break
    case '/artists':
      title = 'Artistes'
      break
    case '/favory':
      title = 'Favories'
      break
    case '/playlist':
      title = 'Playlists'
      break
  }
  const drawer = (
    <div>
      <div className={classes.toolbar} />
      <ListItem component={Link} to="/">
        <ListItemIcon>
          <Home />
        </ListItemIcon>
      </ListItem>
      {/* <div className={classes.toolbar} /> */}
      <Divider />
      <List>
        <ListItem component={Link} to="/favory">
          <ListItemIcon>
            <Star />
          </ListItemIcon>
          <ListItemText primary="Mes favoris" />
        </ListItem>
        <ListItem component={Link} to="/playlist">
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
  );
  return (
    <>
      <div className={classes.root}>
        <CssBaseline />
        <FormGroup>
          <FormControlLabel
            control={<Switch checked={auth} onChange={handleChange} aria-label="login switch" />}
            label={auth ? 'Logout' : 'Login'}
          />
        </FormGroup>
        <AppBar position="absolute" className={clsx(classes.appBar, classes.appBarShift)}>
          <Toolbar className={classes.toolbar}>
            <IconButton
              color="inherit"
              aria-label="open drawer"
              edge="start"
              onClick={handleDrawerToggle}
              className={classes.menuButton}
            >
              <MenuIcon />
            </IconButton> 
            <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
              {title}
            </Typography>
            {/* <Button component={Link} to="/users" color="inherit">Connexion</Button> */}
            {auth && (
              <div>
                <IconButton
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleMenu}
                  color="inherit"
                >
                  <AccountCircle />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorEl}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={open}
                  onClose={handleClose}
                >
                  <MenuItem onClick={handleClose} component={Link} to="/users">Connexion</MenuItem>
                  {/* <MenuItem onClick={handleClose}>My account</MenuItem> */}
                </Menu>
              </div>
            )}
          </Toolbar>
        </AppBar>
        <nav className={classes.drawer} aria-label="mailbox folders">
          {/* <Drawer
            variant="persistent"
            open={open}
            onClose={() => setOpen(false)}
            classes={{
              paper: classes.drawerPaper
            }}
          >
            <div>
              
            </div>
          </Drawer> */}
          <Hidden smUp implementation="css">
            <Drawer
              container={container}
              variant="temporary"
              anchor={theme.direction === 'rtl' ? 'right' : 'left'}
              open={mobileOpen}
              onClose={handleDrawerToggle}
              classes={{
                paper: classes.drawerPaper,
              }}
              ModalProps={{
                keepMounted: true, // Better open performance on mobile.
              }}
            >
                {drawer}
            </Drawer>
          </Hidden>
          <Hidden xsDown implementation="css">
            <Drawer
              classes={{
                paper: classes.drawerPaper,
              }}
              variant="permanent"
              open
            >
              {drawer}
            </Drawer>
          </Hidden>
        </nav>
        <main className={classes.content}>
          <div className={classes.toolbar} />
          <Switch>
            <Route exact path="/" render={() => <Homes />} />
            <Route path="/artists" render={() => <Artists />} />
            <Route path="/albums" render={() => <Albums />} />
            <Route path="/users" render={() => <Users />}  />
            <Route path="/favory" render={() => <Favory />} />
            <Route path="/playlist" render={() => <Playlist />} />
          </Switch>
        </main>
      </div>
    </>
  )
}
