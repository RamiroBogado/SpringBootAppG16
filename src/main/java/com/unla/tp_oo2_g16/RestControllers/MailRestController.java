package com.unla.tp_oo2_g16.RestControllers;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.tp_oo2_g16.dtos.EmailDTO;
import com.unla.tp_oo2_g16.dtos.EmailFileDTO;
import com.unla.tp_oo2_g16.services.interfaces.EmailServiceInterface;

@RestController
@RequestMapping("/v1")
public class MailRestController {
	
	@Autowired
	private EmailServiceInterface emailService;
	
	
	@PostMapping("/sendMessage")
	public ResponseEntity<?> reciveRequestEmail(@RequestBody EmailDTO emailDTO){
		
		System.out.println("Mensaje Recibido" + emailDTO);
		
		emailService.sendEmail(emailDTO.toUser(), emailDTO.subject(), emailDTO.message());
		
		Map<String, String> response = new HashMap<>();
		
		response.put("estado", "Enviado");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/sendMessageFile")
	public ResponseEntity<?> reciveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){
		
		try {
			
			String fileName = emailFileDTO.file().getName();
			
			Path path = Paths.get("src/main/resources/files", fileName);
			Files.createDirectories(path.getParent());
			Files.copy(emailFileDTO.file().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
			File file = path.toFile();
			
			emailService.sendEmailWithFIle(emailFileDTO.toUser(), emailFileDTO.subject(), emailFileDTO.message(), file);
			
			Map<String, String> response = new HashMap<>();
			
			response.put("estado", "Enviado");
			response.put("archivo", "fileName");
			
			return ResponseEntity.ok(response);
			
		}catch(Exception e){
			
			throw new RuntimeException("Error al enviar el Email con el archivo" + e.getMessage());
		}
		
		
		
		
	}
}
