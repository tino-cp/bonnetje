import React from 'react';
import {Box, Typography, Divider, Grid2} from '@mui/material';

function OrderFooter({ totalAmount, finalPrice, vatBreakdown }) {
    const { vatHigh, vatLow, vatTotalLow, vatTotalHigh } = vatBreakdown;

    return (
        <Box sx={{ marginTop: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', marginY: 3 }}>
                <Typography variant="h6" color="text.primary" fontWeight="bold">
                    Subtotaal:
                </Typography>
                <Typography variant="h6" color="primary" fontWeight="bold">
                    €{totalAmount.toFixed(2)}
                </Typography>
            </Box>

            <Divider sx={{ marginY: 3 }} />

            <Grid2 container spacing={2} sx={{ marginBottom: 2 }}>
                <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center'}}>
                    <Typography color="text.primary" fontWeight="bold" noWrap>
                        BTW CATEGORIE
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        BTW BEDRAG
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        INCL.
                    </Typography>
                </Grid2>
            </Grid2>

            <Divider sx={{ marginY: 2 }} />

            <Grid2 container spacing={2} sx={{ marginBottom: 2 }}>
                <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center'}}>
                    <Typography color="text.secondary">
                        BTW Laag (9%):
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <Typography color="text.secondary">
                        €{vatLow.toFixed(2)}
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Typography color="text.secondary">
                        €{vatTotalLow.toFixed(2)}
                    </Typography>
                </Grid2>
            </Grid2>

            <Grid2 container spacing={2} sx={{ marginBottom: 2 }}>
                <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center'}}>
                    <Typography color="text.secondary">
                        BTW Hoog (21%):
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <Typography color="text.secondary">
                        €{vatHigh.toFixed(2)}
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Typography color="text.secondary">
                        €{vatTotalHigh.toFixed(2)}
                    </Typography>
                </Grid2>
            </Grid2>

            <Divider sx={{ marginY: 2 }} />

            <Grid2 container spacing={2} sx={{ marginBottom: 2 }}>
                <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center'}}>
                    <Typography color="text.primary" fontWeight="bold">
                        Totaal BTW:
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        €{(vatLow+vatHigh).toFixed(2)}
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        €{(vatTotalLow+vatTotalHigh).toFixed(2)}
                    </Typography>
                </Grid2>
            </Grid2>

            <Divider sx={{ marginTop: 2, marginBottom: 3 }} />

            <Box sx={{ display: 'flex', justifyContent: 'space-between'}}>
                <Typography variant="h6" color="text.primary" fontWeight="bold">
                    Totaal:
                </Typography>
                <Typography variant="h6" color="primary" fontWeight="bold">
                    €{finalPrice.toFixed(2)}
                </Typography>
            </Box>
        </Box>
    );
}

export default OrderFooter;
