const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

class FavoryService {

    async getAll() {
        console.log(this.entity_endpoint)
        return await axios.get(this.entity_endpoint + '/all?page=0&limit=25').then(
            function(r){
                return r.data.content
            }
        );
    
    }

    async add(entity) {
        console.log('test : ', entity);
        return axios.post(this.entity_endpoint, entity);
      }

    async delete(id) {
        return axios.delete(this.entity_endpoint + '/' + id);
    }
}

const favoryService = new FavoryService();

export default favoryService;