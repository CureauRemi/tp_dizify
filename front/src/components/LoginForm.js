import React, { useEffect, useState } from 'react'
import {Input, Typography, FormControl, FormGroup, Button } from '@material-ui/core'
import Title from '../components/Title'
import { Form } from 'react-bootstrap'
import UserService from '../lib/userService'
import { Redirect} from 'react-router-dom'
import Home from '../App'

export default class LoginForm extends React.Component {
    
    static user;
    
    constructor(props) {
      super(props);
      this.state = {
        email: '',
        password: null,
        redirect: false
      };
    }
    myChangeHandler = (event) => {
      let nam = event.target.name;
      let val = event.target.value;
      this.setState({[nam]: val});
    }

    login = async () => {
      if (this.state.email === '' || this.state.password === '') {
        return
      }
      console.log(this.state)
      console.log('useravant : ',this.user)
      this.user = await UserService.login(this.state)
      console.log('userapres : ',this.user)
  
      if(this.user != undefined ){
        localStorage.setItem('id', this.user.data.id)
        localStorage.setItem('email', this.user.data.email)
        localStorage.setItem('pseudo',this.user.data.pseudo)
        localStorage.setItem('token', this.user.data.token)

        this.setState({ redirect: true })
      }
    }

    render() {
      const { redirect } = this.state;
    
      if (redirect) {
        return <Redirect to='/'/>;
      }
      return (
        <Form >
          <FormGroup>
            <FormControl style={{ width: '20%', left: '40%', top: '20%'}}>
              <Title>Se connecter</Title>
              <Typography>Email :</Typography>
              <Input style={{ margin: '20px' }}
                type='text'
                name='email'
                onChange={this.myChangeHandler}
              />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl style={{ width: '20%', left: '40%', top: '20%'}}>
            <Typography>Mot de passe :</Typography>
            <Input style={{ margin: '20px' }}
              type='password'
              name='password'
              onChange={this.myChangeHandler}
              />
              </FormControl>
          </FormGroup>
          <FormControl style={{ width: '20%', left: '40%', top: '20%'}}>
            <Button onClick={this.login}
              style={{ margin: '20px', color: '#42ab9e',  border: '2px solid rgba(66, 171, 158, 0.5)', background: 'transparent'}}
              // id='submit'
              // type='submit'
            >
            Soumettre </Button>
          </FormControl>
        </Form>
      );
    }
  }