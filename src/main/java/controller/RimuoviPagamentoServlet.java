package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EffettuaDao;
import model.Pagamento;
import model.PagamentoDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RimuoviPagamentoServlet", value = "/rimuovi-pagamento")
public class RimuoviPagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nConto = request.getParameter("nConto");
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        new EffettuaDao().doCanc(utente.getEmailCliente(), nConto);

        RequestDispatcher view = request.getRequestDispatcher("/return-pagamenti");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
