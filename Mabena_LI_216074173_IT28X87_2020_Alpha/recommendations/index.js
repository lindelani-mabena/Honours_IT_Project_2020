const express = require('express');
const bodyparser = require('body-parser')

const app = express();
app.use(bodyparser);

app.get('/recommendations', (req, res)=>
{

});

app.listen(7001, ()=>
{
    console.log("Application listening on port 7001")
});