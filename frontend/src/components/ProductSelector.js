import React from 'react';
import { TextField, MenuItem } from '@mui/material';

function ProductSelector({ availableProducts, product, setProduct, error }) {
    return (
        <TextField
            select
            label="Product"
            value={product.name}
            onChange={(e) => setProduct({ ...product, name: e.target.value })}
            fullWidth
            margin="normal"
            variant="outlined"
            error={!!error}
        >
            {availableProducts.map((prod) => (
                <MenuItem key={prod.id} value={prod.name}>
                    {prod.name}
                </MenuItem>
            ))}
        </TextField>
    );
}

export default ProductSelector;
