const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080';
// axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
// axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';


class UserService {
    
    async add(entity) {
        console.log('test : ', entity);
        return axios.post('user', entity);
    }

    async login(entity){
        
        console.log("je suis dans le service : ",entity)
        return axios.post('sign-in', entity)
    }
}

const userService = new UserService();

export default userService;