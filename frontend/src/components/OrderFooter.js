import React from 'react';
import { Box, Typography, Divider } from '@mui/material';

function OrderFooter({ totalAmount, vatAmount, finalPrice }) {
    return (
        <Box sx={{ marginTop: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
                <Typography variant="body1" color="text.secondary">
                    Subtotal:
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold" textAlign="right">
                    €{totalAmount.toFixed(2)}
                </Typography>
            </Box>

            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
                <Typography variant="body1" color="text.secondary">
                    VAT (21%):
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold" textAlign="right">
                    €{vatAmount.toFixed(2)}
                </Typography>
            </Box>

            <Divider sx={{ marginY: 3 }} />

            <Box sx={{ display: 'flex', justifyContent: 'space-between'}}>
                <Typography variant="h6" color="text.secondary">
                    Estimated Total:
                </Typography>
                <Typography variant="h6" color="primary" fontWeight="bold" textAlign="right">
                    €{finalPrice.toFixed(2)}
                </Typography>
            </Box>
        </Box>
    );
}

export default OrderFooter;
