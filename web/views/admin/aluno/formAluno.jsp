<%@page import="entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Aluno</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div class="container">
            <div class="mt-5">
                <%
                    String id = request.getParameter("id");

                    Aluno aluno = new Aluno();

                    if (id != null) {
                        aluno = (Aluno) request.getAttribute("alunoAlterar");
                    }
                %>

                <h1><%= (id != null) ? "Alterar Aluno" : "Cadastrar Novo Aluno"%></h1>
                <form action="<%= request.getContextPath()%>/admin/aluno?acao=<%= (id != null) ? "alterar" : "cadastrar"%>" method="post">
                    <% if (id != null) {%>
                    <input type="hidden" name="id" value="<%= aluno.getId()%>">
                    <% }%>

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" 
                               value="<%= (id != null) ? aluno.getNome() : ""%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" 
                               value="<%= (id != null) ? aluno.getEmail() : ""%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="celular" class="form-label">Celular</label>
                        <input type="text" class="form-control" id="celular" name="celular" 
                               value="<%= (id != null) ? aluno.getCelular() : ""%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" 
                               value="<%= (id != null) ? aluno.getCpf() : ""%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" 
                               value="<%= (id != null) ? aluno.getSenha() : ""%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="endereco" class="form-label">Endereço</label>
                        <input type="text" class="form-control" id="endereco" name="endereco" 
                               value="<%= (id != null) ? aluno.getEndereco() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="cidade" class="form-label">Cidade</label>
                        <input type="text" class="form-control" id="cidade" name="cidade" 
                               value="<%= (id != null) ? aluno.getCidade() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" class="form-control" id="bairro" name="bairro" 
                               value="<%= (id != null) ? aluno.getBairro() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" class="form-control" id="cep" name="cep" 
                               value="<%= (id != null) ? aluno.getCep() : ""%>">
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= (id != null) ? "Alterar" : "Cadastrar"%>
                    </button>
                </form>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>