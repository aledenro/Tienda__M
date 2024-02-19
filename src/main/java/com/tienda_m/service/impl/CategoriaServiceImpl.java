package com.tienda_m.service.impl;

import com.tienda_m.dao.CategoriaDao;
import com.tienda_m.domain.Categoria;
import com.tienda_m.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alede
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        var lista = categoriaDao.findAll();

        if (activo) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

}
