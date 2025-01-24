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
                <h1>Lista de Turmas</h1>

                <%
                    Map<String, List<Turma>> turmasAgrupadas = (Map<String, List<Turma>>) request.getAttribute("TurmasAgrupadas");
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                    AlunoDAO alunoDAO = new AlunoDAO();
                    
                    Aluno AlunoLogado = (Aluno) session.getAttribute("aluno");
                    int usuarioId = AlunoLogado.getId() ;
                    

                    for (Map.Entry<String, List<Turma>> entry : turmasAgrupadas.entrySet()) {
                        String codigoTurma = entry.getKey();
                        List<Turma> turmas = entry.getValue();
                %>
                <h3>Código da Turma: <%= codigoTurma%></h3>

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Professor</th>
                            <th>Disciplina</th>
                            <th>Aluno</th>
                            <th>Ações</th>
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
                                String nomeAluno;
                                boolean inscreverDisabled = false;
                                boolean cancelarDisabled = true;

                                if (turma.getAlunoId() == -1) {
                                    nomeAluno = "Disponivel";
                                } else if (turma.getAlunoId() == usuarioId) {
                                    if (turma.getNota() == 0) {
                                        nomeAluno = "Inscrito";
                                        inscreverDisabled = true;
                                        cancelarDisabled = false;
                                    } else {
                                        nomeAluno = "Nota registrada";
                                        inscreverDisabled = true;
                                        cancelarDisabled = true;
                                    }
                                } else {
                                    nomeAluno = "Preenchido";
                                    inscreverDisabled = true;
                                    cancelarDisabled = true;
                                }
                        %>
                        <tr>
                            <td><%= nomeProfessor %></td>
                            <td><%= nomeDisciplina %></td>
                            <td><%= nomeAluno %></td>
                            <td> 
                                <form action="<%= request.getContextPath() %>/aluno/inscricao" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="inscrever">
                                    <input type="hidden" name="idAluno" value="<%= usuarioId %>">
                                    <input type="hidden" name="idTurma" value="<%= turma.getId() %>">
                                    <button type="submit" class="btn btn-success" <%= inscreverDisabled ? "disabled" : "" %>>Inscrever</button>
                                </form>

                                <form action="<%= request.getContextPath() %>/aluno/inscricao" method="post" style="display:inline;">
                                    <input type="hidden" name="acao" value="cancelar">
                                     <input type="hidden" name="idAluno" value="<%= usuarioId %>">
                                    <input type="hidden" name="idTurma" value="<%= turma.getId() %>">
                                    <button type="submit" class="btn btn-danger" <%= cancelarDisabled ? "disabled" : "" %>>Cancelar Inscrição</button>
                                </form>
                            </td>
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
