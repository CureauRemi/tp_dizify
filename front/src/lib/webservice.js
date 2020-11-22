const axios = require('axios');
axios.defaults.baseURL = 'http://51.91.58.213:8080';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default class WebSersice {

    entity_endpoint;
    
    constructor(endpoint){
        this.entity_endpoint = endpoint;
    }
    async getAll() {
        const token = localStorage.getItem('token')
        return await axios.get(this.entity_endpoint + '?page=0&limit=25',{headers:{
            'Authorization': `Bearer ${token}` 
        }}).then(
            function(r){
                return r.data.content
            }
        );

      }
    async getOne(id){
        const token = localStorage.getItem('token')
        return await axios.get(this.entity_endpoint + '/' + id,{headers:{
            'Authorization': `Bearer ${token}` 
        }}).then(
            function(r){
                return r.data
            }
        );
      }

    async add(entity) {
        const token = localStorage.getItem('token')
        return axios.post(this.entity_endpoint, entity,{headers:{
            'Authorization': `Bearer ${token}` 
        }});
      }

    async update(entity) {
        const token = localStorage.getItem('token')
        return axios.put(this.entity_endpoint, entity,{headers:{
            'Authorization': `Bearer ${token}` 
        }});
      }

    async delete(id) {
        const token = localStorage.getItem('token')
        return axios.delete(`${this.entity_endpoint}/${id}`,{headers:{
            'Authorization': `Bearer ${token}` 
        }});
    }

}
