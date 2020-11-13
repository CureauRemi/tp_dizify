import webservice from '../lib/webservice'

class ArtistService extends webservice {

    constructor(){
        super('artist')
    }
}

const artistService = new ArtistService();

export default artistService;