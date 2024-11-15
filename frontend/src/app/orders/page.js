'use client';

import React, { useState, useEffect } from 'react';
import { Container, Typography, CircularProgress, TextField, Button, Box, MenuItem, Divider, FormHelperText, FormControl } from '@mui/material';

function App() {
    const [availableProducts, setAvailableProducts] = useState([]);
    const [products, setProducts] = useState([]);
    const [product, setProduct] = useState({ name: '', quantity: 1, price: 0 });
    const [totalAmount, setTotalAmount] = useState(0);
    const [vatAmount, setVatAmount] = useState(0);
    const [finalPrice, setFinalPrice] = useState(0);
    const [loading, setLoading] = useState(false);
    const [isFetchingProducts, setIsFetchingProducts] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('http://localhost:8080/covadistributie/products');
                if (!response.ok) {
                    throw new Error('Failed to fetch products');
                }
                const data = await response.json();
                setAvailableProducts(data);
            } catch (error) {
                console.error('Error fetching products:', error);
            } finally {
                setIsFetchingProducts(false);
            }
        };

        fetchProducts();
    }, []);

    const handleAddProduct = () => {
        if (!product.name || product.quantity <= 0) {
            setError('Please select a product before adding it to the order');
            return;
        }

        const selectedProduct = availableProducts.find((p) => p.name === product.name);
        if (selectedProduct && product.quantity >= 1) {
            setProducts(prevProducts => [...prevProducts, { ...selectedProduct, quantity: product.quantity }]);
            setProduct({ name: '', quantity: 1, price: 0 });
            setError('');
        }
    };

    const handleCalculate = async () => {
        if (products.length === 0) {
            setError('Please add at least one product');
            return;
        }

        setLoading(true);
        setError('');

        try {
            const response = await fetch('http://localhost:8080/covadistributie/order/calculate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ products }),
            });

            if (!response.ok) {
                throw new Error('Failed to calculate order');
            }

            const data = await response.json();
            const { totalAmount, vatAmount, finalPrice } = data;

            setTotalAmount(totalAmount);
            setVatAmount(vatAmount);
            setFinalPrice(finalPrice);
        } catch (error) {
            console.error('Error calculating order:', error);
            setError('Error calculating order, please try again');
        }

        setLoading(false);
    };

    const handleQuantityChange = (e) => {
        const quantity = e.target.value ? parseInt(e.target.value, 10) : 1;

        if (quantity < 1) {
            setProduct({ ...product, quantity: 1 });
        } else {
            setProduct({ ...product, quantity });
        }
    };

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
                    width: '100%',
                    maxWidth: 600,
                    border: '1px solid',
                }}
            >
                <Typography variant="h4" gutterBottom align="center">
                    Order Products
                </Typography>

                <TextField
                    select
                    label="Product"
                    value={product.name}
                    onChange={(e) => setProduct({ ...product, name: e.target.value })}
                    fullWidth
                    margin="normal"
                    variant="outlined"
                    error={!!error}
                >
                    {availableProducts.map((prod) => (
                        <MenuItem key={prod.name} value={prod.name}>
                            {prod.name} - €{prod.price}
                        </MenuItem>
                    ))}
                </TextField>

                <TextField
                    label="Quantity"
                    type="number"
                    value={product.quantity || ''}
                    onChange={handleQuantityChange}
                    fullWidth
                    margin="normal"
                    variant="outlined"
                    error={product.quantity < 1 || !product.quantity}
                    helperText={product.quantity < 1 ? 'Quantity must be at least 1' : ''}
                />

                {error && (
                    <Typography variant="body2" color="error" align="center" sx={{ my: 2 }}>
                        {error}
                    </Typography>
                )}

                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleAddProduct}
                    sx={{ my: 2 }}
                    fullWidth
                >
                    Add Product
                </Button>

                <div>
                    <Typography variant="h6" gutterBottom>
                        Added Products:
                    </Typography>

                    <Box sx={{ marginBottom: 2 }}>
                        {products.map((prod, index) => (
                            <Box
                                key={index}
                                sx={{
                                    display: 'flex',
                                    justifyContent: 'space-between',
                                    padding: 2,
                                    borderBottom: '1px solid #E0E0E0',
                                }}
                            >
                                <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                                    <Typography color="text.primary" fontWeight="bold">
                                        {prod.name}
                                    </Typography>
                                    <Typography color="text.secondary">
                                        {prod.quantity} x €{prod.price.toFixed(2)}
                                    </Typography>
                                </Box>
                                <Typography variant="body1" color="text.primary">
                                    €{(prod.quantity * prod.price).toFixed(2)}
                                </Typography>
                            </Box>
                        ))}
                    </Box>

                    <Divider sx={{ marginY: 3 }} />

                    <Box sx={{ marginBottom: 2 }}>
                        <Typography variant="body1" color="text.secondary">
                            Total:
                        </Typography>
                        <Typography variant="body1" color="text.primary" fontWeight="bold" textAlign="right">
                            €{totalAmount.toFixed(2)}
                        </Typography>
                    </Box>

                    <Box sx={{ marginBottom: 2 }}>
                        <Typography variant="body1" color="text.secondary">
                            VAT (21%):
                        </Typography>
                        <Typography variant="body1" color="text.primary" fontWeight="bold" textAlign="right">
                            €{vatAmount.toFixed(2)}
                        </Typography>
                    </Box>

                    <Divider sx={{ marginY: 3 }} />

                    <Box sx={{ marginBottom: 2 }}>
                        <Typography variant="h6" color="text.secondary">
                            Final Price:
                        </Typography>
                        <Typography variant="h6" color="primary" fontWeight="bold" textAlign="right">
                            €{finalPrice.toFixed(2)}
                        </Typography>
                    </Box>

                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleCalculate}
                        fullWidth
                        sx={{ mt: 4 }}
                    >
                        {loading ? <CircularProgress size={24} color="inherit" /> : 'Calculate Price'}
                    </Button>
                </div>
            </Box>
        </Container>
    );
}

export default App;
