package controller.admin;

import controller.admin.DTO.TurmaRequest;
import entidade.Aluno;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;
import model.TurmaDAO;

@WebServlet(name = "TurmaController", urlPatterns = {"/admin/turma", "/admin/turma/alterar", "/admin/turma/cadastrar"})
public class TurmaController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(TurmaController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getServletPath();
        TurmaDAO turmaDAO = new TurmaDAO();
        ProfessorDAO professorDao = new ProfessorDAO();
        DisciplinaDAO disciplinaDao = new DisciplinaDAO();
        AlunoDAO alunoDao = new AlunoDAO(); 
        try {
            if (acao.contains("/admin/turma/alterar")) {

                String codigoTurma = request.getParameter("codigoTurma");


                Turma turma = turmaDAO.getTurmaByCodigo(codigoTurma);
                String idTurma1 = request.getParameter("idTurma1");
                String idTurma2 = request.getParameter("idTurma2");
                
                logger.log(Level.INFO, "Nota 1: " + idTurma1);
                logger.log(Level.INFO, "Nota 2: " + idTurma2);

                List<Professor> professores = professorDao.getProfessorAll();
                List<Disciplina> disciplinas = disciplinaDao.ListaDeDisciplinas();
                List<Aluno> alunos = alunoDao.getAlunoAll();
                
                request.setAttribute("idTurma1", idTurma1);
                request.setAttribute("idTurma2", idTurma2);
                request.setAttribute("turmaAlterar", turma);
                request.setAttribute("professores", professores);
                request.setAttribute("disciplinas", disciplinas);
                request.setAttribute("alunos", alunos); 


                request.getRequestDispatcher("/views/admin/turma/alterarTurma.jsp").forward(request, response);
            } else if (acao.contains("/admin/turma/cadastrar")) {
                List<Professor> professores = professorDao.getProfessorAll();
                List<Disciplina> disciplinas = disciplinaDao.ListaDeDisciplinas();

                request.setAttribute("professores", professores);
                request.setAttribute("disciplinas", disciplinas);
                request.getRequestDispatcher("/views/admin/turma/cadastrarTurma.jsp").forward(request, response);
            } else {
                Map<String, List<Turma>> turmasAgrupadas = turmaDAO.getTurmasGroupByCodigo();
                request.setAttribute("TurmasAgrupadas", turmasAgrupadas);
                request.getRequestDispatcher("/views/admin/turma/listaTurma.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        TurmaDAO turmaDao = new TurmaDAO();

        if ("cadastrar".equals(acao)) {
            try {
                int professorId = Integer.parseInt(request.getParameter("professor_id"));
                int disciplinaId = Integer.parseInt(request.getParameter("disciplina_id"));
                String codigoTurma = request.getParameter("codigo_turma");

                if (turmaDao.existeTurmaComCodigo(codigoTurma)) {

                    request.setAttribute("erro", "Já existe uma turma com o código fornecido.");

                    ProfessorDAO professorDao = new ProfessorDAO();
                    DisciplinaDAO disciplinaDao = new DisciplinaDAO();
                    List<Professor> professores = professorDao.getProfessorAll();
                    List<Disciplina> disciplinas = disciplinaDao.ListaDeDisciplinas();
                    request.setAttribute("professores", professores);
                    request.setAttribute("disciplinas", disciplinas);

                    request.getRequestDispatcher("/views/admin/turma/cadastrarTurma.jsp").forward(request, response);
                    return;
                }

                Turma turma1 = new Turma();
                turma1.setProfessorId(professorId);
                turma1.setDisciplinaId(disciplinaId);
                turma1.setAlunoId(-1);
                turma1.setCodigoTurma(codigoTurma);
                turma1.setNota(0);

                Turma turma2 = new Turma();
                turma2.setProfessorId(professorId);
                turma2.setDisciplinaId(disciplinaId);
                turma2.setAlunoId(-1);
                turma2.setCodigoTurma(codigoTurma);
                turma2.setNota(0);

                turmaDao.addTurma(turma1);
                turmaDao.addTurma(turma2);

                response.sendRedirect(request.getContextPath() + "/admin/turma");

            } catch (SQLException ex) {
                Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("alterar".equals(acao)) {
            try {
                Turma turma = new Turma();
                String codigoTurma = request.getParameter("codigo_turma");
                int professorId = Integer.parseInt(request.getParameter("professor_id"));
                int disciplinaId = Integer.parseInt(request.getParameter("disciplina_id"));
                int aluno1 = Integer.parseInt(request.getParameter("aluno1"));
                double nota1 = Integer.parseInt(request.getParameter("nota1"));
                int aluno2 = Integer.parseInt(request.getParameter("aluno2"));
                double nota2 = Integer.parseInt(request.getParameter("nota2"));
                int idTurma1 = Integer.parseInt(request.getParameter("id_turma1"));
                int idTurma2 = Integer.parseInt(request.getParameter("id_turma2"));


                
                logger.log(Level.INFO, "Nota 1: " + idTurma1);
                logger.log(Level.INFO, "Nota 2: " + idTurma2);

               turma.setId(idTurma1);
                turma.setCodigoTurma(codigoTurma);
                turma.setProfessorId(professorId);
                turma.setDisciplinaId(disciplinaId);
                turma.setAlunoId(aluno1);
                turma.setNota((int) nota1);

                turmaDao.updateTurma(turma);

                Turma turma2 = new Turma();
               turma2.setId(idTurma2);
                turma2.setCodigoTurma(codigoTurma);
                turma2.setProfessorId(professorId);
                turma2.setDisciplinaId(disciplinaId);
                turma2.setAlunoId(aluno2);
                turma2.setNota((int) nota2);

                turmaDao.updateTurma(turma2);

                response.sendRedirect(request.getContextPath() + "/admin/turma");
            } catch (SQLException ex) {
                Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ("excluir".equals(acao)) {
            try {
                String codigoTurma = request.getParameter("codigo_turma");
                boolean sucesso = turmaDao.excluirTurmaPorCodigo(codigoTurma);
                if (sucesso) {

                    response.sendRedirect(request.getContextPath() + "/admin/turma");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
