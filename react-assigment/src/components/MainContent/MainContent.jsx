import React from 'react';
import './MainContent.scss';

import Companies from '../../services/companies';

class MainContent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      filter: ''
    }
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

  render() {
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
          <Companies filter={this.state.filter}/>
        </div>
      </div>
    );
  } 
}

export default MainContent;