const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

module.exports = {

    async getAlbums(){
        return await axios.get('/albums?page=0&limit=25')
            .then(function (response) {
                return response.data.content
            });
    },

    async getArtists(){
        return await axios.get('/artists?page=0&nb=25')
            .then(function (response) {
                console.log(response)
                return response.data.content
            });
    },

    async addArtist(body){
        return await axios.post('/artist', body)
            .then(function (response) {
                return response
            });
    },

    async deleteArtist(id){
        return await axios.delete('/artist/' + id)
            .then(function (response) {
                return response
            });
    },

    async updateArtist(artist){
        return await axios.put('/artist/', artist)
            .then(function (response) {
                return response
            });
    }
    
}