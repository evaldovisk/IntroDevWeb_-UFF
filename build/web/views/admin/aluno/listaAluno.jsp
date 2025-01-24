<%@page import="entidade.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Alunos</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">

                <a href="<%= request.getContextPath()%>/admin/aluno/cadastrar" class="btn btn-primary mb-3">Incluir</a>

                <h1>Lista de Alunos</h1>
                <table class="table  table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Celular</th>
                            <th>CPF</th>
                            <th>Endereço</th>
                            <th>Cidade</th>
                            <th>Bairro</th>
                            <th>CEP</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Aluno> alunos = (ArrayList<Aluno>) request.getAttribute("Alunos");
                            for (Aluno aluno : alunos) {
                                    if(aluno.getId() == -1) continue;
                        %>
                        <tr>
                            <td><%= aluno.getNome()%></td>
                            <td><%= aluno.getEmail()%></td>
                            <td><%= aluno.getCelular()%></td>
                            <td><%= aluno.getCpf()%></td>
                            <td><%= aluno.getEndereco()%></td>
                            <td><%= aluno.getCidade()%></td>
                            <td><%= aluno.getBairro()%></td>
                            <td><%= aluno.getCep()%></td>
                            <td>
                                <a href="<%= request.getContextPath()%>/admin/aluno/alterar?id=<%= aluno.getId()%>" class="btn btn-warning">Alterar</a>
                                <form action="<%= request.getContextPath()%>/admin/aluno" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="excluir">
                                    <input type="hidden" name="id" value="<%= aluno.getId()%>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir este aluno?');">
                                        Excluir
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
