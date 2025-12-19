package com.example.apiRest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apiRest.entity.Producto;
import com.example.apiRest.repository.ProductoRepository;



@Service
public class ProductoServiceImpl implements Iproducto {

	private ProductoRepository productoRepository;
	
	public  ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Integer id) {
		return productoRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		productoRepository.deleteById(id);
		
	}
	
	@Override
	public Producto update(Producto producto) {
		Producto productoBd = productoRepository.findById(producto.getId()).get();
		
		productoBd.setNombre(producto.getNombre());
		productoBd.setPrecio(producto.getPrecio());
		productoBd.setDetalle(producto.getDetalle());
		
		return productoRepository.save(productoBd);
	}
}
