package controller.professor;


import entidade.Professor;
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

@WebServlet(name = "DiarioController", urlPatterns = {"/professor/diario"})
public class DiarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Professor professorLogado = (Professor) session.getAttribute("professor");
            
            if (professorLogado != null) {
                int professorId = professorLogado.getId();  
                TurmaDAO turmaDAO = new TurmaDAO();
                

                Map<String, List<Turma>> turmasAgrupadas = turmaDAO.getTurmasPorProfessorAgrupadasPorCodigo(professorId);
                request.setAttribute("TurmasAgrupadas", turmasAgrupadas);
            

                request.getRequestDispatcher("/views/professor/diario/listaNotas.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        int turmaId = Integer.parseInt(request.getParameter("idTurma"));
        int alunoId = Integer.parseInt(request.getParameter("idAluno"));
        int nota = Integer.parseInt(request.getParameter("nota")); // Nova nota

        TurmaDAO turmaDao = new TurmaDAO();
        Turma turma = new Turma();
        if ("editarNota".equals(acao)) {

            try {
                turma = turmaDao.getTurmaById(turmaId);
                turma.setNota(nota);  // Atualiza a nota da turma

                boolean sucesso = turmaDao.updateTurma(turma);
                if (sucesso) {
                    response.sendRedirect(request.getContextPath() + "/professor/diario");
                }
            } catch (SQLException ex) {
                
            }

        }
    }
}
