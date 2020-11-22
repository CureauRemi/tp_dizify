const axios = require('axios');
axios.defaults.baseURL = 'http://51.91.58.213:8080';



class UserService {
    
    async add(entity) {
        return axios.post('sign-up', entity);
    }

    async login(entity){
        return axios.post('sign-in', entity)
    }

    async signOut(){
        localStorage.clear();
    }
}

const userService = new UserService();

export default userService;
