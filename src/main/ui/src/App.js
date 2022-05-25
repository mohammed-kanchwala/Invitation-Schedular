import React, { useEffect, useState } from "react";
import './App.css';
import Table from 'react-bootstrap/Table';
import 'bootstrap/dist/css/bootstrap.min.css';
import ButtonComponent from "./components/ButtonComponent";
import InputComponent from "./components/InputComponent";
import TableComponent from "./components/TableComponent";


function App() {

  const [distance, setDistance] = useState("");
  const [partners, setPartners] = useState([]);

  useEffect(() => {
  });

  const getTableBody = (params) => {
    return (
      <tbody>
        {partners && partners.map(partner =>
          <tr key={partner.id}>
            <td>{partner.organization}</td>
            <td>{partner.website}</td>
            <td>{partner.customerLocations}</td>
            <td>
              <Table id="partner_table" striped bordered hover>
                <thead>
                  <tr>
                    <th>Location</th>
                    <th>Address</th>
                  </tr>
                </thead>
                <tbody>
                  {partner.offices && partner.offices.map(office =>
                    <tr key={office.id}>
                      <td>{office.location}</td>
                      <td>{office.address}</td>
                    </tr>
                  )}
                </tbody>
              </Table>
            </td>
          </tr>
        )}
      </tbody>
    );
  }

  const handleSubmit = () => {
    const esc = encodeURIComponent;
    const url = 'http://localhost:8080/api/partners/search?';
    const params = {
      distance: distance,
    };
    const query = Object.keys(params).map(k => `${esc(k)}=${esc(params[k])}`).join('&')
    fetch(url + query)
      .then((res) => res.json())
      .then((json) => {
        setPartners(json)
      })
  }

  const handleDistanceChange = e => {
    setDistance(e.target.value);
  };

  return (
    <>
      <div className="container-fluid">
        <div className="row center">
          <div className="col-4">
            <InputComponent placeHolder="Distance" controlId="form.distance" type="number" name="Distance"
              onChange={handleDistanceChange}
              value={distance} />
          </div>
          <div className="col-2 mt-4">
            <ButtonComponent onClick={handleSubmit} labelName="Search" variant="primary" />
          </div>
        </div>
      </div>
      <div className="container-fluid">
        <TableComponent
          headers={['Organization', 'Website', 'Customer Location', 'Offices']}
          tableBody={getTableBody()}
        />
      </div>
    </>
  );
}

export default App;
