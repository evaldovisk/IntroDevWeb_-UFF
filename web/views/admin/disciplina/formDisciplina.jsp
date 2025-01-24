<%@page import="entidade.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Disciplina</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="mt-5">
                <%
                    String id = request.getParameter("id");

                    Disciplina disciplina = new Disciplina();

                    if (id != null) {
                        disciplina = (Disciplina) request.getAttribute("disciplinaAlterar");
                    }
                %>

                <h1><%= (id != null) ? "Alterar Disciplina" : "Cadastrar Nova Disciplina" %></h1>
                <form action="<%= request.getContextPath() %>/admin/disciplina?acao=<%= (id != null) ? "alterar" : "cadastrar" %>" method="post">
                    <% if (id != null) { %>
                    <input type="hidden" name="id" value="<%= disciplina.getId() %>">
                    <% } %>

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" 
                               value="<%= (id != null) ? disciplina.getNome() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="requisito" class="form-label">Requisito</label>
                        <input type="text" class="form-control" id="requisito" name="requisito" 
                               value="<%= (id != null) ? disciplina.getRequisito() : "" %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="ementa" class="form-label">Ementa</label>
                        <textarea class="form-control" id="ementa" name="ementa" rows="3" required><%= (id != null) ? disciplina.getEmenta() : "" %></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Carga Horária</label>
                        <input type="number" class="form-control" id="cargaHoraria" name="cargaHoraria" 
                               value="<%= (id != null) ? disciplina.getCargaHoraria() : "" %>" required>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= (id != null) ? "Alterar" : "Cadastrar" %>
                    </button>
                    <a href="<%= request.getContextPath() %>/admin/disciplina" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
