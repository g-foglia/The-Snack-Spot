package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AggiuntaProdottoServlet", value = "/aggiunta-prodotto")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 4096,       // 4 MB
        maxRequestSize = 1024 * 6144    // 6 MB
)
public class AggiuntaProdottoServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "Immagini";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("idProdotto");
        String nome = request.getParameter("nome");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String produttore = request.getParameter("produttore");
        String descrizione = request.getParameter("descrizione");
        int qtDeposito = Integer.parseInt(request.getParameter("quantità"));
        String categoria = request.getParameter("categoria");

        List<String> allergeni = new ArrayList<>();
        if(request.getParameter("Glutine") != null)
            allergeni.add("Glutine");
        if(request.getParameter("Frutta a guscio") != null)
            allergeni.add("Frutta a guscio");
        if(request.getParameter("Lattosio") != null)
            allergeni.add("Lattosio");
        if(request.getParameter("Uovo") != null)
            allergeni.add("Uovo");

        //salvataggio foto
        Part filePart = request.getPart("file");
        String nomeFile = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String newFileName = nome + ".jpg";
        String destinazione = CARTELLA_UPLOAD + File.separator + newFileName;
        Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
        InputStream fileInputStream = filePart.getInputStream();
        Files.createDirectories(pathDestinazione.getParent());
        Files.copy(fileInputStream, pathDestinazione);

        //Salvataggio prodotto
        Prodotto prodotto = new Prodotto();
        prodotto.setIdProdotto(idProdotto);
        prodotto.setNomeProdotto(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setProduttore(produttore);
        prodotto.setDescrizione(descrizione);
        prodotto.setQtDeposito(qtDeposito);
        new ProdottoDao().doSave(prodotto);

        //Salvataggio aggiunta
        Aggiunta aggiunta = new Aggiunta();
        aggiunta.setIdProdotto(idProdotto);
        aggiunta.setNomeCat(categoria);
        new AggiuntaDao().doSaveAggiunta(aggiunta);

        for(String allergene : allergeni){
            AssAll assAll = new AssAll();
            assAll.setIdProdotto(idProdotto);
            assAll.setNomeAll(allergene);
            new AssAllDao().doSave(assAll);
        }
        RequestDispatcher view = request.getRequestDispatcher("/inserimentoAvvenuto.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
