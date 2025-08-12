package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraOrdiniServlet", value = "/mostra-ordini")
public class MostraOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        List<Ordine> ordini = new OrdineDAO().doRetrieveByEmail(utente.getEmailCliente());
        List<List<Prodotto>> prodottiOrdini = new ArrayList<>();
        List<List<Integer>> quantitaOrdini = new ArrayList<>();

        for(Ordine ordine : ordini){
            prodottiOrdini.add(new OrdineDAO().returnProdottiByNOrdine(ordine.getnOrdine()));
            quantitaOrdini.add(new OrdineDAO().returnQuantitaProdotti(ordine.getnOrdine()));
        }

        request.setAttribute("ordini",ordini);
        request.setAttribute("prodotti",prodottiOrdini);
        request.setAttribute("quantita",quantitaOrdini);

        RequestDispatcher view = request.getRequestDispatcher("mostraordini.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
