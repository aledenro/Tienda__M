package com.tienda_m.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author alede
 */
@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long idCategoria;

    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    @OneToMany
    @JoinColumn(name = "id_categoria", updatable = false)
    private List<Producto> productos;
}
