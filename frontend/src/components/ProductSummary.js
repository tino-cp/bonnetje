import React from 'react';
import { Box, Typography, Divider } from '@mui/material';

function ProductSummary({ products }) {
    return (
        <Box sx={{ marginBottom: 2 }}>
            {products.map((prod, index) => (
                <Box key={prod.id}>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                        <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                            <Typography color="text.primary" fontWeight="bold">
                                {prod.name}
                            </Typography>
                            <Typography color="text.secondary">
                                Quantity: {prod.quantity} | Price: €{prod.price.toFixed(2)}
                            </Typography>
                        </Box>
                        <Typography variant="body1" color="text.primary" textAlign="right">
                            €{(prod.quantity * prod.price).toFixed(2)}
                        </Typography>
                    </Box>
                    <Divider sx={{ marginY: 1 }} />
                </Box>
            ))}
        </Box>
    );
}

export default ProductSummary;
