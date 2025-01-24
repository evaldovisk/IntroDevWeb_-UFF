<%@page import="entidade.Disciplina"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Disciplinas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">

                <a href="<%= request.getContextPath() %>/admin/disciplina/cadastrar" class="btn btn-primary mb-3">Incluir</a>

                <h1>Lista de Disciplinas</h1>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Requisito</th>
                            <th>Ementa</th>
                            <th>Carga Horária</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) request.getAttribute("disciplinas");
                            for (Disciplina disciplina : disciplinas) {
                        %>
                        <tr>
                            <td><%= disciplina.getId() %></td>
                            <td><%= disciplina.getNome() %></td>
                            <td><%= disciplina.getRequisito() %></td>
                            <td><%= disciplina.getEmenta() %></td>
                            <td><%= disciplina.getCargaHoraria() %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/admin/disciplina/alterar?id=<%= disciplina.getId() %>" class="btn btn-warning">Alterar</a>
                                <form action="<%= request.getContextPath() %>/admin/disciplina" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="excluir">
                                    <input type="hidden" name="id" value="<%= disciplina.getId() %>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir esta disciplina?');">
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
