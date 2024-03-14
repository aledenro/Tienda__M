package com.tienda_m.dao;

import com.tienda_m.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    //consulta ampliada
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    //usa JPQL
    @Query(value = "SELECT p from Producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> metodoJPQL(double precioInf, double precioSup);

    //usa SQL
    @Query(nativeQuery = true, value = "SELECT * from producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> metodoSQL(double precioInf, double precioSup);
}
