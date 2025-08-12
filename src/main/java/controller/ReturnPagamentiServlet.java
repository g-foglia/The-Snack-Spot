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

@WebServlet(name = "ReturnPagamentiServlet", value = "/return-pagamenti")
public class ReturnPagamentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        List<String> nConti = new EffettuaDao().doRetrieveById(utente.getEmailCliente());

        List<Pagamento> pagamenti = new ArrayList<>();
        for(String nConto : nConti){
            Pagamento pagamento = new PagamentoDAO().doRetrieveByNConto(nConto);
            if(pagamento != null)
                pagamenti.add(pagamento);
        }
        request.setAttribute("pagamenti",pagamenti);

        RequestDispatcher view = request.getRequestDispatcher("mostrapagamenti.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
