const swaggerUi = require('swagger-ui-express');
const swaggerDocument = require('./swagger.json'); // OpenAPI spec file

// Matumbos yra cia nieko kito
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

// Add a new farmer
app.post('/api/farmers', (req, res) => {
  const { name, description } = req.body;
  const newFarmer = { id: farmers.length + 1, name, description };
  farmers.push(newFarmer);
  res.status(201).json(newFarmer);
});

// Update a farmer
app.put('/api/farmers', (req, res) => {
  const { id, name, description } = req.body;
  const farmer = farmers.find(f => f.id === id);
  if (farmer) {
    farmer.name = name;
    farmer.description = description;
    res.json(farmer);
  } else {
    res.status(404).json({ message: "Farmer not found" });
  }
});

// Delete a farmer
app.delete('/api/farmers', (req, res) => {
  const { id } = req.query;
  const index = farmers.findIndex(f => f.id == id);
  if (index !== -1) {
    farmers.splice(index, 1);
    res.json({ message: "Farmer deleted successfully" });
  } else {
    res.status(404).json({ message: "Farmer not found" });
  }
});

// Route to get all workers
app.get('/api/worker', (req, res) => {
  res.json(workers);
});

// Add a new worker
app.post('/api/worker', (req, res) => {
  const { name, description } = req.body;
  const newWorker = { id: workers.length + 1, name, description };
  workers.push(newWorker);
  res.status(201).json(newWorker);
});

// Update a worker
app.put('/api/worker', (req, res) => {
  const { id, name, description } = req.body;
  const worker = workers.find(w => w.id === id);
  if (worker) {
    worker.name = name;
    worker.description = description;
    res.json(worker);
  } else {
    res.status(404).json({ message: "Worker not found" });
  }
});

// Delete a worker
app.delete('/api/worker', (req, res) => {
  const { id } = req.query;
  const index = workers.findIndex(w => w.id == id);
  if (index !== -1) {
    workers.splice(index, 1);
    res.json({ message: "Worker deleted successfully" });
  } else {
    res.status(404).json({ message: "Worker not found" });
  }
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

