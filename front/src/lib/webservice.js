const axios = require('axios');
axios.defaults.baseURL = 'localhost:8080';
axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

module.exports = {

    async getAlbums(){
        await axios.get('/album', {headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
            "Access-Control-Allow-Headers": "Access-Control-*, Origin, X-Requested-With, Content-Type, Accept",
            "Access-Control-Expose-Headers": "Access-Control-*",
            'Allow': 'GET, POST, PUT, DELETE, OPTIONS, HEAD'
          }})
            .then(function (response) {
                console.log(response)
                return response
            });
    }
    
}