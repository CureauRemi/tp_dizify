import webservice from './webservice'

class SongService extends webservice {

    constructor(){
        super('song')
    }
}

const songService = new SongService();

export default songService;