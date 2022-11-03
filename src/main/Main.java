package main;

import main.dao.impl.EstudianteDAOH2;
import main.entidades.Estudiante;
import main.servcicios.EstudianteService;

public class Main {
    public static void main(String[] args) {

        Estudiante estudiante = new Estudiante();

        estudiante.setId(1L);
        estudiante.setNombre("Javier");
        estudiante.setApellido("Raba");

        EstudianteService estudianteService = new EstudianteService();
        //seteamos una estrategia de persistencia es decir, un DAO
        estudianteService.setEstudianteIDao(new EstudianteDAOH2());

        estudianteService.guardarEstudiante(estudiante);


        //System.out.println();




    }
}