package filtro;

import entidade.Administrador;
import entidade.Aluno;
import entidade.Professor;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "filtroRestrito", urlPatterns = {"/admin/*", "/aluno/*", "/professor/*"})  // Inclui professor
public class FiltroRestrito implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Administrador administrador = (Administrador) httpRequest.getSession().getAttribute("administrador");
        Aluno aluno = (Aluno) httpRequest.getSession().getAttribute("aluno");
        Professor professor = (Professor) httpRequest.getSession().getAttribute("professor");

        // Verifica se algum dos usuários (administrador, aluno ou professor) está logado
        if ((administrador != null && !administrador.getNome().isEmpty()) || 
            (aluno != null && !aluno.getNome().isEmpty()) || 
            (professor != null && !professor.getNome().isEmpty())) {
            chain.doFilter(request, response); // Continua com a requisição
        } else {
            httpResponse.sendRedirect("http://localhost:8080/aplicacaoMVC/home"); // Redireciona para a home se não autenticado
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
