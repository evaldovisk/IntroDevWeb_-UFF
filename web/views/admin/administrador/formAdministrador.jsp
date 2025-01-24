<%@page import="entidade.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Administrador</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="mt-5">
                <%
                    String id = request.getParameter("id");
                    Administrador administrador = new Administrador();

                    if (id != null) {
                        administrador = (Administrador) request.getAttribute("adminAlterar");
                    }
                %>
                <h1><%= (id != null) ? "Alterar Administrador" : "Cadastrar Novo Administrador" %></h1>
                <form action="<%= request.getContextPath() %>/admin/administrador?acao=<%= (id != null) ? "alterar" : "cadastrar" %>" method="post">
                    <% if (id != null) { %>
                    <input type="hidden" name="id" value="<%= administrador.getId() %>">
                    <% } %>

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="<%= (id != null) ? administrador.getNome() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" value="<%= (id != null) ? administrador.getCpf() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="endereco" class="form-label">Endereço</label>
                        <input type="text" class="form-control" id="endereco" name="endereco" value="<%= (id != null) ? administrador.getEndereco() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" value="<%= (id != null) ? administrador.getSenha() : "" %>" required>
                    </div>
                    <button type="submit" class="btn btn-primary"><%= (id != null) ? "Alterar" : "Cadastrar" %></button>
                    <a href="<%= request.getContextPath() %>/admin/administrador" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
