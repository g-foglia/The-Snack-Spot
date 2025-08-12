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

@WebServlet(name = "AggiuntaCarrelloServlet", value = "/aggiunta-carrello")
public class AggiuntaCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("id");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        String pagina = request.getParameter("pagina");

        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null){ //se ho a che fare con un utente allora devo verificare se ha un carrello attivo
            Carrello carrello = new Carrello();

            if(session.getAttribute("carrello") != null){  //se ha un carrello attivo allora devo vedere se il prodotto è nel carrello
                carrello = (Carrello) session.getAttribute("carrello");
                ComposizioneDao composizioneDao = new ComposizioneDao();
                Composizione composizione = new Composizione();

                if((composizioneDao.doRetrieveById(carrello.getIdCarrello(),idProdotto)) != null){ //se il prodotto è già nel carrello aggiorno la quantità con la nuova

                    if(quantita != 0) {
                        if(!pagina.equals("carrello")) {   //devo sommare la nuova qt alla vecchia
                            composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                            composizione.setQuantita(quantita + composizione.getQuantita());
                            composizioneDao.doUpdate(composizione);
                            carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                            new CarrelloDao().doUpdate(carrello);
                            session.setAttribute("carrello", carrello);
                        }
                        else{     //devo aggiornare la qt con la nuova
                            composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                            composizione.setQuantita(quantita);
                            composizioneDao.doUpdate(composizione);
                            carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                            new CarrelloDao().doUpdate(carrello);
                            session.setAttribute("carrello", carrello);
                        }
                    }
                    else{     //devo cancellare il prodotto dal carrello
                        composizioneDao.doCanc(idProdotto, carrello.getIdCarrello());
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello",carrello);
                    }
                }
                else {  //altrimenti lo aggiungo al carrello
                    composizione.setQuantita(quantita);
                    composizione.setIdCarrello(carrello.getIdCarrello());
                    composizione.setIdProdotto(idProdotto);
                    composizioneDao.doSave(composizione);
                    carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                    new CarrelloDao().doUpdate(carrello);
                    session.setAttribute("carrello",carrello);
                }
            }
            else{   //altrimenti devo cercarlo sul db oppure crearlo
                if((new CarrelloDao().returnActiveChartByEmail(utente.getEmailCliente())) != null ){ //il carrello è sul db, quindi devo caricarlo e modificarlo
                    carrello = new CarrelloDao().returnActiveChartByEmail(utente.getEmailCliente());
                    ComposizioneDao composizioneDao = new ComposizioneDao();
                    Composizione composizione = new Composizione();

                    if((composizioneDao.doRetrieveById(carrello.getIdCarrello(),idProdotto)) != null){ //se il prodotto è già nel carrello aggiorno la quantità con la nuova
                        composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                        composizione.setQuantita(quantita + composizione.getQuantita());
                        composizioneDao.doUpdate(composizione);
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello", carrello);

                    }
                    else {  //altrimenti lo aggiungo al carrello
                        composizione.setQuantita(quantita);
                        composizione.setIdCarrello(carrello.getIdCarrello());
                        composizione.setIdProdotto(idProdotto);
                        composizioneDao.doSave(composizione);
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello",carrello);
                    }
                }
                else{   //il carrello va creato e salvato
                    carrello.setEmail(utente.getEmailCliente());
                    ProdottoDao prodottoDAO = new ProdottoDao();
                    Prodotto prodotto = prodottoDAO.doRetrieveById(idProdotto);
                    carrello.setSubTotale(prodotto.getPrezzo()*quantita);
                    carrello.setIdCarrello(new CarrelloDao().doSave(carrello));

                    Composizione composizione = new Composizione();
                    composizione.setIdProdotto(idProdotto);
                    composizione.setIdCarrello(carrello.getIdCarrello());
                    composizione.setQuantita(quantita);
                    ComposizioneDao service2 = new ComposizioneDao();
                    service2.doSave(composizione);

                    session.setAttribute("carrello",carrello);
                }
            }
        }else { //altrimenti ho a che fare con un guest
            Carrello carrello = new Carrello();

            if (session.getAttribute("carrello") != null) {  //se ha un carrello attivo allora devo vedere se il prodotto è nel carrello
                carrello = (Carrello) session.getAttribute("carrello");
                ComposizioneDao composizioneDao = new ComposizioneDao();
                Composizione composizione = new Composizione();

                if ((composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto)) != null) { //se il prodotto è già nel carrello aggiorno la quantità con la nuova
                    if (quantita != 0) {
                        if(!pagina.equals("carrello")) {   //devo sommare la nuova qt alla vecchia
                            composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                            composizione.setQuantita(quantita + composizione.getQuantita());
                            composizioneDao.doUpdate(composizione);
                            carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                            new CarrelloDao().doUpdate(carrello);
                            session.setAttribute("carrello", carrello);
                        }
                        else{     //devo aggiornare la qt con la nuova
                            composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                            composizione.setQuantita(quantita);
                            composizioneDao.doUpdate(composizione);
                            carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                            new CarrelloDao().doUpdate(carrello);
                            session.setAttribute("carrello", carrello);
                        }
                    } else {     //devo cancellare il prodotto dal carrello
                        composizioneDao.doCanc(idProdotto, carrello.getIdCarrello());
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello", carrello);
                    }
                } else {  //altrimenti lo aggiungo al carrello
                    composizione.setQuantita(quantita);
                    composizione.setIdCarrello(carrello.getIdCarrello());
                    composizione.setIdProdotto(idProdotto);
                    composizioneDao.doSave(composizione);
                    carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                    new CarrelloDao().doUpdate(carrello);
                    session.setAttribute("carrello", carrello);
                }
            } else {   //altrimenti devo cercarlo sul db oppure crearlo
                if ((new CarrelloDao().returnActiveChartByIdGuest(session.getId())) != null) { //il carrello è sul db, quindi devo caricarlo e modificarlo
                    carrello = new CarrelloDao().returnActiveChartByIdGuest(session.getId());
                    ComposizioneDao composizioneDao = new ComposizioneDao();
                    Composizione composizione = new Composizione();

                    if ((composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto)) != null) { //se il prodotto è già nel carrello aggiorno la quantità con la nuova
                        composizione = composizioneDao.doRetrieveById(carrello.getIdCarrello(), idProdotto);
                        composizione.setQuantita(quantita + composizione.getQuantita());
                        composizioneDao.doUpdate(composizione);
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));
                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello", carrello);
                    } else {  //altrimenti lo aggiungo al carrello
                        composizione.setQuantita(quantita);
                        composizione.setIdCarrello(carrello.getIdCarrello());
                        composizione.setIdProdotto(idProdotto);
                        composizioneDao.doSave(composizione);
                        carrello.setSubTotale(composizioneDao.returnSubTotal(carrello.getIdCarrello()));

                        new CarrelloDao().doUpdate(carrello);
                        session.setAttribute("carrello", carrello);
                    }
                } else {   //il carrello va creato e salvato
                    carrello.setIdGuest(session.getId());
                    ProdottoDao prodottoDAO = new ProdottoDao();
                    Prodotto prodotto = prodottoDAO.doRetrieveById(idProdotto);

                    if(new GuestDAO().doRetrieveBiId(session.getId()) ==  null) {
                        Guest guest = new Guest();
                        guest.setIdGuest(carrello.getIdGuest());
                        new GuestDAO().doSave(guest);
                    }
                    carrello.setSubTotale(prodotto.getPrezzo() * quantita);
                    carrello.setIdCarrello(new CarrelloDao().doSave(carrello));

                    Composizione composizione = new Composizione();
                    composizione.setIdProdotto(idProdotto);
                    composizione.setIdCarrello(carrello.getIdCarrello());
                    composizione.setQuantita(quantita);
                    ComposizioneDao service2 = new ComposizioneDao();
                    service2.doSave(composizione);

                    session.setAttribute("carrello", carrello);
                }
            }
        }

        //Redirect alla stessa pagina che ha effettuato la richiesta
        RequestDispatcher view;
        if(pagina.equals("bevande")){
            view = request.getRequestDispatcher("/mostra-bibite");
            view.forward(request,response);
        }
        else if(pagina.equals("salati")){
            view = request.getRequestDispatcher("/mostra-salati");
            view.forward(request,response);
        }
        else if(pagina.equals("dolci")){
            view = request.getRequestDispatcher("/mostra-dolci");
            view.forward(request,response);
        }
        else if(pagina.equals("ricerca")){
            String key = request.getParameter("key");
            view = request.getRequestDispatcher("/ricerca?key="+key);
            view.forward(request,response);
        }
        else if(pagina.equals("carrello")){
            view = request.getRequestDispatcher("/mostra-carrello");
            view.forward(request,response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
