const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const mysql = require('mysql');
const app = express();
var con;
function ConnecttoDatabase1() {
   con = mysql.createConnection({
      host: "databases",
      user: "root",
      port: 3306,
      password: "password",
      database: "accommodationsDB"
   });
   con.connect(function (err) {
      if (err) throw err;
      console.log("Connected to database!");
   });
}
function ConnecttoDatabase() {
   con = mysql.createConnection({
      host: "accommodations-database",
      user: "root",
      port: 3306,
      password: "password",
      database: "accommodationsDB"
   });
   con.connect(function (err) {
      if (err) throw err;
      console.log("Connected to database!");
   });
}
app.use(bodyParser.json());
app.use(cors());

app.post('/addaccommodation', (req, res) => {
   const { accommodation_name, accommodation_address_id, accommodation_gym, accommodation_security, accommodation_washingmachines, accommodation_wifi, manager_id, accommodation_type_id } = req.body;
   const sql = "INSERT INTO Accommodations(accommodation_name,accommodation_address_id, accommodation_gym, accommodation_security, accommodation_washingmachines, accommodation_wifi, manager_id, accommodation_type_id) VALUES('" + accommodation_name + "', '" + accommodation_address_id + "','" + accommodation_gym + "','" + accommodation_security + "','" + accommodation_washingmachines + "','" + accommodation_wifi + "','" + manager_id + "','" + accommodation_type_id + "')";
   con.query(sql, async (err, result) => {
      if (err) throw err;
      console.log("1 record inserted");
      res.status(201).json({ "accommodation_id": result.insertId, "accommodation_name": accommodation_name, "accommodation_address_id": accommodation_address_id, "accommodation_gym": accommodation_gym, "accommodation_security": accommodation_security, "accommodation_washingmachines": accommodation_washingmachines, "accommodation_wifi": accommodation_wifi, "manager_id": manager_id, "accommodation_type_id": accommodation_type_id });
   });
}
);
app.get('/accommodations', (req, res) => {

   con.query("SELECT * FROM Accommodations", async (err, result, fields) => {
      if (err) throw err;
      res.status(200).json(result);
   });
});
app.get('/accommodations/:id', (req, res) => {

   const id = req.params.id;
   const sql = "SELECT * FROM Accommodations WHERE Accommodation_id = +'" + id + "'";
   con.query(sql, async (err, result, fields) => {
      if (err) throw err;
      res.status(200).json(result);
   });
});
app.put('/updateaccommodation/:id', (req, res) => {
   const accommodation_id = req.params.id

   const { accommodation_name, accommodation_gym, accommodation_security, accommodation_washingmachines, accommodation_wifi } = req.body;

   const sql = "UPDATE Accommodations SET accommodation_name='" + accommodation_name + "',accommodation_gym='" + accommodation_gym + "',accommodation_security='" + accommodation_security + "',accommodation_washingmachines='" + accommodation_washingmachines + "',accommodation_wifi='" + accommodation_wifi + "' WHERE accommodation_id='" + accommodation_id + "'";
   con.query(sql, async (err, result) => {
      if (err) throw error;
      res.status(200).json(result);
   });
});

app.get('/helloworld', (req, res) => {
   res.json([]);
});

app.post('/events', (req, res) => {
   console.log('Event Received:', req.body.type);
   res.send({});
});
app.listen(4001, () => {
   ConnecttoDatabase1();
   console.log("Application running on port 4001");
});

