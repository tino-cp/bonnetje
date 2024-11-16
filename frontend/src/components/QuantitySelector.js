import React from 'react';
import { FormControl, InputLabel, Select, MenuItem, FormHelperText } from '@mui/material';

function QuantitySelector({ product, setProduct }) {
    return (
        <FormControl fullWidth margin="normal" variant="outlined" error={product.quantity < 1 || !product.quantity}>
            <InputLabel id="quantity-label">Quantity</InputLabel>
            <Select
                labelId="quantity-label"
                value={product.quantity || ''}
                onChange={(e) => setProduct({ ...product, quantity: e.target.value })}
                label="Quantity"
                MenuProps={{
                    PaperProps: {
                        style: {
                            maxHeight: 200,
                            width: 100,
                        },
                    },
                }}
            >
                {Array.from({ length: 99 }, (_, i) => (
                    <MenuItem key={i + 1} value={i + 1}>
                        {i + 1}
                    </MenuItem>
                ))}
            </Select>
            {product.quantity < 1 && <FormHelperText>Quantity must be at least 1</FormHelperText>}
        </FormControl>
    );
}

export default QuantitySelector;
