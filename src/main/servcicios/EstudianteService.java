package main.servcicios;

import main.dao.IDao;
import main.entidades.Estudiante;

import java.util.List;

public class EstudianteService {

    private IDao<Estudiante> estudianteIDao;


    public IDao<Estudiante> getEstudianteIDao() {
        return estudianteIDao;
    }

    public void setEstudianteIDao(IDao<Estudiante> estudianteIDao) {
        this.estudianteIDao = estudianteIDao;
    }

    public Estudiante guardarEstudiante(Estudiante est){
        //delegamos la responsabilidad de guardar al DAO
        return estudianteIDao.guardar(est);
    }

    public void eliminarEstudiante(Long id){
        //delegamos la responsabilidad de guardar al DAO
        estudianteIDao.eliminar(id);
    }

    public Estudiante buscarEstudiante(Long id) {
        //delegamos la responsabilidad de guardar al DAO
        return estudianteIDao.buscar(id);
    }

    public List<Estudiante> buscarTodos(){
        //delegamos la responsabilidad de guardar al DAO
        return estudianteIDao.buscarTodos();
    }
}
