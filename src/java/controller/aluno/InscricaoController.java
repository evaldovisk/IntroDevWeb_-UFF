package controller.aluno;

import entidade.Aluno;
import entidade.Turma;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;
import model.TurmaDAO;

@WebServlet(name = "InscricaoController", urlPatterns = {"/aluno/inscricao"})
public class InscricaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            TurmaDAO turmaDAO = new TurmaDAO();

            Map<String, List<Turma>> turmasAgrupadas = turmaDAO.getTurmasGroupByCodigo();
            request.setAttribute("TurmasAgrupadas", turmasAgrupadas);

            request.getRequestDispatcher("/views/aluno/inscricao/listaTurmaInscricao.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InscricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        int turmaId = Integer.parseInt(request.getParameter("idTurma"));
        int alunoId = Integer.parseInt(request.getParameter("idAluno"));

        TurmaDAO turmaDao = new TurmaDAO();
        Turma turma = new Turma();
        if ("inscrever".equals(acao)) {

            try {
                turma = turmaDao.getTurmaById(turmaId);
                turma.setAlunoId(alunoId);

                boolean sucesso = turmaDao.updateTurma(turma);
                if (sucesso) {
                    response.sendRedirect(request.getContextPath() + "/aluno/inscricao");

                }
            } catch (SQLException ex) {
                Logger.getLogger(InscricaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ("cancelar".equals(acao)) {

            try {
                turma = turmaDao.getTurmaById(turmaId);
                turma.setAlunoId(-1);

                boolean sucesso = turmaDao.updateTurma(turma);
                if (sucesso) {
                    response.sendRedirect(request.getContextPath() + "/aluno/inscricao");

                }
            } catch (SQLException ex) {
                Logger.getLogger(InscricaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
