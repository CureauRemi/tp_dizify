import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, IconButton, Snackbar, Table, TableBody, TableCell, TableHead, TableRow, FormControl } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import Title from '../components/Title'
import DialogAddUser from './DialogAddUser'
import webservice from '../lib/webservice'

import ReactDOM from 'react-dom'

export default function Users() {
  const [loading, setLoading] = useState(true)
  const [users, setUsers] = useState([])
  const [openAddDialog, setOpenAddDialog] = useState(false)
  const [openAlert, setOpenAlert] = useState(false)

  useEffect(() => {
    init()
  }, [])

  const init = async () => {
    try {
      let users = await webservice.getUsers();
      //users.push({ id, email, password, pseudo })
      setUsers(users)
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
      <Title>Se connecter</Title>
      {loading ? (
        <CircularProgress />
      ) : (
        <form>
        <p>Pseudo:</p>
        <input type="text"/>
        <p>Mot de passe:</p>
        <input type="text"/>
      </form>
      )}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '100%' }}>
        <Fab color="primary" aria-label="add" onClick={() => setOpenAddDialog(true)}>
          <Add />
        </Fab>
      </Box>
      {openAddDialog && (
        <DialogAddUser
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

class SimpleForm extends React.Component {
  constructor(props) {
    super(props);
 
    this.state = {
      fullName: ""
    };
  }
 
  handleSubmitForm(event) {
    alert("Full Name: " + this.state.fullName);
    event.preventDefault();
  }
 
  handleChange(event) {
    var value = event.target.value;
 
    this.setState({
      fullName: value
    });
  }
 
  render() {
    return (
      <form onSubmit={event => this.handleSubmitForm(event)}>
        <label>
          Full Name:
          <input
            type="text"
            value={this.state.fullName}
            onChange={event => this.handleChange(event)}
          />
        </label>
        <input type="submit" value="Submit" />
        <p>{this.state.fullName}</p>        
      </form>
    );
  }
}
 
// Render
ReactDOM.render(<SimpleForm />, document.getElementById("root"));