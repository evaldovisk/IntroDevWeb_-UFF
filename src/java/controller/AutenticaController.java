package controller;


import entidade.Administrador;
import entidade.Aluno;
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

            Administrador administrador = new Administrador(cpf_user, senha_user);
            AdministradorDAO administradorDAO = new AdministradorDAO();
            try {
                administradorObtido = administradorDAO.Logar(administrador);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

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


            if (administradorObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("administrador", administradorObtido);

                rd = request.getRequestDispatcher("/admin/dashboard");
                rd.forward(request, response);

            } else if (alunoObtido != null && alunoObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("aluno", alunoObtido);
                
                rd = request.getRequestDispatcher("/aluno/dashboard");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);
            }
        }
    }
}
