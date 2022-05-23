import React from 'react'
import Form from 'react-bootstrap/Form'

function InputComponent({
    labelName = '',
    placeHolder = '',
    type = 'text',
    className = 'mb-3',
    controlId = '',
    onChange,
    value = '',
    name = '',
    ...props
}) {
    return (
        <Form.Group className={className} controlId={controlId}>
            <Form.Label>{labelName}</Form.Label>
            <Form.Control type={type} placeholder={placeHolder} onChange={onChange} name={name} value={value} />
        </Form.Group>)
}

export default InputComponent
