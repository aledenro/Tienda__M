package com.tienda_m.dao;

import com.tienda_m.domain.Categoria;
import com.tienda_m.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alede
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String Password);

    Usuario findByUsernameOrCorreo(String username, String correo);

    boolean existsByUsernameOrCorreo(String username, String correo);
}
