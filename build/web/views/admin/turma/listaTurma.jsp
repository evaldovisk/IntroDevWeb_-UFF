<%@page import="model.AlunoDAO"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Turma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Lista de Turmas</title>
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <div class="table-responsive">
                <a href="<%= request.getContextPath()%>/admin/turma/cadastrar" class="btn btn-primary mb-3">Incluir Turma</a>
                <h1>Lista de Turmas</h1>

                <%
                    Map<String, List<Turma>> turmasAgrupadas = (Map<String, List<Turma>>) request.getAttribute("TurmasAgrupadas");
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                    AlunoDAO alunoDAO = new AlunoDAO();

                    for (Map.Entry<String, List<Turma>> entry : turmasAgrupadas.entrySet()) {
                        String codigoTurma = entry.getKey();
                        List<Turma> turmas = entry.getValue();
                %>
                <h3>Código da Turma: <%= codigoTurma%></h3>

                <%
                    String idTurma1 = null;
                    String idTurma2 = null;
                    if (turmas.size() > 0) idTurma1 = String.valueOf(turmas.get(0).getId());
                    if (turmas.size() > 1) idTurma2 = String.valueOf(turmas.get(1).getId());
                %>

                <a href="<%= request.getContextPath()%>/admin/turma/alterar?codigoTurma=<%=codigoTurma%>&idTurma1=<%=idTurma1%>&idTurma2=<%=idTurma2%>" class="btn btn-warning">Alterar</a>

                <form action="<%= request.getContextPath()%>/admin/turma" method="post" style="display:inline;">
                    <input type="hidden" name="acao" value="excluir">
                    <input type="hidden" name="codigo_turma" value="<%=codigoTurma%>">
                    <button type="submit" class="btn btn-danger" onclick="return confirm('Tem certeza que deseja excluir esta turma?');">Excluir</button>
                </form>

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Professor</th>
                            <th>Disciplina</th>
                            <th>Aluno</th>
                            <th>Nota</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Turma turma : turmas) {
                                Professor professor = professorDAO.getProfessorById(turma.getProfessorId());
                                Disciplina disciplina = disciplinaDAO.getDisciplina(turma.getDisciplinaId());
                                Aluno aluno = turma.getAlunoId() != -1 ? alunoDAO.getAlunoById(turma.getAlunoId()) : null;

                                String nomeProfessor = (professor != null) ? professor.getNome() : "Professor não encontrado";
                                String nomeDisciplina = (disciplina != null) ? disciplina.getNome() : "Disciplina não encontrada";
                                String nomeAluno = (aluno != null) ? aluno.getNome() : (turma.getAlunoId() == -1 ? "Nenhum aluno matriculado" : "Aluno não encontrado");
                        %>
                        <tr>
                            <td><%= turma.getId()%></td>
                            <td><%= nomeProfessor%></td>
                            <td><%= nomeDisciplina%></td>
                            <td><%= nomeAluno%></td>
                            <td><%= turma.getNota()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }
                %>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
