package application.catalogoService;

import com.google.gson.Gson;
import storage.prodotto.Prodotto;
import storage.prodotto.ProdottoDAO;
import storage.prodotto.ProdottoDAOMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@WebServlet(name = "ServletBarraRicerca", value = "/ServletBarraRicerca")


public class ServletBarraRicerca extends HttpServlet {

    private ProdottoDAOMethod prodottoDAO;

    public ServletBarraRicerca() throws SQLException {
        prodottoDAO = new ProdottoDAO();
    }

    public ServletBarraRicerca(ProdottoDAO prodottoDAO) {
        this.prodottoDAO = prodottoDAO;
    }

    /**
     * Il doGete si occupa di ricercare  per ogni parola digitata dall'utente nella barra di ricerca i prodotto che hanno il prefisso
     * digitato dall'utente ,tutto ciò in modo asincrono
     * @param request oggetto della Servlet contente il prefisso del nome del prodotto da ricercare
     * @param response oggeto della Servlet utile ad effettuare il forward
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valore=request.getParameter("value");
        ArrayList<Prodotto> prodotti=prodottoDAO.prodottoSearch(valore);
        Gson gson= new Gson();
        String prodottiJson=gson.toJson(prodotti);
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter= response.getWriter();
        printWriter.write(prodottiJson);
      //  response.getWriter().write(prodottiJson);
      //  ricercaProdotto(valore);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

/*
    public void ricercaProdotto(String valore) throws IOException {
            ArrayList<Prodotto> prodotti = prodottoDAO.prodottoSearch(valore);
            Gson gson = new Gson();
            String prodottiJson = gson.toJson(prodotti);
    }

 */
}
