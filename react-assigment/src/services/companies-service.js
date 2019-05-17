import axios from 'axios';
import env from './../config/env/local';
class CompanyService {


    /**
     *  Returns a axios promise of get request from API
     */
    static getCompanies() {
        return axios.get(env.server.api.baseURL);
    }
}

export default CompanyService;



