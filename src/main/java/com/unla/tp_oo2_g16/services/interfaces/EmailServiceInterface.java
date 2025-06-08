package com.unla.tp_oo2_g16.services.interfaces;

import java.io.File;

import com.unla.tp_oo2_g16.models.entities.Turno;


public interface EmailServiceInterface {
	
	void sendEmail(String toUser, String subject, String message);
	void sendEmail(String[] toUser, String subject, String message);
	
    public void sendTurnoHtmlEmail(Turno turno);
    
	void sendEmailWithFIle(String toUser, String subject, String message, File file);
	void sendEmailWithFIle(String[] toUser, String subject, String message, File file);

}
