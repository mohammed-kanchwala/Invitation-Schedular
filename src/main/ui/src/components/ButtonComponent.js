import React from 'react'
import Button from 'react-bootstrap/Button'

function ButtonComponent({
    labelName = '',
    ...props
}) {
    return (
        <Button {...props}>{labelName}</Button>)
}

export default ButtonComponent
