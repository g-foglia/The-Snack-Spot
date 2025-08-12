package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RicercaServlet", value = "/ricerca")
public class RicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");

        List<Prodotto> prodotti = new ProdottoDao().doSearch(key);

        if(!prodotti.isEmpty()){
            request.setAttribute("trovati",prodotti);
            request.setAttribute("key",key);
        }

        //redirect alla pagina che deve mostrare i prodotti che sono stati trovati
        RequestDispatcher view = request.getRequestDispatcher("/risultatiRicerca.jsp");
        view.forward(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
