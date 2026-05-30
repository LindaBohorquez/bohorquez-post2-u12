package com.universidad.productosservice.controller;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@PostMapping
	public ResponseEntity<Producto> crear(@RequestBody CrearProductoRequest request) {
		Producto producto = productoService.crear(request.nombre(), request.precio(), request.stock());
		return ResponseEntity.status(HttpStatus.CREATED).body(producto);
	}

	@GetMapping("/{id}")
	public Producto buscarPorId(@PathVariable Long id) {
		return productoService.buscarPorId(id);
	}

	@PatchMapping("/{id}/stock")
	public Producto actualizarStock(@PathVariable Long id, @RequestBody ActualizarStockRequest request) {
		return productoService.actualizarStock(id, request.stock());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.eliminar(id);
	}

	public record CrearProductoRequest(String nombre, Double precio, Integer stock) {
	}

	public record ActualizarStockRequest(Integer stock) {
	}
}
