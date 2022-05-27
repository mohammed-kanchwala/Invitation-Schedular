import React, { useState } from "react";
import './App.css';
import ButtonComponent from "./components/ButtonComponent";
import InputComponent from "./components/InputComponent";
import AccordianComponent from "./components/AccordianComponent";
import Box from '@mui/material/Box';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import Stack from '@mui/material/Stack';

function App() {

  const [distance, setDistance] = useState("");
  const [partners, setPartners] = useState([]);
  const [isFormInvalid, setIsFormInvalid] = useState(false);
  const [isDataAvailable, setIsDataAvailable] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (distance <= 0 || distance === null) {
      setIsFormInvalid(true);
    }
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
      }).then(partners.length !== 0 ? setIsDataAvailable(false) : setIsDataAvailable(true))

  }

  const handleDistanceChange = e => {
    setIsFormInvalid(false);
    setDistance(e.target.value);
  };
  return (
    <>
      <div className="Background-Image" style={{ backgroundColor: "blue" }}>
        <div className="container-fluid">
          <form onSubmit={handleSubmit} >
            <Box
              sx={{
                '& .MuiTextField-root': { m: 4, width: '20' },
              }}
              autoComplete="off"
            >
              <div className="row center">
                <div className="d-flex justify-content-center" style={{ marginTop: "100px" }}>
                  <InputComponent type="number" label="Distance"
                    onChange={handleDistanceChange}
                    value={distance}
                    required
                    variant="filled"
                    error={isFormInvalid}
                    helperText={isFormInvalid && "value of distance should be above 0"}
                  />
                  <div className="mt-5">
                    <ButtonComponent type="submit" label="Search" variant='contained' />
                  </div>
                </div>
              </div>
            </Box>
          </form>
        </div>
        <div className="container-fluid">
          <div className="row center md-12">
            {partners.length ? (
              partners.map((partner, index) => {
                return (
                  <div key={index} className="d-flex justify-content-center" style={{ padding: "5px" }}>
                    <AccordianComponent partner={partner} />
                  </div>);
              })
            ) :
              (
                <div className="d-flex justify-content-center" style={{ padding: "5px" }}>
                  {isDataAvailable ? (
                    <Stack sx={{ width: '100%', marginTop: "1px" }} spacing={2}>
                      <Alert severity="info">
                        <AlertTitle>Info</AlertTitle>
                        <strong>NO Partners Found !</strong>
                      </Alert>
                    </Stack>
                  )
                    : null
                  }
                </div>
              )
            }
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
