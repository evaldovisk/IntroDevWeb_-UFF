<%@page import="java.util.List"%>
<%@page import="entidade.Turma"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="model.DisciplinaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Minhas Turmas</title>
    </head>
    <body>
        <jsp:include page="../../comum/menu.jsp"/>

        <div style="padding: 40px 200px 0px 200px">
            <h1>Minhas Turmas</h1>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Código da Turma</th>
                            <th>Professor</th>
                            <th>Disciplina</th>
                            <th>Nota</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Turma> turmasAssociadas = (List<Turma>) request.getAttribute("turmasAssociadas");
                            ProfessorDAO professorDAO = new ProfessorDAO();
                            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

                            double somaNotas = 0.0;
                            int totalTurmasComNota = 0;

                            if (turmasAssociadas != null && !turmasAssociadas.isEmpty()) {
                                for (Turma turma : turmasAssociadas) {
                                    String codigoTurma = turma.getCodigoTurma();
                                    String nomeProfessor = professorDAO.getProfessorById(turma.getProfessorId()).getNome();
                                    String nomeDisciplina = disciplinaDAO.getDisciplina(turma.getDisciplinaId()).getNome();
                                    int nota = turma.getNota();

                       
                                    if (nota > 0) {
                                        somaNotas += nota;
                                        totalTurmasComNota++;
                                    }
                        %>
                        <tr>
                            <td><%= codigoTurma%></td>
                            <td><%= nomeProfessor%></td>
                            <td><%= nomeDisciplina%></td>
                            <td><%= (nota > 0) ? nota : "Sem nota"%></td>
                        </tr>
                        <%
                            }

                            double mediaCR = (totalTurmasComNota > 0) ? (somaNotas / totalTurmasComNota) : 0.0;
                        %>
                        <tr>
                            <td colspan="3" style="text-align: right; font-weight: bold;">Média de CR:</td>
                            <td><%= String.format("%.2f", mediaCR)%></td>
                        </tr>
                        <%
                        } else {
                        %>
                        <tr>
                            <td colspan="4">Você não está inscrito em nenhuma turma.</td>
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
