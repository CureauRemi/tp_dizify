const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080/';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

class FavoryService {

    async getAll(idUser) {
        
        return await axios.get(`user/${idUser}/favorite`).then(
            function(r){
                console.log(r.data)
                return r.data.content
            }
        );
    
    }

    async addFavorite(entity) {
        console.log('test : ', entity);
        return axios.post(`user/${this.idUser}/favorite`, entity);
      }

    async deleteFavoriteAlbum(entity) {
        return axios.delete(`user/${this.idUser}/favorite`, entity);
    }
}

const favoryService = new FavoryService();

export default favoryService;