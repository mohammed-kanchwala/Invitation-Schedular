import React from 'react'
import Table from 'react-bootstrap/Table';

function TableComponent({
    headers = [],
    tableBody = '',
    ...props
}) {
    return (
        <Table id="partner_table" striped bordered hover>
            <thead>
                <tr>
                    {
                        headers.map((item, index) => {
                            return (<th key={index}>{item}</th>)
                        })
                    }
                </tr>
            </thead>
            {tableBody}
        </Table>
    )
}

export default TableComponent
