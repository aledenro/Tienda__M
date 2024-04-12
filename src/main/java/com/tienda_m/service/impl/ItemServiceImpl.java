package com.tienda_m.service.impl;

import com.tienda_m.domain.Item;
import com.tienda_m.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public List<Item> gets() {
        return listaItems;
    }

    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto().equals(item.getIdProducto())) {
                return i;
            }
        }

        return null;
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;

        for (Item i : listaItems) {
            posicion++;
            if (i.getIdProducto().equals(item.getIdProducto())) {
                existe = true;
                break;
            }
        }

        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void save(Item item) {
        var existe = false;

        for (Item i : listaItems) {
            if (i.getIdProducto().equals(item.getIdProducto())) {
                existe = true;

                if (i.getCantidad() < item.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }

                break;
            }
        }

        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto().equals(item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Override
    public void facturar() {

    }
}
