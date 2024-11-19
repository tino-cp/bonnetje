import React from 'react';
import { FormControl, InputLabel, Select } from '@mui/material';

function QuantitySelector({ product, setProduct, generateMenuItems }) {
    return (
        <FormControl fullWidth margin="normal" variant="outlined">
            <InputLabel id="quantity-label">Aantal</InputLabel>
            <Select
                labelId="quantity-label"
                value={product.quantity}
                onChange={(e) => setProduct({ ...product, quantity: e.target.value })}
                label="Aantal"
                MenuProps={{
                    PaperProps: {
                        style: {
                            maxHeight: 200,
                            width: 100,
                        },
                    },
                }}
            >
                {generateMenuItems()}
            </Select>
        </FormControl>
    );
}

export default QuantitySelector;
