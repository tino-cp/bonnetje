import React, { useState, useEffect } from 'react';
import { Box, Typography, Divider, Grid2, IconButton, Select, Collapse } from '@mui/material';
import { Close as CloseIcon } from '@mui/icons-material';

function ProductSummary({ products, setProducts, calculateOrder, generateMenuItems, isMainCollapsed, orderResponse }) {    const [visibleProducts, setVisibleProducts] = useState(products.map((p) => p.id));
    const [isModified, setIsModified] = useState(false);

    const handleDeleteProduct = (productId) => {
        const updatedProducts = products.filter((prod) => prod.id !== productId);
        setVisibleProducts((prev) => prev.filter((id) => id !== productId));
        setProducts(updatedProducts);
        setIsModified(true);
    };

    const handleQuantityChange = (prodId, newQuantity) => {
        setProducts((prevProducts) => {
            return prevProducts.map((prod) =>
                prod.id === prodId ? { ...prod, quantity: newQuantity } : prod
            );
        });
        setIsModified(true);
    };


    useEffect(() => {
        if (isModified) {
            calculateOrder(products)
            setIsModified(false);
        }
    }, [isModified, products, calculateOrder]);

    useEffect(() => {
        if (isMainCollapsed) {
            setVisibleProducts(products.map((p) => p.id));
        }
    }, [products, isMainCollapsed]);

    return (
        <Box sx={{ marginBottom: 2 }}>
            <Grid2 container spacing={2}>
                <Grid2 size={6} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', overflow: 'hidden', marginLeft: 7 }}>
                    <Typography color="text.primary" fontWeight="bold" noWrap>
                        PRODUCT
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        P. STUK
                    </Typography>
                </Grid2>

                <Grid2 size="grow" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Typography color="text.primary" fontWeight="bold">
                        BEDRAG
                    </Typography>
                </Grid2>
            </Grid2>
            <Divider sx={{ marginY: 2}} />

            {products.map((prod) => {
                const productSummary = orderResponse?.productSummaries.find((summary) => summary.productId === prod.id);

                return (
                    <Collapse key={prod.id} in={visibleProducts.includes(prod.id)}>
                        <Box>
                            <Grid2 container spacing={2}>
                                <Grid2 sx={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                                    <IconButton className="deleteButton" onClick={() => handleDeleteProduct(prod.id)}
                                                sx={{color: 'red'}}>
                                        <CloseIcon/>
                                    </IconButton>
                                </Grid2>
                                <Grid2 size={6} sx={{display: 'flex', flexDirection: 'column', justifyContent: 'center'}}>
                                    <Typography color="text.secondary" fontWeight="bold" noWrap>
                                        {prod.name}
                                    </Typography>
                                    <Typography component="div" color="text.secondary" noWrap>
                                        Aantal:
                                        <Select
                                            value={prod.quantity}
                                            onChange={(e) => handleQuantityChange(prod.id, e.target.value)}
                                            sx={{
                                                width: 60,
                                                height: 30,
                                                marginLeft: 1,
                                                marginTop: 1
                                            }}
                                            MenuProps={{
                                                PaperProps: {style: {maxHeight: 200}}
                                            }}
                                        >
                                            {generateMenuItems()}
                                        </Select>
                                    </Typography>
                                </Grid2>

                                {prod.quantity > 1 && (
                                    <Grid2 size="grow" sx={{display: 'flex', justifyContent: 'center'}}>
                                        <Typography color="text.primary">
                                            €{prod.price.toFixed(2)}
                                        </Typography>
                                    </Grid2>
                                )}

                                <Grid2 size="grow" sx={{display: 'flex', justifyContent: 'flex-end'}}>
                                    <Typography color="text.primary">
                                        €{productSummary?.subTotal.toFixed(2)}
                                    </Typography>
                                </Grid2>
                            </Grid2>

                            {productSummary?.discountAmount > 0 && (
                                <Grid2 container spacing={2} sx={{ marginTop: 1 }} key={productSummary.productId}>
                                    <Grid2 size={6} sx={{display: 'flex', justifyContent: 'flex-start', marginLeft: 7}}>
                                        <Typography color="text.secondary" fontStyle="italic">
                                            Korting toegepast ({prod.discount.discount}%):
                                        </Typography>
                                    </Grid2>
                                    <Grid2 size="grow" sx={{display: 'flex', justifyContent: 'flex-end'}}>
                                        <Typography color="success.light" fontStyle="italic">
                                            -€{productSummary.discountAmount.toFixed(2)}
                                        </Typography>
                                    </Grid2>
                                </Grid2>
                            )}

                            <Divider sx={{marginY: 1}}/>
                        </Box>
                    </Collapse>
                );
            })}
        </Box>
    );
}

export default ProductSummary;
