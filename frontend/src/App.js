import React, { Component } from "react"
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import CityList from './CityList';
import CityForecast from './CityForecast';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={CityList}/>
            <Route path='/forecast-city/:id' component={CityForecast}/>
          </Switch>
        </Router>
    )
  }
}

export default App;
