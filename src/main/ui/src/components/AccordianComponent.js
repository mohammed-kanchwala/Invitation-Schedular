import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import LocationOnOutlinedIcon from '@mui/icons-material/LocationOnOutlined';
import LanguageOutlinedIcon from '@mui/icons-material/LanguageOutlined';
import BusinessOutlinedIcon from '@mui/icons-material/BusinessOutlined';
import LocationCityOutlinedIcon from '@mui/icons-material/LocationCityOutlined';
import MyLocationOutlinedIcon from '@mui/icons-material/MyLocationOutlined';

export default function AccordianComponent({
    partner,
}) {
    return (
        <div style={{ width: "600px" }}>
            <Accordion>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    <h5 s>{partner.organization.toUpperCase()}</h5>
                </AccordionSummary>
                <AccordionDetails>
                    <div className='row'>
                        <div className='col-12'>
                            <LanguageOutlinedIcon style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} />
                            {partner.website}
                        </div>
                    </div>
                    <div className='row'>
                        <div className='col-12'>
                            <div className='col-4'>
                                <img src={require('../assets/icons/remote-work.svg').default} alt='remoteWork' height={25} width={25} style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} />
                                {partner.willWorkRemotely ? 'Yes' : 'No'}</div>
                        </div>
                    </div>
                    <div className='row'>
                        <div className='col-12'>
                            <LocationOnOutlinedIcon style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} />{partner.customerLocations}
                        </div>
                    </div>
                    <div className='row'>
                        <div className='col-12'>
                            <Accordion>
                                <AccordionSummary
                                    expandIcon={<ExpandMoreIcon />}
                                    aria-controls="panel1a-content"
                                    id="panel1a-header"
                                >
                                    <BusinessOutlinedIcon />
                                    <h6 style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} >OFFICES:</h6>
                                </AccordionSummary>
                                <AccordionDetails>
                                    <div className='row'>
                                        <div className='col-12'>
                                            <ul>
                                                {partner.offices.map((office) => {
                                                    return (
                                                        <>
                                                            <li key={office.location + partner.organization} style={{ padding: "5px" }}>
                                                                <div className='row'>
                                                                    <div className='col-12'>
                                                                        <LocationCityOutlinedIcon style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} />{office.location}
                                                                    </div>
                                                                </div>
                                                                <div className='row'>
                                                                    <div className='col-12'>
                                                                        <MyLocationOutlinedIcon style={{ display: "inline-block", paddingBottom: "5px", paddingRight: "5px" }} />
                                                                        {office.address}
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </>
                                                    );
                                                })}
                                            </ul>
                                        </div>
                                    </div>
                                </AccordionDetails>
                            </Accordion>
                        </div>
                    </div>

                    {/* offices: {partner.offices} */}
                </AccordionDetails>
            </Accordion>
        </div >
    );
}
