<%@page import="java.util.List"%>
<%@page import="entidade.Turma"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Disciplina"%>
<%@page import="entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>Alterar Turma</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../../comum/menu.jsp"/>

    <div style="padding: 40px 200px 0px 200px">
        <div class="table-responsive">
            <a href="<%= request.getContextPath() %>/admin/turma" class="btn btn-secondary mb-3">Voltar</a>

            <h1>Alterar Turma</h1>
            <form action="<%= request.getContextPath() %>/admin/turma?acao=alterar" method="post">
                <!-- Campo oculto com o código da turma -->
                <input type="hidden" name="codigo_turma" 
                    value="<%= request.getAttribute("turmaAlterar") != null ? ((Turma)request.getAttribute("turmaAlterar")).getCodigoTurma() : "" %>">

                <!-- Campo oculto para os IDs das turmas -->
                <input type="hidden" name="id_turma1" 
                    value="<%= request.getAttribute("idTurma1") != null ? request.getAttribute("idTurma1") : "" %>">
                
                <input type="hidden" name="id_turma2" 
                    value="<%= request.getAttribute("idTurma2") != null ? request.getAttribute("idTurma2") : "" %>">

                <!-- Select de Professor -->
                <div class="form-group">
                    <label for="professor_id">Professor</label>
                    <select name="professor_id" id="professor_id" class="form-control" required>
                        <option value="">Selecione o Professor</option>
                        <% 
                            List<Professor> professores = (List<Professor>) request.getAttribute("professores");
                            Turma turmaAlterar = (Turma) request.getAttribute("turmaAlterar");
                            if (turmaAlterar != null) {
                                for (Professor professor : professores) {
                                    String selected = "";
                                    if (professor.getId() == turmaAlterar.getProfessorId()) {
                                        selected = "selected";
                                    }
                        %>
                                    <option value="<%= professor.getId() %>" <%= selected %>><%= professor.getNome() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Select de Disciplina -->
                <div class="form-group">
                    <label for="disciplina_id">Disciplina</label>
                    <select name="disciplina_id" id="disciplina_id" class="form-control" required>
                        <option value="">Selecione a Disciplina</option>
                        <% 
                            List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
                            if (turmaAlterar != null) {
                                for (Disciplina disciplina : disciplinas) {
                                    String selected = "";
                                    if (disciplina.getId() == turmaAlterar.getDisciplinaId()) {
                                        selected = "selected";
                                    }
                        %>
                                    <option value="<%= disciplina.getId() %>" <%= selected %>><%= disciplina.getNome() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Select de Aluno 1 -->
                <div class="form-group">
                    <label for="aluno1">Aluno 1</label>
                    <select name="aluno1" id="aluno1" class="form-control" required>
                        <option value="">Selecione o Aluno 1</option>
                        <% 
                            List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
                            if (turmaAlterar != null) {
                                for (Aluno aluno : alunos) {
                                    String selected = "";
                                    if (aluno.getId() == turmaAlterar.getAlunoId()) {
                                        selected = "selected";
                                    }
                        %>
                                    <option value="<%= aluno.getId() %>" <%= selected %>><%= aluno.getNome() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Nota Aluno 1 -->
                <div class="form-group">
                    <label for="nota1">Nota do Aluno 1</label>
                    <input type="number" name="nota1" id="nota1" class="form-control" 
                           value="<%= turmaAlterar != null && turmaAlterar.getNota() != 0 ? turmaAlterar.getNota() : "" %>" step="0.1" required>
                </div>

                <!-- Select de Aluno 2 -->
                <div class="form-group">
                    <label for="aluno2">Aluno 2</label>
                    <select name="aluno2" id="aluno2" class="form-control" required>
                        <option value="">Selecione o Aluno 2</option>
                        <% 
                            if (turmaAlterar != null) {
                                for (Aluno aluno : alunos) {
                                    String selected = "";
                                    if (aluno.getId() == turmaAlterar.getAlunoId()) {
                                        selected = "selected";
                                    }
                        %>
                                    <option value="<%= aluno.getId() %>" <%= selected %>><%= aluno.getNome() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Nota Aluno 2 -->
                <div class="form-group">
                    <label for="nota2">Nota do Aluno 2</label>
                    <input type="number" name="nota2" id="nota2" class="form-control" 
                           value="<%= turmaAlterar != null && turmaAlterar.getNota() != 0 ? turmaAlterar.getNota() : "" %>" step="0.1" required>
                </div>

                <button type="submit" class="btn btn-success mt-3">Alterar</button>
            </form>
        </div>
    </div>

    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
