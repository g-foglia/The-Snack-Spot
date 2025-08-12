package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegistrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if(!validazioneEmail(email))
            throw new RuntimeException("Errore nel formato dell'email");

        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String nc = request.getParameter("nc");
        int cap = Integer.parseInt(request.getParameter("cap"));
        String cellulare = request.getParameter("cellulare");

        HttpSession session = request.getSession(true);
        String idGuest = session.getId();

        Utente utente = new Utente();
        utente.setEmailCliente(email);
        utente.setPassword(password);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setVia(via);
        utente.setCitta(citta);
        utente.setNc(nc);
        utente.setCap(cap);
        utente.setCellulare(cellulare);

        //Aggancio il carrello del guest (se esiste) al nuovo profilo di utente registrato
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if(carrello != null){
            carrello.setEmail(email);
            utente.setIdGuest(carrello.getIdGuest());
            new UtenteDAO().doSave(utente);
            new CarrelloDao().doUpdate(carrello);
            session.setAttribute("carrello",carrello);
        }
        else{
            utente.setIdGuest(null);
            new UtenteDAO().doSave(utente);
        }

        session.setAttribute("utente", utente);

        //Redirect alla home page del sito
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean validazioneEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
