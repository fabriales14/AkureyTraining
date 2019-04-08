import { Component, OnInit } from '@angular/core';
import { ProfesorService } from '../services/profesor.service';
import { EstudianteService } from '../services/estudiante.service';
import { Profesor } from '../models/profesor';
import { Estudiante } from '../models/estudiante';
import { Router, RouterLinkWithHref } from '@angular/router';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
  providers: [ProfesorService, EstudianteService]
})
export class InicioComponent implements OnInit {
  profesores: Profesor[];
  profesor: Profesor;
  estudiantes: Estudiante[];
  estudiante: Estudiante;
  usuario: {
    email: string;
    password: string;
  }

  // se cambian algunas funciones para que no se encuentre todo en el constructor del componente
  constructor(private profesorService: ProfesorService, 
    private estudianteService: EstudianteService, 
    public router: Router) {
    var newUser = {
      email: '',
      password:  ''
    }
    this.usuario = newUser;
  }

  // se crean las funciones de carga por aparte para cada elemento, en lugar de una sola funcion
  ngOnInit() {
    this.cargarProfesores();
    this.cargarEstudiantes();
  }

  cargarProfesores(){
    this.profesorService.getProfesores()
    .subscribe(profesores => {
      this.profesores = profesores;
    });
  }

  cargarEstudiantes(){
    this.estudianteService.getEstudiantes()
      .subscribe(estudiantes => {
        this.estudiantes = estudiantes;
      });
  }

  // se crean funciones que buscan las credenciales de log in para cada tipo de usuario
  logIn(){
    this.buscarProfesor();
    this.buscarEstudiante();
    alert("Email no encontrado");
  }

  buscarProfesor(){
    for (var i=0; i<this.profesores.length; i++){
      if (this.usuario.email == this.profesores[i].email){
        if (this.usuario.password == this.profesores[i].password){
          this.profesor = this.profesores[i];
          var id = this.profesorService.getId(this.profesor);
          this.router.navigateByUrl('/profesor/'+id);
          return;
        }alert("Contraseña incorrecta"); return;
      }
    }
  }

  buscarEstudiante(){
    for (var i=0; i<this.estudiantes.length; i++){
      if (this.usuario.email == this.estudiantes[i].email){
        if (this.usuario.password == this.estudiantes[i].password){
          this.estudiante = this.estudiantes[i];
          var id = this.estudianteService.getId(this.estudiante);
          this.router.navigateByUrl('/estudiante/'+id);
          return;
        }alert("Contraseña incorrecta"); return;
      }
    }
  }
}
