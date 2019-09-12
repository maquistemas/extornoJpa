package pe.com.maquistemas.extornoJpa.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.maquistemas.extornoJpa.oauth.services.PlSqlService;

@CrossOrigin({ "http://localhost:4200" })
@RestController
@RequestMapping("/plsql")
public class PlSqlController {
	
	@Autowired
	private PlSqlService plSqlService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/transferencia")
	public ResponseEntity<?> sp_transferencia(
			@RequestParam("PAR_TRANSF_USUARIO_ID") Integer PAR_TRANSF_USUARIO_ID, 
			@RequestParam("PAR_TRANSF_CUENTA_ORIGEN") Integer PAR_TRANSF_CUENTA_ORIGEN, 
			@RequestParam("PAR_TRANSF_CUENTA_DESTINO") Integer PAR_TRANSF_CUENTA_DESTINO, 
			@RequestParam("PAR_TRANSF_MONTO") BigDecimal PAR_TRANSF_MONTO){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			String mensaje = plSqlService.sp_transferencia(PAR_TRANSF_USUARIO_ID, PAR_TRANSF_CUENTA_ORIGEN, PAR_TRANSF_CUENTA_DESTINO, PAR_TRANSF_MONTO);
			response.put("mensaje", mensaje);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response.put("mensaje", "No se pudo hacer la transferencia");
			response.put("error", e.getMessage() + ":  "+ e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/extorno")
	public ResponseEntity<?> sp_extorno(@RequestParam("PAR_TRANSF_ID") Integer PAR_TRANSF_ID, @RequestParam("PAR_USUARIO_ID") Integer PAR_USUARIO_ID){
		Map<String, Object> response = new HashMap<>();
		
		try {
			String mensaje = plSqlService.sp_extorno(PAR_TRANSF_ID, PAR_USUARIO_ID);
			response.put("mensaje", mensaje);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response.put("mensaje", "no se pudo realizar el extorno");
			response.put("error", e.getMessage()+ ": "+ e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
