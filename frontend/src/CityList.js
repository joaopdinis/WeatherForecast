import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table} from 'reactstrap';


class CityList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            cities: [],
            message: null
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.fetchCities();
    }

    async fetchCities() {
        fetch('/api/cities/list')
            .then(response => response.json())
            .then(data => this.setState({cities: data}));
    }

    async handleSubmit(event) {
        event.preventDefault();
        const cityName = event.target.cityName.value;

        const options = {
            method: 'POST',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({cityName: cityName}),
        };

        const response = await fetch('/api/cities/register', options);

        if (response.ok) {
            this.fetchCities();
            this.setState({message: 'City Added'});
        } else {
            this.setState({message: 'City Not Added'});
            const errorData = await response.json()
            this.setState({message: errorData.message});
        }
        

    }

    async remove(cityId) {
        const options = {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
        };

        const response = await fetch('/api/cities/delete/' + cityId, options);
        if (response.ok) {
            let updatedCities = this.state.cities.filter(city => city.id!== cityId);
            this.setState({cities: updatedCities});
        }
    }

    render() {
        const {cities, isLoading, message} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const cityList = cities.map(city => {
            const href = "/forecast-city/" + city.id;
            return <tr key={city.id}>
                <td>
                    <a href={href}> {city.name}</a>
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="danger" onClick={() => this.remove(city.id)}>Delete</Button>
                    </ButtonGroup>
                </td>

            </tr>
        });


            
        return (
            <div>
                <Container >
                    <div className="float-right">
                        <form onSubmit={this.handleSubmit}>
                            <div className='form-group my-3 d-flex flex-column gap-1 w-50'>
                                <label htmlFor="cityName">Add New City</label>
                                <input type="text" className="form-control" name="cityName" placeholder="Enter City Name" />
                            </div>
                            {message && <p>{message}</p>} {/* Display the response message */}
                            <div className='form-group'>
                                <Button type="submit">Add City</Button>
                            </div>
                        </form>
                    </div>
                    
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Cities</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cityList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default CityList;