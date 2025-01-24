<%@page import="entidade.Professor"%>
<%@page import="entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Área Restrita - Aluno </title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div >
            <jsp:include page="../../comum/menu.jsp" />
            <div style="padding: 40px 200px 0px 200px">

                <h1>Área Restrita</h1>
                <%
                    Professor professorLogado = (Professor) session.getAttribute("professor");
                    out.println("<h3>Usuário logado com sucesso</h3>");
                    out.println("<h2>Nome: " + professorLogado.getNome() + "</h2>");
                %>


            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
