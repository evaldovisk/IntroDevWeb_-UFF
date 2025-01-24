package controller.admin;

import entidade.Aluno;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TurmaDAO;

@WebServlet(name = "RelatorioController", urlPatterns = {"/admin/relatorio"})
public class RelatorioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();


            Map<String, List<Turma>> turmasAgrupadas = turmaDAO.getTurmasGroupByCodigo();
            request.setAttribute("TurmasAgrupadas", turmasAgrupadas);
            request.getRequestDispatcher("/views/admin/relatorio/relatorio.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
