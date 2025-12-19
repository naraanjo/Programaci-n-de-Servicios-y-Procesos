package com.example.apiRest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiRest.entity.Producto;
import com.example.apiRest.service.Iproducto;

@RestController
@RequestMapping("/productos") // Define la ruta base para este controlador
public class ProductoController {

    private Iproducto productoService;

    // Inyección de dependencia del servicio
    public ProductoController(Iproducto productoService) {
        this.productoService = productoService;
    }

    // Post, porque añade datos al server
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.save(producto);
    }
    
    // Get - saca datos del server
    @GetMapping
    public List<Producto> findAll(){
    	return productoService.findAll();
    }
    
    // Get - porque sacamos datos del server
    // http://localhost:8081/id
    @GetMapping("/{id}")
    public Producto findById(@PathVariable Integer id) {
    	return productoService.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        productoService.deleteById(id);
    }

    
    // Put - porque actualiza datos en el server
    @PutMapping
    public Producto update(@RequestBody Producto producto) {
    	return productoService.update(producto);
    }
}