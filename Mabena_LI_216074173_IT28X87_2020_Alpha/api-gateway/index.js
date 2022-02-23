const gateway = require('fast-gateway');
const server = gateway({
    middlewares: [
    ],
    routes: [
        {
            prefix: '/accommodation',
            target: 'http://127.0.0.1:4001'
        },
        {
            prefix: '/reviews',
            target: 'http://127.0.0.1:4002'
        },
        {
            prefix: '/authentication',
            target: 'http://127.0.0.1:4000'
        },
        {
            prefix: '/recommendations',
            target: 'http://127.0.0.1:4003'
        }
    ]
})
server.start(7000).then(server => {
    console.log("Server running on port 7000");
});