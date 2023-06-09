package com.gestion.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.entity.Empleado;

@Repository
public interface IEmpleado extends JpaRepository<Empleado, Long> {

}
