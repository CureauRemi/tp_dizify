const axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

module.exports = {

    async get(URL){
        return await axios.get(URL)
            .then(function (response) {
                return response.data.content
            });
    },

    async add(URL, body){
        return await axios.post(URL, body)
            .then(function (response) {
                return response
            });
    },

    async delete(URL){
        return await axios.delete(URL)
            .then(function (response) {
                return response
            });
    },

    async update(URL, body){
        return await axios.put(URL, body)
            .then(function (response) {
                return response
            });
    },

    // Albums
    async getAlbums(){
        return await axios.get('/album/all?page=0&limit=25')
            .then(function (response) {
                return response.data.content
            });
    },

    async addArtist(body){
        return await axios.post('/album', body)
            .then(function (response) {
                return response
            });
    },

    //Artist
    async getArtists(){
        return await axios.get('/artist/all?page=0&limit=25')
            .then(function (response) {
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
                console.log(response)
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