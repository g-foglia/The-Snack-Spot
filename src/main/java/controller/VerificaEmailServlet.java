package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VerificaEmailServlet", value = "/verifica-email")
public class VerificaEmailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UtenteDAO service = new UtenteDAO();
        Utente utente = service.doRetrieveByEmail(email);
        if(utente != null){
            response.getWriter().write("invalid");
        }
        else{
            response.getWriter().write("valid");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
