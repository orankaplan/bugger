var express = require('express');
var bodyParser = require('body-parser');
var morgan = require('morgan');
var router = express.Router();
var app = express();
app.use(morgan('dev'));
app.use(bodyParser.json({limit: "5mb"}));
app.use(bodyParser.raw({limit: '5mb'}));
app.use(bodyParser.text({limit: '5mb'}));
app.use(bodyParser.urlencoded({ limit: "5mb", extended: true}));

// parse various different custom JSON types as JSON
app.use(bodyParser.json({ type: 'application/*+json' }))

// parse some custom thing into a Buffer
app.use(bodyParser.raw({ type: 'application/vnd.custom-type' }))

// parse an HTML body into a string
app.use(bodyParser.text({ type: 'text/html' }))
app.use('/',router);


/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


router.post('/webhook', function(req, res, next) {
  console.log(req.body);
  res.render('index', { title: 'Post' });
});

router.post('//webhook', function(req, res, next) {
  console.log(req.body);
  res.render('index', { title: 'Post' });
});
/// catch 404 and forwarding to error handler
app.use(function (req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});


/// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

app.on('uncaughtException', function (err) {
  console.log('Uncaught exception!', err);
});
module.exports = app;
