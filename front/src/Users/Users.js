import React, { useEffect, useState } from 'react'
import { Box, CircularProgress, Fab, Snackbar, Input, Typography, FormControl  } from '@material-ui/core'
import { Add, Delete } from '@material-ui/icons'
import Alert from '@material-ui/lab/Alert'

import DialogAddUser from './DialogAddUser'
import LoginForm from '../components/LoginForm'
import UserService from '../lib/userService'

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
      {loading ? (
        <CircularProgress />
      ) : (
      <LoginForm />
      )}
      <Box display="flex" alignItems="center" justifyContent="center" style={{ height: '100%' }}>
      <Typography style={{ margin: '20px' }}>Créez un compte utilisateur</Typography>
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