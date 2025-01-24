<%@page import="entidade.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Administradores</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">
                <a href="<%= request.getContextPath()%>/admin/administrador/cadastrar" class="btn btn-primary mb-3">Incluir</a>
                <h1>Lista de Administradores</h1>
                <table class="table  table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Endereço</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Administrador> administradores = (ArrayList<Administrador>) request.getAttribute("Administradores");
                            for (Administrador admin : administradores) {
                        %>
                        <tr>
                            <td><%= admin.getId()%></td>
                            <td><%= admin.getNome()%></td>
                            <td><%= admin.getCpf()%></td>
                            <td><%= admin.getEndereco()%></td>
                            <td>
                                <a href="<%= request.getContextPath()%>/admin/administrador/alterar?id=<%= admin.getId()%>" class="btn btn-warning">Alterar</a>
                                <form action="<%= request.getContextPath()%>/admin/administrador" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="excluir">
                                    <input type="hidden" name="id" value="<%= admin.getId()%>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir este admin?');">
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
