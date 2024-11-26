'use client';

import React, { useState, useEffect } from 'react';
import { Container, Typography, CircularProgress, Box, Button, MenuItem, Collapse } from '@mui/material';
import ProductSelector from '@/components/ProductSelector';
import QuantitySelector from '@/components/QuantitySelector';
import ProductSummary from '@/components/ProductSummary';
import OrderFooter from '@/components/OrderFooter';

export default function App() {
    const [availableProducts, setAvailableProducts] = useState([]);
    const [products, setProducts] = useState([]);
    const [product, setProduct] = useState([]);
    const [totalAmount, setTotalAmount] = useState(0);
    const [finalPrice, setFinalPrice] = useState(0);
    const [isFetchingProducts, setIsFetchingProducts] = useState(true);
    const [error, setError] = useState('');
    const [vatBreakdown, setVatBreakdown] = useState({
        vatHigh: 0,
        vatLow: 0,
        vatTotalLow: 0,
        vatTotalHigh: 0
    });
    const [orderResponse, setOrderResponse] = useState({ productResponses: [] });
    const [isMainCollapsed, setIsMainCollapsed] = useState(false);

    useEffect(() => {
        const fetchProducts = async () => {
            setIsFetchingProducts(true);

            try {
                const response = await fetch('http://localhost:8080/bonnetje/products');
                if (!response.ok) {
                    setError('Error fetching products');
                    setIsFetchingProducts(false);
                    return;
                }

                const data = await response.json();
                setAvailableProducts(data);

                if (data.length > 0 && !product.name) {
                    setProduct({ name: '', quantity: 1, price: 0, vat: 0 });
                }

                setIsFetchingProducts(false);
            } catch (error) {
                console.error('Error fetching products:', error);
                setError('Error fetching data');
                setIsFetchingProducts(false);
            }
        };

        fetchProducts();
    }, []);

    useEffect(() => {
        if (products.length === 0) {
            setIsMainCollapsed(false);
        }
    }, [products]);

    const handleAddProduct = async () => {
        if (!product.name) {
            setError('Selecteer een product voordat je het aan de bestelling toevoegt');
            return;
        }

        setError('');

        const selectedProduct = availableProducts.find((p) => p.name === product.name);
        if (selectedProduct && product.quantity > 0) {
            const newProduct = {
                ...selectedProduct,
                quantity: product.quantity,
                id: products.length ? Math.max(...products.map(p => p.id)) + 1 : 1,
            };

            const updatedProducts = [...products, newProduct];
            setProducts(updatedProducts);

            await calculateOrder(updatedProducts);
        }
    };

    const calculateOrder = async (updatedProducts) => {
        const orderDTO = { products: updatedProducts };

        try {
            const response = await fetch('http://localhost:8080/bonnetje/order/calculate', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(orderDTO),
            });

            const data = await response.json();

            setTotalAmount(data.totalAmount);
            setFinalPrice(data.finalPrice);
            setVatBreakdown({ vatHigh: data.vatHigh, vatLow: data.vatLow, vatTotalLow: data.vatTotalLow, vatTotalHigh: data.vatTotalHigh });
            setOrderResponse(data);
        } catch (error) {
            console.error('Error:', error);
            setError('There was an error with your order calculation.');
        }
    };

    const handleMainCollapseEntered = () => {
        setIsMainCollapsed(true);
    };

    function generateMenuItems() {
        return Array.from({ length: 99 }, (_, i) => (
            <MenuItem key={i + 1} value={i + 1}>
                {i + 1}
            </MenuItem>
        ));
    }


    if (isFetchingProducts) {
        return (
            <Container sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <CircularProgress />
            </Container>
        );
    }

    return (
        <Container sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
            <Box
                sx={{
                    backgroundColor: 'white',
                    borderRadius: 2,
                    boxShadow: 5,
                    padding: 4,
                    marginY: 2,
                    width: '100%',
                    maxWidth: 600,
                    border: '1px solid',
                }}
            >
                <Typography variant="h4" gutterBottom align="center">
                    Bonnetje
                </Typography>

                <ProductSelector
                    availableProducts={availableProducts}
                    product={product}
                    setProduct={setProduct}
                    error={error}
                />

                <QuantitySelector product={product} setProduct={setProduct} generateMenuItems={generateMenuItems} />

                {error && (
                    <Typography variant="body2" color="error" align="center" sx={{ my: 2 }}>
                        {error}
                    </Typography>
                )}

                <Button variant="contained" color="primary" onClick={handleAddProduct} sx={{ my: 2 }} fullWidth>
                    Product Toevoegen
                </Button>

                <Collapse in={products.length > 0} onEntered={handleMainCollapseEntered}>
                    <Box>
                        <ProductSummary products={products} setProducts={setProducts} calculateOrder={calculateOrder} generateMenuItems={generateMenuItems} isMainCollapsed={isMainCollapsed} orderResponse={orderResponse}/>
                        <OrderFooter totalAmount={totalAmount} finalPrice={finalPrice} vatBreakdown={vatBreakdown} />
                    </Box>
                </Collapse>
            </Box>
        </Container>
    );
}
