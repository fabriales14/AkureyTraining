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

  constructor(private profesorService: ProfesorService, private estudianteService: EstudianteService, public router: Router) {
    this.profesorService.getProfesores()
      .subscribe(profesores => {
        this.profesores = profesores;
      });
    this.estudianteService.getEstudiantes()
      .subscribe(estudiantes => {
        this.estudiantes = estudiantes;
      });
    var newUser = {
      email: '',
      password:  ''
    }
    this.usuario = newUser;
  }

  logIn(){
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
    alert("Email no encontrado");
  }

  ngOnInit() {
  }

}
