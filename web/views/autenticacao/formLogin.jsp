<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Login</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div style="padding: 40px 200px 0px 200px">
            <%--<jsp:include page="../comum/menu.jsp" />--%>
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Login</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <form action="/aplicacaoMVC/AutenticaController?acao=login" method="POST">
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" value="249.252.810-38" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" name="senha" value="123" class="form-control">
                    </div>
                    <div style="display: flex; gap: 15px; flex-direction: row;">
                        <div >
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                        <div>
                            <a href="<%= request.getContextPath()%>" class="btn btn-primary">Voltar</a>  
                        </div>

                        <!--                        <div class="col-sm-6">                                
                                                    <h6>Não possui acesso <a href="/aplicacaoMVC/RegistrarController">Registre-se aqui</a></h6>-->
                    </div>
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

