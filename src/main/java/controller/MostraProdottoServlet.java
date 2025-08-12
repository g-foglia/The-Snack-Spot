package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AggiuntaDao;
import model.AssAllDao;
import model.Prodotto;
import model.ProdottoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MostraProdottoServlet", value = "/mostra-prodotto")
public class MostraProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("idProdotto");

        Prodotto prodotto = new ProdottoDao().doRetrieveById(idProdotto);
        String categoria = new AggiuntaDao().doRetrieveByIdP(idProdotto);
        List<String> allergeni = new AssAllDao().doRetrieveById(idProdotto);

        request.setAttribute("prodotto",prodotto);
        request.setAttribute("categoria",categoria);
        request.setAttribute("allergeni",allergeni);

        //redirect alla pagina che stampa il prodotto
        RequestDispatcher view = request.getRequestDispatcher("modificaprodotto.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
