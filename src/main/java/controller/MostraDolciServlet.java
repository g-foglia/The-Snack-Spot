package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aggiunta;
import model.AggiuntaDao;
import model.Prodotto;
import model.ProdottoDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraDolciServlet", value = "/mostra-dolci")
public class MostraDolciServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> idProdotti = new AggiuntaDao().doRetrieveByNomeCat("Dolce");

        List<Prodotto> dolci = new ArrayList<>();
        ProdottoDao service = new ProdottoDao();
        for(String idProdotto : idProdotti){
            dolci.add(service.doRetrieveById(idProdotto));
        }

        request.setAttribute("dolci",dolci);
        RequestDispatcher view = request.getRequestDispatcher("/dolci.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
