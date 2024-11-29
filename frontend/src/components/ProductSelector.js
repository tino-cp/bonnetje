import React from 'react';
import { FormControl, InputLabel, Select, MenuItem, Typography, Box } from '@mui/material';

function ProductSelector({ availableProducts, product, setProduct, error }) {
    return (
        <FormControl fullWidth margin="normal" variant="outlined" error={!!error}>
            <InputLabel id="productDTO-label">Product</InputLabel>
            <Select
                labelId="productDTO-label"
                value={product.name}
                onChange={(e) => setProduct({ ...product, name: e.target.value })}
                label="Product"
                MenuProps={{
                    PaperProps: {
                        style: {
                            maxHeight: 300,
                            overflowY: 'auto',
                        },
                    },
                }}
            >
                {availableProducts.map((prod) => (
                    <MenuItem key={prod.id} value={prod.name}>
                        <Box sx={{ display: 'flex', flexDirection: 'column', padding: 1 }}>
                            <Typography variant="body1">{prod.name}</Typography>
                            {prod.discount && prod.discount.quantity > 0 && (
                                <Box sx={{ backgroundColor: '#f0f8ff', padding: '4px 8px', marginTop: 1, borderRadius: 1 }}>
                                    <Typography variant="body2" color="green">
                                        <strong>Staffelkorting:</strong> Korting van {prod.discount.discount}%
                                        bij {prod.discount.quantity} stuks of meer.
                                    </Typography>
                                </Box>
                            )}
                        </Box>
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
}

export default ProductSelector;
