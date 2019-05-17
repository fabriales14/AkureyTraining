import React from 'react';
import './Card.scss';
import certified from '../../assets/images/certified.png';
import agentImg from '../../assets/images/chuck.jpg';

class Card extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: props.name,
            isCertified: props.isCertified,
            description: props.description,
            rate: props.rate,
            hours: props.hours
        }
    }

    render() {
        return (
            <article className="container__body__item">
                <div className="container__body__item__header">
                    <h1 className="container__body__item__header__title">{this.state.name}</h1>
                    {this.state.isCertified ? <img className="container__body__item__header__logo" src={certified} /> : null}
                </div>
                <div className="container__body__item__line"></div>
                <section className="container__body__item__section">
                    <img className="container__body__item__section__img" src={agentImg} />
                    <p className="container__body__item__section__info">{this.state.description}</p>
                </section>
                <footer className="container__body__item__footer">
                    <p className="container__body__item__footer__tittle">Desde ${this.state.rate} {this.state.hours}/hrs</p>
                    <button className="container__body__item__footer__button">Contratar</button>
                </footer>
            </article>
        );
    }
}

export default Card;