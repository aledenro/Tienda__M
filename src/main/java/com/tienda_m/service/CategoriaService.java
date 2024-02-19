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
}
