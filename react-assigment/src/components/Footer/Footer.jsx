import React from 'react';
import './Footer.scss';

class Footer extends React.Component {
  render() {
    return (
        <footer className="footer">
            <p className="footer__header">Desarrollado por Akurey.com</p>
            <p className="footer__text">Todos los derechos reservados.</p>
            <div className="footer__link">
                <a href="">Términos y Condiciones</a>
            </div>
        </footer>
    );
  } 
}

export default Footer;