<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador, entidade.Aluno, entidade.Professor" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Administrador AdministradorLogado = (Administrador) session.getAttribute("administrador");
                        Aluno AlunoLogado = (Aluno) session.getAttribute("aluno");
                        Professor ProfessorLogado = (Professor) session.getAttribute("professor");

                        // Verifica se o administrador está logado
                        if (AdministradorLogado != null) { %>
                <!--<a class="nav-link" href="/aplicacaoMVC/admin/dashboard">Dashboard</a>-->
                <a class="nav-link" href="/aplicacaoMVC/admin/relatorio">Relatorio</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/aluno">Aluno</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/professor">Professor</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/administrador">Administrador</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/disciplina">Disciplina</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/turma">Turmas</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  
                        // Verifica se o aluno está logado
                        } else if (AlunoLogado != null) { %>
                <!--<a class="nav-link" href="/aplicacaoMVC/aluno/dashboard">Dashboard</a>-->
                <a class="nav-link" href="/aplicacaoMVC/aluno/inscricao">Inscrição Disciplina</a>
                <a class="nav-link" href="/aplicacaoMVC/aluno/historico">Historico</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  
                        // Verifica se o professor está logado
                        } else if (ProfessorLogado != null) { %>
                <!--<a class="nav-link" href="/aplicacaoMVC/professor/dashboard">Dashboard</a>-->
                <a class="nav-link" href="/aplicacaoMVC/professor/diario">Diario - Gerenciar Notas</a>
                <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  
                        } else { %>
                <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    
                        }
                    }
                %>
            </div>
        </div>
    </div>
</nav>
