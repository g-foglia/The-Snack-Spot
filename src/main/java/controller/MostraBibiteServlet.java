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

@WebServlet(name = "MostraBibiteServlet", value = "/mostra-bibite")
public class MostraBibiteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> idProdotti = new AggiuntaDao().doRetrieveByNomeCat("Bibita");

        List<Prodotto> bibite = new ArrayList<>();
        ProdottoDao service = new ProdottoDao();
        for(String idProdotto : idProdotti){
            bibite.add(service.doRetrieveById(idProdotto));
        }

        request.setAttribute("bibite",bibite);
        RequestDispatcher view = request.getRequestDispatcher("/bevande.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
