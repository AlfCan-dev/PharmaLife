package controller.admin;

import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteProdottoAdmin", value = "/ServletDeleteProdottoAdmin")
public class ServletDeleteProdottoAdmin extends HttpServlet {
    private ProdottoDAOMethod prodottoDAO;

    public ServletDeleteProdottoAdmin(){
        prodottoDAO=new ProdottoDAO();
    }
    public ServletDeleteProdottoAdmin(ProdottoDAO prodottoDAO){
        this.prodottoDAO=prodottoDAO;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto=Integer.parseInt(request.getParameter("id"));
        eliminaProdottoDalCatalogo(idProdotto);

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/admin/areaAmministratore.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Questo metodo permette ad un Amministratore di eliminare un prodotto dal catalogo
     * @param idProdotto del prodotto da eliminare
     * @throws ServletException
     * @throws IOException
     * @post @post prodottoDAO.doRetrieveByAllProdotti.size=@pre prodottoDAO.doRetrieveByAllProdotti.size-1
     */
    private void eliminaProdottoDalCatalogo(int idProdotto) throws ServletException, IOException {

         prodottoDAO= new ProdottoDAO();
        prodottoDAO.deleteProdotto(idProdotto);

    }
}
