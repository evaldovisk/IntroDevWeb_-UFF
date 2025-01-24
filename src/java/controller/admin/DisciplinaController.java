package controller.admin;

import entidade.Disciplina;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DisciplinaDAO;

@WebServlet(name = "Disciplina", urlPatterns = {"/admin/disciplina", "/admin/disciplina/alterar", "/admin/disciplina/cadastrar"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getServletPath();

        if (acao.contains("/admin/disciplina/alterar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                DisciplinaDAO disciplinaDao = new DisciplinaDAO();
                Disciplina disciplina = disciplinaDao.getDisciplina(id);
                request.setAttribute("disciplinaAlterar", disciplina);
                request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, "Erro ao buscar disciplina", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar disciplina");
            } catch (Exception ex) {
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        // Se for a ação de cadastrar
        else if (acao.contains("/admin/disciplina/cadastrar")) {
            request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp").forward(request, response);
        } 
        // Se for para listar disciplinas
        else {
            try {
                DisciplinaDAO disciplinaDao = new DisciplinaDAO();
                ArrayList<Disciplina> disciplinas = disciplinaDao.ListaDeDisciplinas();
                request.setAttribute("disciplinas", disciplinas);
                request.getRequestDispatcher("/views/admin/disciplina/listaDisciplina.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, "Erro ao listar disciplinas", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar disciplinas");
            } catch (Exception ex) {
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        DisciplinaDAO disciplinaDao = new DisciplinaDAO();

        try {
            if ("cadastrar".equals(acao)) {
                Disciplina disciplina = new Disciplina();
                disciplina.setNome(request.getParameter("nome"));
                disciplina.setRequisito(request.getParameter("requisito"));
                disciplina.setEmenta(request.getParameter("ementa"));
                disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("cargaHoraria")));

                disciplinaDao.Inserir(disciplina);
                response.sendRedirect(request.getContextPath() + "/admin/disciplina");

            } 
            else if ("alterar".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Disciplina disciplina = new Disciplina();
                disciplina.setId(id);
                disciplina.setNome(request.getParameter("nome"));
                disciplina.setRequisito(request.getParameter("requisito"));
                disciplina.setEmenta(request.getParameter("ementa"));
                disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("cargaHoraria")));

                disciplinaDao.Alterar(disciplina);
                response.sendRedirect(request.getContextPath() + "/admin/disciplina");
            } 
            else if ("excluir".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Disciplina disciplina = new Disciplina();
                disciplina.setId(id);
                disciplinaDao.Excluir(disciplina);
                response.sendRedirect(request.getContextPath() + "/admin/disciplina");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, "Erro ao processar ação", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar ação");
        } catch (Exception ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
