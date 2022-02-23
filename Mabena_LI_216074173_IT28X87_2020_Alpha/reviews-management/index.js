const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql');
const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
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
        host: "localhost",
        user: "root",
        port: 3306,
        password: "Inno2173*",
        database: "ReviewsManagementDB"
    });
    con.connect((err) => {
        if (err) throw err;
        console.log("Connected to database!");
    });
}
app.post('/review', (req, res) => {
    var { review_rating, review_description, review_Reviewer_id, review_Accommodation_Reviewed } = req.body;
    var reviewer_id= parseInt(review_Reviewer_id, 10);
    //var accommodation_reviewed = parseInt(review_Accommodation_Reviewed, 10);
    const sql = "INSERT INTO review (review_rating,review_description,review_Reviewer_id,review_Accommodation_Reviewed) VALUES('"+review_rating+"','" + review_description + "','"+ reviewer_id+"','"+review_Accommodation_Reviewed+"')";
    con.query(sql, (err, result) => {
        if (err) throw err;
        var insertId = result.insertId;
        console.log("added 1 record to reviews")
        res.status(201).json({ "review_id": result.insertId, "review_rating": review_rating, "review_description": review_description, "review_Reviewer_id": review_Reviewer_id, "review_Accommodation_Reviewed": review_Accommodation_Reviewed });
    });
});

app.get('/review/accommodation/:id', (req, res) => {

    var id = req.params.id;
    var sql = "SELECT * FROM review WHERE review_Accommodation_Reviewed ='" +id+ "'";
    con.query(sql, (err, result) => {
        if (err) throw err;
        res.status(200).json(result);
    });
});

app.get('/reviews/reviewer/:id', (req, res) => {
    var reviewerID = req.params.id;
    var sql = "SELECT * FROM review WHERE review_Reviewer_id ='"+reviewerID+"'";
    con.query(sql, (err, result) => {
        if (err) throw err;
        res.status(200).send(result);
    });
});
app.put('/review/:id', (req, res) => {
    var reviewAvailable = "";
    var reviewerID = req.params.id;

    var { review_rating, review_description } = req.body;


    const sql = "SELECT * FROM review"
    con.query(sql, (err, result) => {
        if (err) throw err;

        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.review_id == reviewerID) {
                reviewAvailable = "Available";
                return ;
            }
        });
        if (reviewAvailable == "Available") {
            const sql2 = "UPDATE review SET review_rating='" + review_rating + "',review_description='" + review_description + "' WHERE review_id='" + reviewerID + "'";

            con.query(sql2,  (err) => {
                if (err) throw error;
                res.status(200).json({ "review_rating": review_rating, "review_description": review_description });
            });
        }
        else {
            res.status(404).json("no review found");
        }
    });
});
app.delete('/review/:id', (req, res) => {

    var reviewAvailable = "";
    var reviewID = req.params.id;

    var { review_rating, review_description } = req.body;


    const sql = "SELECT * FROM review"
    con.query(sql, (err, result) => {
        if (err) throw err;

        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.review_id == reviewID) {
                reviewAvailable = "Available";
                return ;
            }
        });
        if (reviewAvailable == "Available") {
            const sql2 = "DELETE FROM review WHERE review_id='"+reviewID+"'";

            con.query(sql2,  (err) => {
                if (err) throw error;
                res.status(200).json({"status":"Deleted"});
            });
        }
        else {
            res.status(404).json("no review found");
        }
    });
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