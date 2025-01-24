package controller.admin;

import entidade.Aluno;
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
import model.AlunoDAO;

@WebServlet(name = "Aluno", urlPatterns = {"/admin/aluno", "/admin/aluno/alterar", "/admin/aluno/cadastrar"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getServletPath();
        if (acao.contains("/admin/aluno/alterar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                AlunoDAO alunoDao = new AlunoDAO();
                Aluno aluno = alunoDao.getAlunoById(id);
                request.setAttribute("alunoAlterar", aluno);
                request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.contains("/admin/aluno/cadastrar")) {
            request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp").forward(request, response);
        } else {
            AlunoDAO alunoDao = new AlunoDAO();
            try {
                ArrayList<Aluno> alunos = alunoDao.getAlunoAll();
                request.setAttribute("Alunos", alunos);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("/views/admin/aluno/listaAluno.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        AlunoDAO alunoDao = new AlunoDAO();
                    Logger logger = Logger.getLogger(AlunoController.class.getName());
logger.log(Level.INFO, "Received ID: " + acao);

        if ("cadastrar".equals(acao)) {
            Aluno aluno = new Aluno();
            aluno.setNome(request.getParameter("nome"));
            aluno.setEmail(request.getParameter("email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setSenha(request.getParameter("senha"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));

            try {
                alunoDao.addAluno(aluno);
                response.sendRedirect(request.getContextPath() + "/admin/aluno");
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("alterar".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(request.getParameter("nome"));
            aluno.setEmail(request.getParameter("email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setSenha(request.getParameter("senha"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));

            try {
                alunoDao.updateAluno(aluno);
                response.sendRedirect(request.getContextPath() + "/admin/aluno");
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("excluir".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            try {
                boolean sucesso = alunoDao.deleteAluno(id);
                if (sucesso) {
                    response.sendRedirect(request.getContextPath() + "/admin/aluno");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao excluir o aluno.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno no servidor.");
            }
        }
    }
}
