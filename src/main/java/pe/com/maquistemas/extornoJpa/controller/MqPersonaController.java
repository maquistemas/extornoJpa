package pe.com.maquistemas.extornoJpa.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.PostRemove;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.com.maquistemas.extornoJpa.entity.MqPersona;
import pe.com.maquistemas.extornoJpa.entity.MqRegion;
import pe.com.maquistemas.extornoJpa.services.IMqPersonaService;
import pe.com.maquistemas.extornoJpa.services.IUploadFileService;



@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class MqPersonaController {
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private IMqPersonaService personaService;
	
	
	@GetMapping("/persona/findall")
	public List<MqPersona> findAll() {
		return personaService.findAll();
	}
	
	@GetMapping("/persona/page/{page}")
	public Page<MqPersona> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return personaService.findAll(pageable);
	}
	
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/persona/find/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		MqPersona persona = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			persona = personaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(persona == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MqPersona>(persona, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/persona/create")
	public ResponseEntity<?> create(@Valid @RequestBody MqPersona persona, BindingResult result ){
		MqPersona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> "El error '"+error.getField()+"'"+error.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			personaNew = personaService.save(persona);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al registrar persona en BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La persona fue creada con éxito");
		response.put("persona", personaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/persona/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody MqPersona persona, @PathVariable Long id, BindingResult result){
		
		MqPersona personaActual = personaService.findById(id);
		MqPersona personaUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> "El error: '"+error.getField()+"'"+error.getDefaultMessage())
					.collect(Collectors.toList());
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(personaActual == null) {
			response.put("mensaje", "No se puede editar la persona con el id: ".concat(id.toString()).concat(" No existe en BD"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			personaActual.setApellido(persona.getApellido());
			personaActual.setNombre(persona.getNombre());
			personaActual.setEmail(persona.getEmail());
			personaActual.setCreateAt(persona.getCreateAt());
			personaActual.setMqregion(persona.getMqregion());
			
			personaUpdate = personaService.save(personaActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "No se editar la persona");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La persona se actualizó correctamente");
		response.put("persona", personaUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/persona/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			MqPersona persona = personaService.findById(id);
			String fotoAnterior = persona.getFoto();
			uploadService.eliminar(fotoAnterior);
			personaService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "No se puso eliminar la persona con id: ".concat(id.toString()));
			response.put("error", "El error: '"+e.getMessage()+"'"+e.getMostSpecificCause().getMessage());
		}
		
		response.put("mensaje", "Se eliminó la persona correctamente");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/persona/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		MqPersona persona = null;
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			
			try {
				nombreArchivo = uploadService.copiar(archivo);
				persona = personaService.findById(id);
			} catch (Exception e) {
				response.put("mensaje", "No se pudo subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String fotoAnterior = persona.getFoto();
			uploadService.eliminar(fotoAnterior);
			persona.setFoto(nombreArchivo);
			personaService.save(persona);
			
			response.put("mensaje", "Se ha subido la imagen correctamente: "+nombreArchivo);
			response.put("persona", persona);
			
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/persona/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/persona/regiones")
	@Secured("ROLE_ADMIN")
	public List<MqRegion> listarRegiones(){
		return personaService.findAllMqRegiones();
	}

}
