package com.example.apiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiRest.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
