package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Utente utente = new UtenteDAO().doRetrieveByEmail(email);
        if (utente!=null){
            if(utente.getPassword().equals(password)){
                // Salva l'utente nella sessione
                HttpSession session = request.getSession(true);
                session.setAttribute("utente", utente);

                //carico il carrello dal db, se c'è
                Carrello carrello = (Carrello) session.getAttribute("carrello");
                Carrello carrello1 = new CarrelloDao().returnActiveChartByEmail(utente.getEmailCliente());

                if(carrello != null && carrello1 != null){   //do priorità al carrello del db
                    session.setAttribute("carrello",carrello1);
                }
                else if(carrello == null && carrello1 != null){
                    session.setAttribute("carrello",carrello1);
                }
                else if(carrello != null && carrello1 == null){
                    carrello.setEmail(utente.getEmailCliente());
                    session.setAttribute("carrello",carrello);
                    new CarrelloDao().doUpdate(carrello);
                }

                //Redirect alla home page del sito
                RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
                view.forward(request,response);
            }
            else{
                RequestDispatcher view = request.getRequestDispatcher("/credenziali.jsp");
                view.forward(request,response);

            }
        } else {
            RequestDispatcher view = request.getRequestDispatcher("/credenziali.jsp");
            view.forward(request,response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
