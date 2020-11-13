import webservice from '../lib/webservice'

class AlbumService extends webservice {

    constructor(){
        super('album')
    }
}

const albumService = new AlbumService();

export default albumService;