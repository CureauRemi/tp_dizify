import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import { Box, CircularProgress, Fab, Snackbar, Input, Typography, FormControl, FormGroup } from '@material-ui/core'
import Title from '../components/Title'
import { Form } from 'react-bootstrap'

export default class LoginForm extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        pseudo: '',
        password: null,
      };
    }
    myChangeHandler = (event) => {
      let nam = event.target.name;
      let val = event.target.value;
      this.setState({[nam]: val});
    }
    render() {
      return (
        <Form >
          <FormGroup>
            <FormControl style={{ width: '20%', left: '40%', top: '20%'}}>
              <Title>Se connecter</Title>
              <Typography>Pseudo :</Typography>
              <Input style={{ margin: '20px' }}
                type='text'
                name='pseudo'
                onChange={this.myChangeHandler}
              />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl style={{ width: '20%', left: '40%', top: '20%'}}>
            <Typography>Mot de passse :</Typography>
            <Input style={{ margin: '20px' }}
              type='password'
              name='password'
              onChange={this.myChangeHandler}
            />
            <input style={{ margin: '20px', color: '#42ab9e',  border: '2px solid rgba(66, 171, 158, 0.5)', background: 'transparent'}}
              id='submit'
              type='submit'
            />
            </FormControl>
        </FormGroup>
        </Form>
      );
    }
  }