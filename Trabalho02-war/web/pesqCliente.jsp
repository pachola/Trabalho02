<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="formerro.jsp"%>
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head style="margin:0px;">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Imobiliária APH</title>
    </head>
    <link rel="stylesheet" href="css/layout.css" type="text/css"/>
    <body>
        <div id="container" >
            <div id="header">
                <img src="imagens/logoaph.png">
                <div class="tituloPri">APH Imóveis</div>
            </div>
            <div id="leftBar"></div><div id="contentLogin">
                <form action="ServletPrincipal" name="formindex" id="formindex" method="post">
                    <div id="cxCliente">
                        <a style="position: absolute; margin-left: 225px" href="admin.jsp">admin</a>
                        <div id="titCxLogin">Consultas de Imóveis</div>
                        <br>
                        <input style="position: absolute; left: 60px" type="radio"  name="radioacao" value="Aluguel" checked/>                    
                        <label style="position: absolute; left: 85px" for="radioAluguel">Aluguel</label>

                        <input style="position: absolute; left: 140px" type="radio"  name="radioacao" value="Venda" />
                        <label style="position: absolute; left: 165px" for="radioVenda">Venda</label>
                        <br>
                        <script type="text/javascript">
                            function escolheacao() {
                                var radiotipo = document.getElementsByName("radioacao");
                                if (radiotipo[0].checked) {
                                    document.getElementById("acao").value = "Aluguel";
                                    document.formindex.submit();
                                } else if (radiotipo[1]) {
                                    document.getElementById("acao").value = "Venda";
                                    document.formindex.submit();
                                } else {
                                    alert("Escolha o tipo de imóvel a qual deseja buscar.");
                                }
                                alert("A consulta ainda não foi implementada\n Tente clicar em Admin");
                            }
                        </script>

                        <label class="labelsLogin" for="txtUsuario">Código</label>
                        <input class="inputsLogin" id="txtCodigo" name="txtCodigo" type="text"/>
                        <br/>
                        <p>
                            <label  class="labelsLogin2"  for="txttipoimovel">Tipo</label>
                            <select class="comboCliente"  id="comboImoveis" name="comboImoveis">
                                <option value=""> </option>
                                <option value="1">Apartamento</option>
                                <option value="2">Casa</option>
                                <option value="3">Casa Pré-Fabricada</option>
                                <option value="4">Imóvel Comercial</option>
                            </select>
                            <br>
                        </p>
                        <p>
                            <label  class="labelsLogin2"  for="txtcidade">Cidade</label>
                            <select  class="comboCliente" id="comboCidades" name="comboCidades">
                                <option value=""> </option>
                                <option value="1">Arroio Grande</option>
                                <option value="2">Pelotas</option>
                                <option value="3">Jaguarão</option>
                                <option value="4">SLS</option>
                            </select>
                            <br>
                        </p>

                        <label  class="labelsLogin2"   for="txtbairro">Bairro</label>
                        <select class="comboCliente" id="comboBairros" name="comboBairros">
                            <option value=""> </option>
                            <option value="1">São Gabriel</option>
                            <option value="2">Centro</option>
                            <option value="3">São José</option>
                            <option value="4">Silvina</option>
                        </select>
                        <br>

                        <input class="buttonsCliente" type="button" value="Pesquisar" onclick="escolheacao()"/>
                        <input type="hidden" id="acao" name="acao" value=""/>
                    </div>
                </form>
            </div>
            <div id="footer">
                <div class="tituloSec">APH Imóveis, aqui você sonha, ela realiza!!!</div>
            </div>
        </div>
    </body>
</html>