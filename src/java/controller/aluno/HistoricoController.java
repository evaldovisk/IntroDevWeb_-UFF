package controller.aluno;

import entidade.Aluno;
import entidade.Turma;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AlunoDAO;
import model.TurmaDAO;

@WebServlet(name = "HistoricoController", urlPatterns = {"/aluno/historico"})
public class HistoricoController  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Aluno alunoLogado = (Aluno) session.getAttribute("aluno");

            if (alunoLogado == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            TurmaDAO turmaDao = new TurmaDAO();
            List<Turma> turmasAssociadas = turmaDao.getTurmasByAlunoId(alunoLogado.getId());


            request.setAttribute("turmasAssociadas", turmasAssociadas);

            request.getRequestDispatcher("/views/aluno/historico/listaHistorico.jsp").forward(request, response);
        } catch (SQLException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar as turmas.");
        }
    }
}
