import React from 'react';
import './MainContent.scss';
import CompanyService  from '../../services/companies-service';
import Card from '../Card/Card';

class MainContent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      companies: [],
      filter: '',
    }

    this.getCompanies = this.getCompanies.bind(this);
  }

  componentWillMount() {
    this.getCompanies();
  }

  handleChange(e) {
    if (e.target.value.length >2){
      this.setState({
        filter: e.target.value.toLowerCase()
      })  
    } else {
      this.setState({
        filter: ''
      }) 
    }
  }

  /**
   * This function gets all companies from the service
   */
  getCompanies() {
    CompanyService.getCompanies().then( (response) => {
      this.setState({ companies: response.data.companies})
    });
  }

  render() {
    const companies = this.state.companies.filter(company => company.name.toLowerCase().includes(this.state.filter)).map ( company => (
      <Card name={company.name} isCertified={company.isCertified} description={company.description} rate={company.rate} hours={company.hours}/>
    ))
    return (
      <div>
        <div className="search">
        </div>

        <div className="container">
          <div className="container__header">
            <h1 className="container__header__title container__header__title--black">Los Mejores agentes de seguridad</h1>
            <input className="container__header__input" type="search" placeholder="Buscar por Ubicación" onChange={(e) => {this.handleChange(e)}} />
            <button className="container__header__button"/>
          </div>
          <div className="container__body" id="box">
                { companies }
            </div>
        </div>
      </div>
    );
  } 
}

export default MainContent;
