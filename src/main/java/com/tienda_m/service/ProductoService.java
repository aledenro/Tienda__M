package com.tienda_m.service;

import com.tienda_m.domain.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductoService {

    //recupera una lista de productos
    public List<Producto> getProductos(boolean activo);

    //recupera un registro por id
    public Producto getProducto(Producto producto);

    /*
     *si tiene un valor, modifica el registro
     *si no tiene un valor, inserta el registro
     */
    public void save(Producto producto);

    //elimina el registro por id
    public void delete(Producto producto);

    public List<Producto> metodoJPA(double precioInf, double precioSup);

    public List<Producto> metodoJPQL(double precioInf, double precioSup);

    public List<Producto> metodoSQL(double precioInf, double precioSup);

}
