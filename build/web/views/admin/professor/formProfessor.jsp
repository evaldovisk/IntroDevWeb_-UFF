<%@page import="entidade.Professor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Professor</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="mt-5">
                <%
                    String id = request.getParameter("id");
                    Professor professor = new Professor();

                    if (id != null) {
                        professor = (Professor) request.getAttribute("professorAlterar");
                    }
                %>
                <h1><%= (id != null) ? "Alterar Professor" : "Cadastrar Novo Professor" %></h1>
                <form action="<%= request.getContextPath() %>/admin/professor?acao=<%= (id != null) ? "alterar" : "cadastrar" %>" method="post">
                    <% if (id != null) { %>
                    <input type="hidden" name="id" value="<%= professor.getId() %>">
                    <% } %>

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="<%= (id != null) ? professor.getNome() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= (id != null) ? professor.getEmail() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" value="<%= (id != null) ? professor.getCpf() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" value="<%= (id != null) ? professor.getSenha() : "" %>" required>
                    </div>
                    <button type="submit" class="btn btn-primary"><%= (id != null) ? "Alterar" : "Cadastrar" %></button>
                     <a href="<%= request.getContextPath() %>/admin/professor" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
