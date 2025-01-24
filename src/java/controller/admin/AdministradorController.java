package controller.admin;

import entidade.Administrador;
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
import model.AdministradorDAO;

@WebServlet(name = "Administrador", urlPatterns = {"/admin/administrador", "/admin/administrador/alterar", "/admin/administrador/cadastrar"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getServletPath();
        AdministradorDAO adminDao = new AdministradorDAO();

        try {
            if (acao.contains("/admin/administrador/alterar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Administrador admin = adminDao.getAdministrador(id);
                request.setAttribute("adminAlterar", admin);
                request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp").forward(request, response);
            } else if (acao.contains("/admin/administrador/cadastrar")) {
                request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp").forward(request, response);
            } else {
                ArrayList<Administrador> administradores = adminDao.ListaDeAdministrador();
                request.setAttribute("Administradores", administradores);
                request.getRequestDispatcher("/views/admin/administrador/listaAdministrador.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        AdministradorDAO adminDao = new AdministradorDAO();
        Administrador admin = new Administrador();

        try {
            if ("cadastrar".equals(acao)) {
                admin.setNome(request.getParameter("nome"));
                admin.setCpf(request.getParameter("cpf"));
                admin.setEndereco(request.getParameter("endereco"));
                admin.setSenha(request.getParameter("senha"));

                try {
                    adminDao.Inserir(admin);
                } catch (Exception ex) {
                    Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, "deu ruim", ex);
                }
                response.sendRedirect(request.getContextPath() + "/admin/administrador");
            } else if ("alterar".equals(acao)) {
                admin.setId(Integer.parseInt(request.getParameter("id")));
                admin.setNome(request.getParameter("nome"));
                admin.setCpf(request.getParameter("cpf"));
                admin.setSenha(request.getParameter("senha"));

                try {
                    adminDao.Alterar(admin);
                } catch (Exception ex) {
                    Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect(request.getContextPath() + "/admin/administrador");
            } else if ("excluir".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));

                admin.setId(id);
                adminDao.Excluir(admin);
                response.sendRedirect(request.getContextPath() + "/admin/administrador");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
