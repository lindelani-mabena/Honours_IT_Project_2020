const express = require('express');
const bodyParser = require('body-parser');
const bcrypt = require('bcrypt');
const axios = require('axios');
const cors = require('cors');
const mysql = require('mysql');
const moment = require('moment');
const app = express();
app.use(bodyParser.json());
app.use(cors())
app.use(bodyParser.urlencoded({ extended: false }));
var connection;
function ConnecttoDatabase1() {
    connection = mysql.createConnection({
        host: "databases",
        user: "root",
        port: 3306,
        password: "password",
        database: "PeopleManagementDB"
    });

    connection.connect((err) => {
        if (err) throw err;
        console.log("Connected To Database!");
    });
}
function ConnecttoDatabase() {
    connection = mysql.createConnection({
        host: "localhost",
        user: "root",
        port: 3306,
        password: "Inno2173*",
        database: "PeopleManagementDB"
    });

    connection.connect((err) => {
        if (err) throw err;
        console.log("Connected to database!");
    });
}
app.put('/profile/update/:user_id',  (req, res) => {

    var user_id = req.params.user_id;
    const {res_seeker_religion, res_seeker_employmentstatus_id} = req.body;
    var id = parseInt(user_id, 10);
    var tempid = 0;
    var tempEmploymentStatus = parseInt(res_seeker_employmentstatus_id, 10);

    const sql = "SELECT * FROM residence_seekers";
    connection.query(sql, (err, result) => {
        if (err) throw error;

        Object.keys(result).forEach((key) => {

            var row = result[key];
            if (row.user_id == id) {
                tempid = row.user_id;
            }
        });

        if (tempid != 0) {
            const sql2 = "UPDATE residence_seekers SET res_seeker_religion='" + res_seeker_religion + "',res_seeker_employmentstatus_id='" + tempEmploymentStatus + "'  WHERE user_id='"+id+"'";
            connection.query(sql2,  (err, result) => {
                if (err) throw err;
                res.status(200).json({ "user_id": user_id, "res_seeker_religion": res_seeker_religion, "res_seeker_employmentstatus_id": res_seeker_employmentstatus_id });
            });
        }
        else {
            res.send("User not found!");
        }
    });
});

app.post('/register/manager', (req, res) => {

    const { user_id } = req.body;
    var id;
    var tempid = parseInt(user_id, 10);
    var exists = "";
    const sql = "SELECT * FROM users";
    connection.query(sql, (err, result) => {
        if (err) throw error;
        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.user_id == tempid) {
                id = row.user_id;
            }
        });
        const sql = "SELECT * FROM Residence_Manager";
        connection.query(sql, (err, result) => {
            if (err) throw err;
            Object.keys(result).forEach((key) => {
                var row = result[key];
                if (row.user_id == id) {
                    exists = "exists";
                }
            });
            if (exists == "") {
                tempid = parseInt(id, 10);
                const sql1 = "INSERT INTO Residence_Manager(user_id) VALUES('" + tempid + "')"
                connection.query(sql1,  (err,result, fields) => {
                    if (err) throw err;

                    res.status(201).json({"manager_id": result.insertId });
                });
            }
            else {
                res.status(400).json({ "error": "The residence manager already exists, proceed to login" });
            }
        })
    });

});
app.post('/register/seeker', (req, res) => {

    const { user_id, res_seeker_gender, res_seeker_DateofBirth, res_seeker_race, res_seeker_religion, res_seeker_homelanguage, res_seeker_employmentstatus_id} = req.body;
    console.log("date " + res_seeker_DateofBirth);
    var id;
    var tempid = parseInt(user_id, 10);
    var exists = "";
    const sql = "SELECT * FROM users";
    connection.query(sql, (err, result) => {
        if (err) throw error;
        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.user_id == tempid) {
                id = row.user_id;
            }
        });
        const sql = "SELECT * FROM residence_seekers";
        connection.query(sql, (err, result, fields) => {
            if (err) throw error;
            Object.keys(result).forEach((key) => {
                var row = result[key];
                if (row.user_id == id) {
                    exists = "exists";
                }
            });
            if (exists == "") {
                tempid = parseInt(id, 10);
                var employment_status = parseInt(res_seeker_employmentstatus_id, 10);

                var then = new Date(res_seeker_DateofBirth);
                var formattedDate = moment(then).format('YYYY-MM-DD');

                const sql1 = "INSERT INTO residence_seekers(user_id,res_seeker_gender,res_seeker_Date0fBirth,res_seeker_race,res_seeker_religion,res_seeker_homelanguage,res_seeker_employmentstatus_id) VALUES('" + tempid + "','" + res_seeker_gender + "','" + formattedDate + "','" + res_seeker_race + "','" + res_seeker_religion + "','" + res_seeker_homelanguage + "','" + employment_status + "')"
                connection.query(sql1,  (err, result) => {
                    if (err) throw err;

                    res.status(201).json({ "seeker_id":result.insertId, "res_seeker_gender": res_seeker_gender, "res_seeker_Date0fBirth": formattedDate, "res_seeker_race": res_seeker_race, "res_seeker_religion": res_seeker_religion, "res_seeker_homelanguage": res_seeker_homelanguage, "res_seeker_employmentstatus_id": res_seeker_employmentstatus_id });
                });
            }
            else {
                res.status(400).json({"error": "The residence seeker already exists, proceed to login" });
            }
        })
    });
});
app.post('/register',  (req, res) => {
    var {user_name, user_title, user_emailaddress, user_password, user_confirmpassword} = req.body;
    user_password =user_password + "";
     user_confirmpassword =user_confirmpassword+"";

    var email = "";
    const sql = "SELECT * FROM users";

    connection.query(sql, (err, result, fields) => {
        console.log("result" + user_emailaddress);
        if (err) throw err;
        Object.keys(result).forEach((key) => {
            var row = result[key];

            if (row.user_emailaddress == user_emailaddress) {
                email = "available";
                return;
            }
        });
        if (email == "") {

            if (user_password == user_confirmpassword) {
                bcrypt.hash(user_password, 10, function (err, hash) {

                    if (err) throw error;
                    var sql1 = "INSERT INTO users(user_name, user_title, user_emailaddress, user_password) VALUES('"+user_name+"','"+user_title+"','"+user_emailaddress+"', '"+hash+"')";

                    connection.query(sql1, async (err, result) => {
                        if (err) throw error;
                        console.log("Registered user");
                        var id = result.insertId;
                        res.status(201).json({ "user_id": result.insertId, "user_name": user_name, "user_title": user_title, "user_emailaddress": user_emailaddress, "user_password": user_password, "user_confirmpassword": user_confirmpassword });

                    });
                });
            }
            else {
                res.status(400).json({ "error": "confirm password does not equal password" });
            }
        }
        else {
            res.status(400).json({ "error": "email already exists" });
        }
    });
});
app.post('/login', (req, res) => {
    const { user_emailaddress, user_password } = req.body;
    var { email, password, id } = "";
    const sql = "SELECT * FROM users";

    connection.query(sql, (err, result, fields) => {
        if (err) throw error;
        Object.keys(result).forEach((key) => {
            var row = result[key];
            if (row.user_emailaddress == user_emailaddress) {

                email = row.user_emailaddress;
                password = row.user_password;
                
                id = row.user_id;
                return;
            }
        });
        bcrypt.compare(user_password, password,  function (err, result) {
            if (result) {
                res.status(200).json({ "user_id": id,"user_emailaddress":email});
            }
            else {
                res.status(400).json({ "error": "Incorrect Username or Password" })
            }
        });
    });
});

app.get('/usertype/:id', (req,res)=>
{
    var id = req.params.id;
    var res_seeker_religion;
    var res_seeker_employmentstatus_id;
    var userType=""; 
    const sql = "SELECT * FROM residence_seekers WHERE user_id= '"+id+"'";

    connection.query(sql, (err,result, fields)=>{
        if(err) throw error;
        Object.keys(result).forEach((key)=>
        {
            var row = result[key];
            if(row.user_id==id)
            {  
                res_seeker_religion = row.res_seeker_religion;
                res_seeker_employmentstatus_id = row.res_seeker_employmentstatus_id;
                userType ="ResidenceSeeker"
                return;
            }
         });
        });
    const sql1 = "SELECT * FROM residence_manager WHERE user_id= '"+id+"'";
    connection.query(sql1, (err,result, fields)=>{
        if(err) throw error;
        Object.keys(result).forEach((key)=>
        {
            var row = result[key];
            if(row.user_id==id)
            {  
                userType ="Manager"
                return;
            }
         });


         if(userType == "Manager")
         {
            res.status(200).json({userType:"Manager"});
         }
         else if(userType == "ResidenceSeeker")
         {
             res.status(200).json({"userType":"Residence_Seeker", "res_seeker_religion": res_seeker_religion, "res_seeker_employmentstatus_id":res_seeker_employmentstatus_id});
         }
         else
         {
             res.status(400).json({userType:"Not Found"});
         }

    });

});

app.post('/events', (req, res) => {
    console.log('Event Received:', req.body.type);
    res.send({});
    
});

app.get('/helloworld', (req, res) => {
    res.send("hello world!");
});

app.listen(4000, () => {
    ConnecttoDatabase1();
    console.log("Application running on port 4000");
});
