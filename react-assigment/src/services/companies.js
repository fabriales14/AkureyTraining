import axios from 'axios';

class CompanyService {


    /**
     *  Returns a axios promise of get request from API
     */
    static getCompanies() {
        return axios.get('https://api.myjson.com/bins/uptto');
    }
}

export default CompanyService;



