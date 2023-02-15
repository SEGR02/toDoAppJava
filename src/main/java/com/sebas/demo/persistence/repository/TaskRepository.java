package com.sebas.demo.persistence.repository;

import com.sebas.demo.persistence.entity.Task;
import com.sebas.demo.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> { //nombre de la entidad, tipo de dato del identificador
    public List<Task> findAllByTaskStatus(TaskStatus status);
    @Modifying //indica que es una query de actualizacion
    @Query(value = "UPDATE TASK SET IS_FINISHED=true WHERE ID=:id", nativeQuery = true) // es como si fuera a funcionar automaticamente con gestor de db por defecto
    public void markTaskAsFinished(@Param("id") Long id);
}
//primero repositorio
// funcion de repositorio
// es decir el metodo con el cual nos vamos a traer el info con o sin where con un solo modelo o varios etc