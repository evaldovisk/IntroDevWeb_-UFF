<%@page import="entidade.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Professores</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">
                <a href="<%= request.getContextPath()%>/admin/professor/cadastrar" class="btn btn-primary mb-3">Incluirr</a>
                <h1>Lista de Professores</h1>
                <table class="table  table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>CPF</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Professor> professores = (ArrayList<Professor>) request.getAttribute("Professores");
                            for (Professor professor : professores) {
                        %>
                        <tr>
                            <td><%= professor.getId()%></td>
                            <td><%= professor.getNome()%></td>
                            <td><%= professor.getEmail()%></td>
                            <td><%= professor.getCpf()%></td>
                            <td>
                                <a href="<%= request.getContextPath()%>/admin/professor/alterar?id=<%= professor.getId()%>" class="btn btn-warning">Alterar</a>
                                <form action="<%= request.getContextPath()%>/admin/professor" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="excluir">
                                    <input type="hidden" name="id" value="<%= professor.getId()%>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir este professor?');">
                                        Excluir
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
