package com.tienda_m.service;

import com.tienda_m.domain.Producto;
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
}
