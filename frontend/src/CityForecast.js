import React, { Component } from 'react';
import {Container, Table} from 'reactstrap';


class CityForecast extends Component {

    constructor(props) {
        super(props);
        this.state = {
            forecastList: [],
            message: null
        };
    }

    async componentDidMount() {
        const response = await (await fetch(`/api/city-forecast/${this.props.match.params.id}`)).json();
        this.setState({
            forecastList: response,
        });

    }

    


    render() {
        const {forecastList, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const forecastTable = forecastList.map(forecast => {
            const pop = forecast.rainProbability * 100;
            return <tr>
                <td style={{whiteSpace: 'nowrap'}}>{forecast.date}</td>
                <td>{forecast.tempMin}°C</td>
                <td>{forecast.tempMax}°C</td>
                <td>{forecast.mainWeather}</td>
                <td>{pop.toFixed(0)}%</td>

            </tr>
        });


            
        return (
            <div>
                <Container >
                    
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Date</th>
                            <th width="20%">Minimum Temperature</th>
                            <th width="20%">Maximum Temperature</th>
                            <th width="20%">Weather</th>
                            <th width="20%">Rain Probability</th>
                        </tr>
                        </thead>
                        <tbody>
                        {forecastTable}
                        </tbody>
                    </Table>

                    <a href={"/"}>See Cities List</a>
                </Container>


            </div>
        );
    }
}
export default CityForecast;