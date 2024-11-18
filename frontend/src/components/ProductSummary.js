import React, {useEffect} from 'react';
import {Box, Typography, Divider, Grid2, IconButton, MenuItem, Select} from '@mui/material';
import { Close as CloseIcon } from '@mui/icons-material';

function ProductSummary({ products, setProducts, calculateOrder}) {
    const handleDeleteProduct = async (productId) => {
        const updatedProducts = products.filter(prod => prod.id !== productId);
        setProducts(updatedProducts);

        await calculateOrder(updatedProducts);
    };

    const handleQuantityChange = (prodId, newQuantity) => {
        setProducts((prevProducts) =>
            prevProducts.map((prod) =>
                prod.id === prodId ? { ...prod, quantity: newQuantity } : prod
            )
        );
    };

    useEffect(() => {
        calculateOrder(products);
    }, [products, calculateOrder]);

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
                            <IconButton className="deleteButton" onClick={() => handleDeleteProduct(prod.id)} sx={{ color: 'red'}}>
                                <CloseIcon />
                            </IconButton>
                        </Grid2>
                        <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', overflow: 'hidden' }}>
                            <Typography color="text.primary" fontWeight="bold" noWrap>
                                {prod.name}
                            </Typography>
                            <Typography component="div" color="text.secondary" noWrap>
                                Quantity:
                                <Select
                                    value={prod.quantity || ''}
                                    onChange={(e) => handleQuantityChange(prod.id, e.target.value)}
                                    sx={{
                                        width: 60,
                                        height: 30,
                                        marginLeft: 1,
                                        marginTop: 1
                                    }}

                                    MenuProps={{
                                        PaperProps: {
                                            style: {
                                                maxHeight: 200
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
