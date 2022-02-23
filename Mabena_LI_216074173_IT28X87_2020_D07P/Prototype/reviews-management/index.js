const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql');
const app = express();
app.use(bodyParser.json());
app.use(cors());
var con;
function ConnectToDatabase1() {
    con = mysql.createConnection({
        host: "databases",
        user: "root",
        port: 3306,
        password: "password",
        database: "ReviewsManagementDB"
    });
    con.connect((err) => {
        if (err) throw err;
        console.log("Connected to database!");
    });
}
function ConnectToDatabase() {
    con = mysql.createConnection({
        host: "ReviewsDatabase",
        user: "root",
        port: 3306,
        password: "password1",
        database: "ReviewsManagementDB"
    });
    con.connect((err) => {
        if (err) throw err;
        console.log("Connected to database!");
    });
}
app.post('/review', (req, res) => {
    var { review_rating, review_description, review_Reviewer_id, review_Accommodation_Reviewed } = req.body;
    const sql = "INSERT INTO review (review_rating,review_description,review_Reviewer_id,review_Accommodation_Reviewed) VALUES('" + review_rating + "','" + review_description + "','" + review_Reviewer_id + "','" + review_Accommodation_Reviewed + "')";
    con.query(sql, async (err, result) => {
        if (err) throw err;
        var insertId = result.insertId;
        console.log("added 1 record to reviews")
        res.status(201).json({ "review_id": result.insertId, "review_rating": review_rating, "review_description": review_description, "review_Reviewer_id": review_Reviewer_id, "review_Accommodation_Reviewed": review_Accommodation_Reviewed });
    });
});

app.get('/review/:AccommodationID', (req, res) => {

    var id = req.params.AccommodationID;
    var sql = "SELECT * FROM review WHERE review_Accommodation_Reviewed ='" + id + "'";
    con.query(sql, async (err, result) => {
        if (err) throw err;
        res.status(200).json(result);
    });
});
app.get('/reviews/:accommodationID/:reviewerID', (req, res) => {
    var accommodationID = req.params.accommodationID;
    var reviewerID = req.params.reviewerID;
    var sql = "SELECT * FROM review WHERE review_Accommodation_Reviewed ='" + accommodationID + "' AND review_Reviewer_id ='" + reviewerID + "";
    con.query(sql, (err, result) => {
        if (err) throw err;
        res.status(200).send(result);
    });
});
app.put('/review/:id', (req, res) => {
    var reviewAvailable = "";
    var reviewerID = req.params.id;

    var { review_rating, review_description } = req.body;
    var int_review_rating = parseInt(review_rating, 10);

    const sql = "SELECT * FROM review"
    
    con.query(sql, (err, result) => {
        if (err) throw err;

        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.review_Reviewer_id == reviewerID) {
                reviewAvailable = "Available";
            }
        });
        if (reviewAvailable == "Available") {
            const sql2 = "UPDATE review SET review_rating='" + int_review_rating + "',review_description='" + review_description + "' WHERE review_id='" + reviewerID + "'";

            con.query(sql2, async (err) => {
                if (err) throw error;
                res.status(200).json({ "review_rating": review_rating, "review_description": review_description });
            });
        }
        else {
            res.send("no review found");
        }
    });
});
app.delete('/review/:review_id', (req, res) => {

    var reviewID = req.params.review_id;
});

app.post('/events', (req, res) => {
    console.log('Event Received:', req.body.type);
    res.send({});
});

app.get('/helloworld', (req, res) => {
    res.send('hello world');
});
app.listen(4002, () => {
    ConnectToDatabase1();
    console.log("Application running on port 4002");
});