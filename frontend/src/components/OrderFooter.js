import React from 'react';
import { Box, Typography, Divider } from '@mui/material';

function OrderFooter({ totalAmount, finalPrice, vatBreakdown }) {
    const { vatHigh, vatLow } = vatBreakdown;

    return (
        <Box sx={{ marginTop: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginY: 3 }}>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    Subtotal:
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    €{totalAmount.toFixed(2)}
                </Typography>
            </Box>

            <Divider sx={{ marginY: 3 }} />

            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
                <Typography variant="body1" color="text.secondary">
                    VAT Low (9%):
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    €{vatLow.toFixed(2)}
                </Typography>
            </Box>

            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
                <Typography variant="body1" color="text.secondary">
                    VAT High (21%):
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    €{vatHigh.toFixed(2)}
                </Typography>
            </Box>

            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    Total VAT:
                </Typography>
                <Typography variant="body1" color="text.primary" fontWeight="bold">
                    €{(vatHigh+vatLow).toFixed(2)}
                </Typography>
            </Box>

            <Divider sx={{ marginY: 3 }} />

            <Box sx={{ display: 'flex', justifyContent: 'space-between'}}>
                <Typography variant="h6" color="text.primary" fontWeight="bold">
                    Estimated Total:
                </Typography>
                <Typography variant="h6" color="primary" fontWeight="bold">
                    €{finalPrice.toFixed(2)}
                </Typography>
            </Box>
        </Box>
    );
}

export default OrderFooter;
