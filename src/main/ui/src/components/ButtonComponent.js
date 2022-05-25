import React from 'react'
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button'

export default function ButtonComponent({
    label = '',
    variant = '',
    ...props
}) {
    return (
        <Stack spacing={2} direction="row">
            <Button {...props} variant={variant}>{label}</Button>
        </Stack>
    );
}
