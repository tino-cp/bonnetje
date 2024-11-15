'use client';

import { useEffect, useState } from 'react';
import { Container, Typography, CircularProgress, Alert } from '@mui/material';

export default function Home() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/covadistributie/test/helloWorld')
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.text();
        })
        .then((data) => {
          setData(data);
          setLoading(false);
        })
        .catch((err) => {
          setError(err.message);
          setLoading(false);
        });
  }, []);

  return (
      <Container maxWidth="sm" style={{ marginTop: '50px', textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Hello World API Interaction
        </Typography>
        {loading && <CircularProgress />}
        {error && <Alert severity="error">{error}</Alert>}
        {data && (
            <Typography variant="h6" style={{ marginTop: '20px' }}>
              API Response: {data}
            </Typography>
        )}
      </Container>
  );
}
