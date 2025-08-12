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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CreaOrdineServlet", value = "/crea-ordine")
public class CreaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nConto = request.getParameter("nConto");

        //Se il metodo di pagamento non esiste allora lo creo e lo aggiungo
        if((new PagamentoDAO().doRetrieveByNConto(nConto)) == null) {
            String intestatario = request.getParameter("intestatario");
            String circuito = request.getParameter("circuito");
            String scadenza = request.getParameter("scadenza");
            int cvv = Integer.parseInt(request.getParameter("cvv"));
            //validazione data di scadenza
            if(validazioneScadenza(scadenza))
                throw new RuntimeException("Errore nell'elaborazione della data di scadenza");

            Pagamento pagamento = new Pagamento();
            pagamento.setnConto(nConto);
            pagamento.setIntestatario(intestatario);
            pagamento.setCircuito(circuito);
            pagamento.setScadenza(scadenza);
            pagamento.setCvv(cvv);
            new PagamentoDAO().doSave(pagamento);
        }
        //altrimenti non faccio nulla perché mi basta il numero del conto per fare le altre operazioni

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        //Associo il pagamento all'utente, se non è già associato
        if(new EffettuaDao().doRetrieve(nConto, utente.getEmailCliente()) == null) {
            Effettua effettua = new Effettua();
            effettua.setEmail(utente.getEmailCliente());
            effettua.setnConto(nConto);
            new EffettuaDao().doSaveEff(effettua);
        }

        //Creo e salvo l'ordine
        Ordine ordine = new Ordine();
        ordine.setStato("Elaborazione");

        //creazione della stringa con la data odierna
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String date = dateFormat.format(Calendar.getInstance().getTime());

        ordine.setDataOrdine(new GregorianCalendar(Integer.parseInt(date.substring(6)),Integer.parseInt(date.substring(3,5))-1,Integer.parseInt(date.substring(0,2))));
        ordine.setDataConsegna(new GregorianCalendar(Integer.parseInt(date.substring(6)),Integer.parseInt(date.substring(3,5))-1,Integer.parseInt(date.substring(0,2))+2));
        ordine.setnConto(nConto);
        ordine.setIdCarrello(carrello.getIdCarrello());
        ordine.setTotale(new ComposizioneDao().returnSubTotal(carrello.getIdCarrello()));
        new OrdineDAO().doSaveOrdine(ordine);

        //aggiorno le quantità dei prodotti
        List<Composizione> composizioni = new ComposizioneDao().doRetrieveChartById(carrello.getIdCarrello());
        ProdottoDao service = new ProdottoDao();

        for(Composizione composizione : composizioni){
            Prodotto prodotto = service.doRetrieveById(composizione.getIdProdotto());
            int temp = prodotto.getQtDeposito();
            prodotto.setQtDeposito(temp - composizione.getQuantita());
            service.doUpdate(prodotto);
        }

        session.removeAttribute("carrello");
        RequestDispatcher view = request.getRequestDispatcher("/OrdineCompiuto.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private boolean validazioneScadenza(String scadenza){
        String regex = "(0[1-9]|[12][0-9]|3[01])[-/.](19|20)dd";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scadenza);
        return matcher.matches();
    }
}
