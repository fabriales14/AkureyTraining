import React from 'react';
import certified from '../assets/images/certified.png';
import agentImg from '../assets/images/chuck.jpg';

class Companies extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            companies: [],
        };
    }

    componentDidMount() {
        fetch('https://api.myjson.com/bins/uptto')
        .then( res => res.json())
        .then( (data) => {
            this.setState({
                companies: data.companies
            });
        },
        (error) => {
            this.setState({
                error
            });
        })
    }

    render() {
        const companies = this.state.companies.filter(company => company.name.toLowerCase().includes(this.props.filter)).map ( company => (
            <article className="container__body__item">
                <div className="container__body__item__header">
                    <h1 className="container__body__item__header__title">{company.name}</h1>
                    <img className="container__body__item__header__logo" src={certified}/>
                </div>
                <div className="container__body__item__line"></div>
                <section className="container__body__item__section">
                    <img className="container__body__item__section__img" src={agentImg}/>
                    <p className="container__body__item__section__info">{company.description}</p>
                </section>
                <footer className="container__body__item__footer">
                    <p className="container__body__item__footer__tittle">Desde ${company.rate} {company.hours}/hrs</p>
                    <button className="container__body__item__footer__button">Contratar</button>
                </footer>
            </article>
        ))
        return (
            <div className="container__body" id="box">
                { companies }
            </div>
        )
    }
}

export default Companies
