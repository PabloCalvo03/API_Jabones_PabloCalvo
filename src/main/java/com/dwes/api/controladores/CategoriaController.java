package com.dwes.api.controladores;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwes.api.entidades.Categoria;

import com.dwes.api.errores.CategoriaNotFoundException;
import com.dwes.api.servicios.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

	
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Obtener todos las categorias", description = "Devuelve una lista paginada de categorias")
    @ApiResponse(responseCode = "200", description = "Lista de categorias obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No hay categorias disponibles")
    @ApiResponse(responseCode = "400", description = "Parámetros de solicitud incorrectos")
    public ResponseEntity<?> getAllCategorias(
            Pageable pageable) {

        logger.info("## getAllCategorias ##");
    
        Page<Categoria> page = categoriaService.findAll(pageable);
        return ResponseEntity.ok(page);

    }
  

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoria por ID", description = "Devuelve una categoria específica por su ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrado",
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) })
    @ApiResponse(responseCode = "404", description = "Cateogorias no encontrado")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        logger.info("## getCategoriaById ## id:({}) " + id);
        Categoria categoria = categoriaService.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException("Categoria con ID " + id + " no encontrada"));
        return ResponseEntity.ok(categoria);
    }
    

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria", description = "Actualiza los detalles de una categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada para actualizar")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
    	  logger.info("## actualizarCategoria id({}) ##",id);
    	if (!categoriaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id); // Establece el ID del objeto a actualizar
        Categoria categoriaActualizada = categoriaService.save(categoria); // Utiliza el servicio para guardar el jabón actualizado
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una categoria", description = "Elimina una categoria existente por su ID")
    @ApiResponse(responseCode = "204", description = "Categoria eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada para eliminar")
    public ResponseEntity<Void> borrarCategoria(@PathVariable Long id) {
    	  logger.info("## borrarCategoria id:{} ##",id);
        if (!categoriaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente una categoria", description = "Actualiza parcialmente los detalles de una categoria")
    @ApiResponse(responseCode = "200", description = "Categoria actualizada parcialmente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada para actualización parcial")
    public ResponseEntity<Categoria> actualizarParcialmenteCategoria(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        if (!categoriaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Categoria categoriaActual = categoriaService.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Jabón con ID " + id + " no encontrado"));

        // Aplica las actualizaciones parciales a los campos del jabón
        if (updates.containsKey("nombre")) {
        	categoriaActual.setNombre((String) updates.get("nombre"));
        }
        if (updates.containsKey("descripcion")) {
        	categoriaActual.setDescripcion((String) updates.get("descripcion"));
        }
        // Guarda el jabón actualizado
        Categoria categoriaActualizada = categoriaService.save(categoriaActual);
        return ResponseEntity.ok(categoriaActualizada);
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva categoria", description = "Crea una nueva categoria y la guarda en la base de datos")
    @ApiResponse(responseCode = "201", description = "Categoria creada con éxito")
    @ApiResponse(responseCode = "400", description = "Datos proporcionados para la nueva categoria son inválidos")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria nuevaCategoria) {
    	
    	logger.info("## crearCategoria ##");
        // Realiza la validación y lógica de negocio necesaria antes de guardar el nuevo jabón
        // Por ejemplo, puedes verificar si ya existe un jabón con el mismo nombre

        // Guarda el nuevo jabón en la base de datos
        Categoria categoriaCreada = categoriaService.save(nuevaCategoria);

        // Devuelve una respuesta con el jabón creado y el código de estado 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

}