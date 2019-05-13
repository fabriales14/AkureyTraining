import React from 'react';
import './Header.scss';
import logo from '../../assets/images/logo.png'

class Header extends React.Component {
  render() {
    return (
      <header className="header">
        <img className="header__logo" alt="" src={logo}></img>
        <a className="header__icon"><i className="fas fa-bars fa-3x"></i></a>
        <nav className="header__nav">
          <ul className="header__nav__elements">
            <li><a href="#">Directorio de Agentes</a></li>
            <li><a href="#">Contacto</a></li>
            <li><a href="#">Ingresar</a></li>
          </ul>
        </nav>
      </header>
    );
  } 
}

export default Header;