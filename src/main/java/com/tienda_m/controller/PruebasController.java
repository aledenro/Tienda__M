package com.tienda_m.controller;

import com.tienda_m.domain.Categoria;
import com.tienda_m.domain.Producto;
import com.tienda_m.service.CategoriaService;
import com.tienda_m.service.ProductoService;
import com.tienda_m.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {

        var lista = productoService.getProductos(false);
        var listaCategorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        model.addAttribute("categorias", listaCategorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listadoIds(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        var producto = categoria.getProductos();
        var listaCategorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", producto);
        model.addAttribute("categorias", listaCategorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado2")
    public String listado2(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);

        return "/pruebas/listado2";
    }

    @PostMapping("/query1")
    public String consultaQuery1(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.metodoJPA(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

    @PostMapping("/query2")
    public String consultaQuery2(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.metodoJPQL(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

    @PostMapping("/query3")
    public String consultaQuery3(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.metodoSQL(precioInf, precioSup);

        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

    @PostMapping("/queryTarea")
    public String queryTarea(@RequestParam(value = "descripcion") String descripcion, Model model) {
        var listaProd = productoService.metodoTarea(descripcion);
        model.addAttribute("productos", listaProd);
        model.addAttribute("descripcion", descripcion);

        return "/pruebas/listado2";
    }
}

