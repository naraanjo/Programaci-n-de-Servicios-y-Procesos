package com.example.apiRest.service;

import java.util.List;

import com.example.apiRest.entity.Producto;

public interface Iproducto {

	Producto save(Producto producto);
	List<Producto> findAll();
	Producto findById(Integer id);
	void deleteById(Integer id);
	Producto update(Producto producto);
}
