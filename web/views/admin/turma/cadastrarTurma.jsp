<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastrar Turma</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">
                <a href="<%= request.getContextPath()%>/admin/turma" class="btn btn-secondary mb-3">Voltar</a>

                <h1>Cadastrar Turma</h1>

                <%-- Exibindo a mensagem de erro, se houver --%>
                <%
                    String erro = (String) request.getAttribute("erro");
                    if (erro != null) {
                %>
                <div class="alert alert-danger">
                    <%= erro %>
                </div>
                <%
                    }
                %>

                <form action="<%= request.getContextPath()%>/admin/turma?acao=cadastrar" method="post">
                    <div class="form-group">
                        <label for="professor_id">Professor</label>
                        <select name="professor_id" id="professor_id" class="form-control" required>
                            <option value="">Selecione o Professor</option>
                            <%
                                ArrayList<Professor> professores = (ArrayList<Professor>) request.getAttribute("professores");
                                for (Professor professor : professores) {
                            %>
                            <option value="<%= professor.getId() %>"><%= professor.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="disciplina_id">Disciplina</label>
                        <select name="disciplina_id" id="disciplina_id" class="form-control" required>
                            <option value="">Selecione a Disciplina</option>
                            <%
                                ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) request.getAttribute("disciplinas");
                                for (Disciplina disciplina : disciplinas) {
                            %>
                            <option value="<%= disciplina.getId() %>"><%= disciplina.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="codigo_turma">Código da Turma</label>
                        <input type="text" name="codigo_turma" id="codigo_turma" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-success mt-3">Cadastrar</button>
                </form>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
