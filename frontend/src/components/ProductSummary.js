import React from 'react';
import { Box, Typography, Divider, Grid2, IconButton } from '@mui/material';
import { Close as CloseIcon } from '@mui/icons-material';

function ProductSummary({ products, onDeleteProduct }) {
    return (
        <Box sx={{ marginBottom: 2 }}>
            {products.map((prod) => (
                <Box
                    key={prod.id}
                    sx={{
                        position: 'relative',
                        '&:hover .deleteButton': {
                            opacity: 1,
                            visibility: 'visible',
                        },
                    }}
                >
                    <Grid2 container spacing={2}>
                        <Grid2 sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                            <IconButton className="deleteButton" onClick={() => onDeleteProduct(prod.id)} sx={{ color: 'red'}}>
                                <CloseIcon />
                            </IconButton>
                        </Grid2>
                        <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', overflow: 'hidden' }}>
                            <Typography color="text.primary" fontWeight="bold" noWrap>
                                {prod.name}
                            </Typography>
                            <Typography color="text.secondary" noWrap>
                                Quantity: {prod.quantity}
                            </Typography>
                        </Grid2>

                        {prod.quantity > 1 && (
                            <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center', overflow: 'hidden' }}>
                                <Typography variant="body1" color="text.primary">
                                    €{prod.price.toFixed(2)}
                                </Typography>
                            </Grid2>
                        )}

                        <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end', overflow: 'hidden' }}>
                            <Typography variant="body1" color="text.primary">
                                €{(prod.quantity * prod.price).toFixed(2)}
                            </Typography>
                        </Grid2>
                    </Grid2>
                    <Divider sx={{ marginY: 1 }} />
                </Box>
            ))}
        </Box>
    );
}

export default ProductSummary;
