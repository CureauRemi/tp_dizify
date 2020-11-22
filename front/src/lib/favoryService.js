const axios = require('axios');
axios.defaults.baseURL = 'http://51.91.58.213:8080/';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

class FavoryService {

    async getAll() {
        const token = localStorage.getItem('token');
        return await axios.get(`favorite`, {headers:{
            'Authorization': `Bearer ${token}` 
        }}).then(
            function(r){
                return r.data
            }
        );
    
    }

    async addFavorite(root,id) {
        const token = await localStorage.getItem('token') 
        return axios.post(`favorite/add/${root}/${id}`,{},{headers:{
            'Authorization': `Bearer ${token}` 
        }});
      }

    async deleteFavorite(root,id) {
        const token = localStorage.getItem('token') 
        return axios.delete(`favorite/delete/${root}/${id}`,{headers:{
            'Authorization': `Bearer ${token}` 
        }});
    }
}

const favoryService = new FavoryService();

export default favoryService;
