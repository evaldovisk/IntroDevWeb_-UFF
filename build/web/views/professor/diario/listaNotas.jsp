<%@page import="model.DisciplinaDAO"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="model.AlunoDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
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
                <h1>Lista de Turmas</h1>

                <%
                    Map<String, List<Turma>> turmasAgrupadas = (Map<String, List<Turma>>) request.getAttribute("TurmasAgrupadas");
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                    AlunoDAO alunoDAO = new AlunoDAO();
                    
                    Professor professorLogado = (Professor) session.getAttribute("professor");
                    int professorId = professorLogado.getId();  // ID do professor logado

                    for (Map.Entry<String, List<Turma>> entry : turmasAgrupadas.entrySet()) {
                        String codigoTurma = entry.getKey();
                        List<Turma> turmas = entry.getValue();
                %>
                <h3>Código da Turma: <%= codigoTurma%></h3>

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Disciplina</th>
                            <th>Aluno</th>
                            <th>Nota</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Turma turma : turmas) {
                                if (turma.getProfessorId() == professorId && turma.getAlunoId() != -1) {  // Verifica se o professor é o responsável e se o aluno está inscrito
                                    Disciplina disciplina = disciplinaDAO.getDisciplina(turma.getDisciplinaId());

                                    String nomeDisciplina = (disciplina != null) ? disciplina.getNome() : "Disciplina não encontrada";
                                    Aluno aluno = alunoDAO.getAlunoById(turma.getAlunoId());  

                                    String nomeAluno = aluno != null ? aluno.getNome() : "Nenhum aluno inscrito";
                                    boolean notaEditada = turma.getNota() > 0;  // Verifica se a nota foi editada
                        %>
                        <tr>
                            <td><%= nomeDisciplina %></td>
                            <td><%= nomeAluno %></td>
                            <td><%= notaEditada ? turma.getNota() : "Nenhuma nota" %></td>
                            <td>
                                <form action="<%= request.getContextPath() %>/professor/diario" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="editarNota">
                                    <input type="hidden" name="idAluno" value="<%= turma.getAlunoId() %>">
                                    <input type="hidden" name="idTurma" value="<%= turma.getId() %>">
                                    <input type="number" name="nota" value="<%= turma.getNota() %>" required>
                                    <button type="submit" class="btn btn-warning">Editar Nota</button>
                                </form>
                            </td>
                        </tr>
                        <%
                                }
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
