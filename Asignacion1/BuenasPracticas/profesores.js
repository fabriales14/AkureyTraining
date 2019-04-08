const express = require('express');
const router = express.Router();
var mongojs = require('mongojs');
var db = mongojs('mongodb://fabricio:1234@ds247178.mlab.com:47178/matricula_prototipo', ['profesores']);

/* mostrar todos los profesores */
router.get('/profesores', (req, res, next) => {
  db.profesores.find(function (err, profesores) {
    if (err) {
      res.send(err);
    }
    res.json(profesores);
  });
});

/* mostrar un profesor */
router.get('/profesor/:id', (req, res, next) => {
  db.profesores.findOne({ _id: mongojs.ObjectId(req.params.id) }, function (err, profesor) {
    if (err) {
      res.send(err);
    }
    res.json(profesor);
  });
});

// Guardar profesor
router.post('/profesor', (req, res, next) => {
  var profesor = req.body;
  db.profesores.save(profesor, function (err, institucion) {
    if (err) {
      send(err);
    }
    res.json(institucion);
  });
});

/* eliminar un profesor */
router.delete('/profesor/:id', (req, res, next) => {
  db.profesores.remove({ _id: mongojs.ObjectId(req.params.id) }, function (err, profesor) {
    if (err) {
      res.send(err);
    }
    res.json(profesor);
  });
});

// editar profesor
router.put('/profesor/:id', (req, res, next) => {
  var profesor = req.body;
  var profesorEdit = {};

  if (profesor.nombre || profesor.apellidos || profesor.email || profesor.password || profesor.idInstitucion) {
    profesorEdit.nombre = profesor.nombre;
    profesorEdit.apellidos = profesor.apellidos;
    profesorEdit.email = profesor.email;
    profesorEdit.password = profesor.password;
    profesorEdit.idInstitucion = profesor.idInstitucion;
  }

  if (!profesorEdit) {
    res.status(400);
    res.json({
      "error": "Bad data"
    });
  } else {
    db.profesores.update({ _id: mongojs.ObjectId(req.params.id) }, profesorEdit, {}, function (err, profesor) {
      if (err) {
        res.send(err);
      }
      res.json(profesor);
    });
  }
});


module.exports = router;