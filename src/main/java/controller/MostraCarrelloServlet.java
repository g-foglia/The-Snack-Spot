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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraCarrello", value = "/mostra-carrello")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null) { //carrello vuoto o non caricato
            if (session.getAttribute("utente") != null) {    //ho a che fare con un utente
                Utente utente = (Utente) session.getAttribute("utente");
                if (new CarrelloDao().returnActiveChartByEmail(utente.getEmailCliente()) != null) { //carrello sul db
                    carrello = new CarrelloDao().returnActiveChartByEmail(utente.getEmailCliente());
                    session.setAttribute("carrello", carrello);

                    List<Composizione> composizioni = new ComposizioneDao().doRetrieveChartById(carrello.getIdCarrello());

                    List<Prodotto> prodotti = new ArrayList<>();
                    List<Boolean> flag = new ArrayList<>();
                    List<Integer> quantita = new ArrayList<>();

                    ProdottoDao service = new ProdottoDao();
                    for (Composizione composizione : composizioni) {
                        Prodotto prodotto = service.doRetrieveById(composizione.getIdProdotto());
                        prodotti.add(prodotto);
                        if (composizione.getQuantita() > prodotto.getQtDeposito()) { //se la qt selezionata è maggiore di quella disponibile viene impostata la quantità massima
                            flag.add(true);
                            composizione.setQuantita(prodotto.getQtDeposito());
                            new ComposizioneDao().doUpdate(composizione);

                            quantita.add(prodotto.getQtDeposito());
                        } else {   //i flag servono nella jsp per segnalare l'avvenuto cambio di quantità
                            flag.add(false);
                            quantita.add(composizione.getQuantita());
                        }
                    }

                    request.setAttribute("prodotti", prodotti);
                    request.setAttribute("flag", flag);
                    request.setAttribute("quantita", quantita);
                    request.setAttribute("totale", new ComposizioneDao().returnSubTotal(carrello.getIdCarrello()));

                    RequestDispatcher view = request.getRequestDispatcher("/mostraCarrello.jsp");
                    view.forward(request, response);

                } else {   //carrello vuoto
                    RequestDispatcher view = request.getRequestDispatcher("/mostraCarrello.jsp");
                    view.forward(request, response);
                }
            } else { // ho a che fare con un guest e devo cercare il carrello sul db
                if (new CarrelloDao().returnActiveChartByIdGuest(session.getId()) != null) { //carrello sul db
                    carrello = new CarrelloDao().returnActiveChartByIdGuest(session.getId());
                    session.setAttribute("carrello", carrello);

                    List<Composizione> composizioni = new ComposizioneDao().doRetrieveChartById(carrello.getIdCarrello());

                    List<Prodotto> prodotti = new ArrayList<>();
                    List<Boolean> flag = new ArrayList<>();
                    List<Integer> quantita = new ArrayList<>();

                    ProdottoDao service = new ProdottoDao();
                    for (Composizione composizione : composizioni) {
                        Prodotto prodotto = service.doRetrieveById(composizione.getIdProdotto());
                        prodotti.add(prodotto);
                        if (composizione.getQuantita() > prodotto.getQtDeposito()) { //se la qt selezionata è maggiore di quella disponibile viene impostata la quantità massima
                            flag.add(true);
                            composizione.setQuantita(prodotto.getQtDeposito());
                            new ComposizioneDao().doUpdate(composizione);

                            quantita.add(prodotto.getQtDeposito());
                        } else {   //i flag servono nella jsp per segnalare l'avvenuto cambio di quantità
                            flag.add(false);
                            quantita.add(composizione.getQuantita());
                        }
                    }

                    request.setAttribute("prodotti", prodotti);
                    request.setAttribute("flag", flag);
                    request.setAttribute("quantita", quantita);
                    request.setAttribute("totale", new ComposizioneDao().returnSubTotal(carrello.getIdCarrello()));

                    RequestDispatcher view = request.getRequestDispatcher("/mostraCarrello.jsp");
                    view.forward(request, response);

                } else {   //carrello vuoto
                    RequestDispatcher view = request.getRequestDispatcher("/mostraCarrello.jsp");
                    view.forward(request, response);
                }
            }
        } else {   //il carrello c'è quindi va mostrato
            List<Composizione> composizioni = new ComposizioneDao().doRetrieveChartById(carrello.getIdCarrello());

            List<Prodotto> prodotti = new ArrayList<>();
            List<Boolean> flag = new ArrayList<>();
            List<Integer> quantita = new ArrayList<>();

            ProdottoDao service = new ProdottoDao();
            for (Composizione composizione : composizioni) {
                Prodotto prodotto = service.doRetrieveById(composizione.getIdProdotto());
                prodotti.add(prodotto);
                if (composizione.getQuantita() > prodotto.getQtDeposito()) { //se la qt selezionata è maggiore di quella disponibile viene impostata la quantità massima
                    flag.add(true);
                    composizione.setQuantita(prodotto.getQtDeposito());
                    new ComposizioneDao().doUpdate(composizione);
                    quantita.add(prodotto.getQtDeposito());
                } else {   //i flag servono nella jsp per segnalare l'avvenuto cambio di quantità
                    flag.add(false);
                    quantita.add(composizione.getQuantita());
                }
            }
            request.setAttribute("prodotti", prodotti);
            request.setAttribute("flag", flag);
            request.setAttribute("quantita", quantita);
            request.setAttribute("totale", new ComposizioneDao().returnSubTotal(carrello.getIdCarrello()));

            RequestDispatcher view = request.getRequestDispatcher("/mostraCarrello.jsp");
            view.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
