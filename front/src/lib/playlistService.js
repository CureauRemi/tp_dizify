const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080/';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

class PlaylistService {

    async getAll() {
        const token = localStorage.getItem('token');
        return await axios.get(`playlist`, {headers:{
            'Authorization': `Bearer ${token}` 
        }}).then(
            function(r){
                console.log(r.data)
                return r.data
            }
        );
    
    }

    async addPlaylist(root,id) {
        console.log(root, '', id)
        const token = await localStorage.getItem('token') 
        console.log(token)
        return axios.post(`playlist/add/${root}/${id}`,{},{headers:{
            'Authorization': `Bearer ${token}` 
        }});
      }

    async deletePlaylist(root,id) {
        const token = localStorage.getItem('token') 
        return axios.delete(`playlist/delete/${root}`, id,{headers:{
            'Authorization': `Bearer ${token}` 
        }});
    }
}

const playlistService = new PlaylistService();

export default playlistService;