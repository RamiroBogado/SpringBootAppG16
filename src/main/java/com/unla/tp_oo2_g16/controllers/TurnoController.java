package com.unla.tp_oo2_g16.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.tp_oo2_g16.helpers.ViewRouteHelper;
import com.unla.tp_oo2_g16.models.dtos.request.ClienteRequestDTO;
import com.unla.tp_oo2_g16.models.dtos.request.TurnoRequestDTO;
import com.unla.tp_oo2_g16.models.entities.Servicio;
import com.unla.tp_oo2_g16.models.entities.Turno;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;
import com.unla.tp_oo2_g16.services.interfaces.ClienteServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.DisponibilidadesServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.EmailServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.EmpleadoServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.ServicioServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.TurnoServiceInterface;
import com.unla.tp_oo2_g16.services.interfaces.UbicacionServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/turno")
public class TurnoController {

	@Autowired
	private ServicioServiceInterface servicioService;
	@Autowired
	private UbicacionServiceInterface ubicacionService;
	@Autowired
	private DisponibilidadesServiceInterface disponibilidadesService;
	@Autowired
	private EmpleadoServiceInterface empleadoService;
	@Autowired
	private TurnoServiceInterface turnoService;
	@Autowired
	private ClienteServiceInterface clienteService;
	@Autowired
	private EmailServiceInterface emailService;

	private ModelMapper modelMapper = new ModelMapper();
	
	/*
	 * PRIMERA VISTA: botón "Solicitar Turno" URL: GET /index
	 */
	@GetMapping("/index")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ModelAndView mostrarInicio() {
		return new ModelAndView(ViewRouteHelper.SOLICITAR_TURNO);
	}

	/*
	 * SEGUNDA VISTA: formulario de turno con servicios,ubicaciones,dias y horarios
	 * URL: GET /formulario-turno-a
	 */
	@GetMapping("/formulario-turno-a")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ModelAndView mostrarFormularioA() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_TURNO_A);

		mAV.addObject("servicios", servicioService.findAll());
		mAV.addObject("ubicaciones", ubicacionService.findAll());
		mAV.addObject("horarios", disponibilidadesService.findAllhorariosDisponibles());

		return mAV;
	}

	/* TERCERA VISTA: formulario de Cliente 
	 * URL: POST /formulario-turno-b   */
	@PostMapping("/formulario-turno-b")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ModelAndView mostrarFormularioB(@RequestParam("servicioId") Integer servicio,
			@RequestParam("ubicacionId") Integer ubicacion,@RequestParam("fecha")LocalDate dia,
			@RequestParam("horario")LocalTime hora, HttpSession session) {

		Servicio servicioAux = servicioService.findById(servicio);
		Ubicacion ubicacionAux = ubicacionService.findById(ubicacion);
		session.setAttribute("servicioElegido", servicioAux);
		session.setAttribute("ubicacionElegida", ubicacionAux);
		session.setAttribute("diaElegido", dia);
		session.setAttribute("horaElegida", hora);

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_TURNO_B);
		mAV.addObject("clienteDTOF", new ClienteRequestDTO());
		return mAV;
	}

	
	/* CUARTA VISTA: confirmacion del turno
	 * URL: POST /confirmacion-turno  */
	@PostMapping("/confirmacion-turno")
	public ModelAndView confirmarTurno(@ModelAttribute("clienteDTOF") ClienteRequestDTO clienteDTO,
			HttpSession session) {

		Servicio servicioAux = (Servicio) session.getAttribute("servicioElegido");
		Ubicacion ubicacionAux = (Ubicacion) session.getAttribute("ubicacionElegida");
		LocalDate diaAux = (LocalDate) session.getAttribute("diaElegido");
		LocalTime horaAux = (LocalTime) session.getAttribute("horaElegida");

		TurnoRequestDTO turno = new TurnoRequestDTO();
	
		Random random = new Random();
		Integer numero = random.nextInt(5) + 1;
		turno.setEmpleado(empleadoService.findById(numero));
		
		turno.setCliente(clienteDTO);
		turno.setServicio(servicioAux);
		turno.setFechaHora(LocalDateTime.of(diaAux, horaAux));
		turno.setObservaciones("");
		turno.setEstado("Pendiente");
		turno.setUbicacion(ubicacionAux);
		turno.setCodigoA(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CONFRIMACION_TURNO);
		
		mAV.addObject("turno", turno);
		
		session.setAttribute("turnoDTO", turno);
	
		return mAV;
	}
	
	/* QUINTA VISTA: turno guardado
	 * URL: POST /guardar-turno   */
	@PostMapping("/guardar-turno")
	public ModelAndView guardarTurno (HttpSession session) {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GUARDAR_TURNO);
		
		Turno turnoE = modelMapper.map(session.getAttribute("turnoDTO"), Turno.class);
				
		clienteService.save(turnoE.getCliente());
		turnoService.save(turnoE);
						
		emailService.sendEmail(turnoE.getCliente().getEMail(), "Info turno", "Su codigo de turno es " + turnoE.getCodigoA());
		
		return mAV; 
		
	}
	
	
	/* PRIMERA VISTA ANULACION: formulario de anulacion
	 * URL: GET /anular-turno  */
	@GetMapping("/anular-turno")
    public ModelAndView mostrarFormularioAnulacion() {
		
        return new ModelAndView(ViewRouteHelper.ANULAR_TURNO);
    }
	
	/* SEGUNDA VISTA ANULACION: vista de anulacion correcta
	 * URL: POST /anulacionCorrecta  */
    @PostMapping("/anulacionCorrecta")
    public ModelAndView anularTurno(
            @RequestParam("codigo") String codigoA) {

        Turno turnoAux = turnoService.findByCodigoA(codigoA);

        turnoAux.setEstado("Anulado");
        
        turnoService.save(turnoAux);

        return new ModelAndView(ViewRouteHelper.ANULACIONCORRECTA_TURNO);
        
    }

}
