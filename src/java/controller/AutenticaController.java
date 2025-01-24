package controller;

import entidade.Administrador;
import entidade.Aluno;
import entidade.Professor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;
import model.AlunoDAO;
import model.ProfessorDAO;

@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;

        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);
        } else {
            Administrador administradorObtido = null;
            Aluno alunoObtido = null;
            Professor professorObtido = null;

            // Autenticação de Administrador
            Administrador administrador = new Administrador(cpf_user, senha_user);
            AdministradorDAO administradorDAO = new AdministradorDAO();
            try {
                administradorObtido = administradorDAO.Logar(administrador);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para logar administrador");
            }

            // Autenticação de Aluno
            Aluno aluno = new Aluno();
            aluno.setCpf(cpf_user);
            aluno.setSenha(senha_user);
            AlunoDAO alunoDAO = new AlunoDAO();
            try {
                alunoObtido = alunoDAO.getAlunoByCpfSenha(aluno);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para logar aluno");
            }

            // Autenticação de Professor
            Professor professor = new Professor();
            professor.setCpf(cpf_user);
            professor.setSenha(senha_user);
            ProfessorDAO professorDAO = new ProfessorDAO();
            try {
                professorObtido = professorDAO.getProfessorByCpfSenha(professor);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para logar professor");
            }

            if (administradorObtido != null && administradorObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("administrador", administradorObtido);

                rd = request.getRequestDispatcher("/admin/dashboard");
                rd.forward(request, response);

            } else if (alunoObtido != null && alunoObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("aluno", alunoObtido);

                rd = request.getRequestDispatcher("/aluno/dashboard");
                rd.forward(request, response);

            } else if (professorObtido != null && professorObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("professor", professorObtido);

                rd = request.getRequestDispatcher("/professor/dashboard");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);
            }
        }
    }
}
