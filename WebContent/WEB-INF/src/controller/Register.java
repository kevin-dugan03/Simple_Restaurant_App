package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;

import model.RegistrationDB;
import objects.Registrant;

/**
 * Servlet that registers a new user, if valid info is provided.
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		HttpSession session = request.getSession();
		String message = "";

		//new registrant bean from the parameters.
		Registrant reg = new Registrant();
		reg.setEmail((String) request.getParameter("email"));
		reg.setUsername((String) request.getParameter("username"));
		reg.setPassword((String) request.getParameter("password1"));
		
		//create new DB Access object to register the user.
		RegistrationDB regDB = new RegistrationDB(reg);
		
		try {
			//if the user has been registered show the proper message, send confirmation email
			if (regDB.addRegistrant())
			{
				message = "Congratulations, you have successfully registered. Please log in.";
				
				session.setAttribute("user", reg);
				session.setAttribute("message", message);
				
				try {
					sendMailMsg(reg);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			else
			{
				message = "Another user already registered the email address you supplied.";
				session.setAttribute("message", message);
			}
			
			//upon success or failure, redirect to login page with appropriate message.
			String url = "/login.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("user", reg);
	}
	
	public void sendMailMsg(Registrant registrant) throws MessagingException
	{
		//Get a mail session
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", "smtp.mail.yahoo.com");
		props.put("mail.smtps.port", 465);
		props.put("mail.smtps.auth", "true");
		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
		
		//create the message
		String to = registrant.getEmail();
		String from = "testCOMP321@yahoo.com";
		String subject = "COMP 321 Pizza Restaurant Confirmation Email";
		String body = "Thank you for registering with the COMP 321 Pizza Restaurant. Your " +
			"Username is '" + registrant.getUsername() + "' and your password is '" + 
			registrant.getPassword() + "'. Have a nice day.\n\nPizza Restaurant Manager";

		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setText(body);
			
		Transport transport = mailSession.getTransport();
		transport.connect("testCOMP321", "123testing");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}
}
