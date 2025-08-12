package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PreparaOrdineServlet", value = "/prepara-ordine")
public class PreparaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");Carrello carrello = (Carrello) session.getAttribute("carrello");

        //estraggo i metodi di pagamento dell'utente, se ci sono
        List<String> conti = new EffettuaDao().doRetrieveById(utente.getEmailCliente());


        List<Pagamento> pagamenti = new ArrayList<>();
        for(String nConto : conti){
            pagamenti.add(new PagamentoDAO().doRetrieveByNConto(nConto));
        }
        request.setAttribute("pagamenti",pagamenti);


        RequestDispatcher view = request.getRequestDispatcher("pagamento.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
