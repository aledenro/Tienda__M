package com.tienda_m.service;

import com.tienda_m.domain.Categoria;
import java.util.List;

/**
 *
 * @author alede
 */
public interface CategoriaService {

    //Metodo para obtener los registros de categoria en forma de lista
    public List<Categoria> getCategorias(boolean activo);

    //Se recupera un registro tomando el idCategoria como clave de busqueda
    public Categoria getCategoria(Categoria categoria);

    //Si idCategoria tiene un valor, se modifica ese registro
    //Si idCategoria no tien valor, se inserta un nuevo registro
    public void save(Categoria categoria);

    //Se elimina el registro que tenga el valor de idCategoria pasado
    public void delete(Categoria categoria);
}
