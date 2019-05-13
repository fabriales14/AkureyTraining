import React from 'react';
import './App.scss';
import logo from './assets/images/logo.png'

import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import MainContent from './components/MainContent/MainContent';

class App extends React.Component {
  render() {
    return (
      <div>
        <Header/>
        <MainContent/>
        <Footer/>
      </div>
    );
  } 
}

export default App;
