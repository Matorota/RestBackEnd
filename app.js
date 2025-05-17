const swaggerUi = require('swagger-ui-express');
const swaggerDocument = require('./swagger.json'); // OpenAPI spec file

// Serve OpenAPI documentation at /api-docs
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

// Updated data loader functions
const farmers = [
  { id: 1, name: "Farmer 1", description: "Description for Farmer 1" },
  { id: 2, name: "Farmer 2", description: "Description for Farmer 2" }
];

const workers = [
  { id: 1, name: "Worker 1", description: "Description for Worker 1" },
  { id: 2, name: "Worker 2", description: "Description for Worker 2" }
];

// Route to get all farmers
app.get('/api/farmers', (req, res) => {
  res.json(farmers);
});

// Route to get all workers
app.get('/api/worker', (req, res) => {
  res.json(workers);
});

// Route to get all farmers and workers combined
app.get('/api/farmerworker', (req, res) => {
  const combinedData = [
    ...farmers.map(farmer => ({ ...farmer, type: "farmer" })),
    ...workers.map(worker => ({ ...worker, type: "worker" }))
  ];
  res.json(combinedData);
});

// Serve HTML page at /html
app.get('/html', (req, res) => {
  const data = [
    ...farmers.map(farmer => ({ ...farmer, type: "farmer" })),
    ...workers.map(worker => ({ ...worker, type: "worker" }))
  ];
  res.send(`
    <!DOCTYPE html>
    <html>
      <head>
        <title>Data Loader Example</title>
      </head>
      <body>
        <h1>Data Loader Example</h1>
        <ul>
          ${data.map(item => `<li>${item.type.charAt(0).toUpperCase() + item.type.slice(1)} ${item.id}: ${item.description}</li>`).join('')}
        </ul>
      </body>
    </html>
  `);
});

// Start the server on port 8080
const PORT = 8080;
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
