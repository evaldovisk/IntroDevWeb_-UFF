package controller.admin;

import entidade.Professor;
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
import model.ProfessorDAO;

@WebServlet(name = "Professor", urlPatterns = {"/admin/professor", "/admin/professor/alterar", "/admin/professor/cadastrar"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getServletPath();
        ProfessorDAO professorDao = new ProfessorDAO();

        try {
            if (acao.contains("/admin/professor/alterar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Professor professor = professorDao.getProfessorById(id);
                request.setAttribute("professorAlterar", professor);
                request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp").forward(request, response);
            } else if (acao.contains("/admin/professor/cadastrar")) {
                request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp").forward(request, response);
            } else {
                ArrayList<Professor> professores = professorDao.getProfessorAll();
                request.setAttribute("Professores", professores);
                request.getRequestDispatcher("/views/admin/professor/listaProfessor.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        ProfessorDAO professorDao = new ProfessorDAO();
        Professor professor = new Professor();

        try {
            if ("cadastrar".equals(acao)) {
                professor.setNome(request.getParameter("nome"));
                professor.setEmail(request.getParameter("email"));
                professor.setCpf(request.getParameter("cpf"));
                professor.setSenha(request.getParameter("senha"));

                professorDao.addProfessor(professor);
                response.sendRedirect(request.getContextPath() + "/admin/professor");
            } else if ("alterar".equals(acao)) {
                professor.setId(Integer.parseInt(request.getParameter("id")));
                professor.setNome(request.getParameter("nome"));
                professor.setEmail(request.getParameter("email"));
                professor.setCpf(request.getParameter("cpf"));
                professor.setSenha(request.getParameter("senha"));

                professorDao.updateProfessor(professor);
                response.sendRedirect(request.getContextPath() + "/admin/professor");
            } else if ("excluir".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));

                boolean sucesso = professorDao.deleteProfessor(id);
                if (sucesso) {
                    response.sendRedirect(request.getContextPath() + "/admin/professor");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao excluir o professor.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
