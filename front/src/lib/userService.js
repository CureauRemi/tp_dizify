import webservice from '../lib/webservice'

class UserService extends webservice {

    constructor(){
        super('user')
    }
}

const userService = new UserService();

export default userService;