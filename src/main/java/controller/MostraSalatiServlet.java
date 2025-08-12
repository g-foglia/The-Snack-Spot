package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AggiuntaDao;
import model.Prodotto;
import model.ProdottoDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraSalatiServlet", value = "/mostra-salati")
public class MostraSalatiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> idProdotti = new AggiuntaDao().doRetrieveByNomeCat("Salato");

        List<Prodotto> salati = new ArrayList<>();
        ProdottoDao service = new ProdottoDao();
        for(String idProdotto : idProdotti){
            salati.add(service.doRetrieveById(idProdotto));
        }

        request.setAttribute("salati",salati);
        RequestDispatcher view = request.getRequestDispatcher("/salati.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
