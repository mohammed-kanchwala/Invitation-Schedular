import React from 'react'
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';

export default function InputComponent({
    label = '',
    type = 'text',
    variant = 'filled',
    onChange,
    ...props
}) {
    return (

        <TextField
            id="filled-required"
            label={label}
            type={type}
            onChange={onChange}
            variant={variant}
            InputLabelProps={{
                shrink: true,
            }}
            {...props}
            InputProps={{
                startAdornment: <InputAdornment position="start">km</InputAdornment>,
            }}
        />
    );
}
