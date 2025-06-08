package com.unla.tp_oo2_g16.services.implementations;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.unla.tp_oo2_g16.models.entities.Turno;
import com.unla.tp_oo2_g16.services.interfaces.EmailServiceInterface;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailServiceInterface{
	
	@Value("${EMAIL_SENDER}") 
	private String emailUser;

	@Autowired
	private JavaMailSender mailSender;
	
    @Autowired
    private TemplateEngine templateEngine;
	
	@Override
	public void sendEmail(String toUser, String subject, String message) {
		
		SimpleMailMessage  mailMessage = new SimpleMailMessage();
		
		//mailMessage.setFrom(emailUser);
		mailMessage.setFrom(emailUser);
		mailMessage.setTo(toUser);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		
		
		
		mailSender.send(mailMessage);
		
	} 

    @Override
    public void sendTurnoHtmlEmail(Turno turno) {
        try {
            // Armar el contexto con los datos del turno
            Context context = new Context();
            context.setVariable("turno", turno);

            // Renderizar el HTML
            String htmlContent = templateEngine.process("confirmacion-turno", context);

            // Armar el mensaje
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom("tpoo2g16@gmail.com");
            helper.setTo(turno.getCliente().getEMail()); // o cualquier destinatario
            helper.setSubject("Confirmación de Turno");
            helper.setText(htmlContent, true); // true = es HTML

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el email con HTML.");
        }
    }
	
	@Override
	public void sendEmail(String[] toUser, String subject, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithFIle(String[] toUser, String subject, String message, File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithFIle(String toUser, String subject, String message, File file) {
		// TODO Auto-generated method stub
		
	}


	

}
