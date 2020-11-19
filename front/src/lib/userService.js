const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080';


class UserService {
    
    async add(entity) {
        console.log('test : ', entity);
        return axios.post('user', entity);
    }

    async login(pseudo, mdp){
        return axios.post('sign-in', pseudo, mdp)
    }
}

const userService = new UserService();

export default userService;