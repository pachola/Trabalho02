<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >
<%@page import="bean.Imovel"%>
<%@page import="java.util.List"%>
<%@page errorPage="formerro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/layout.css" type="text/css"/>
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Cadastros de Imóveis</title>
    </head>
    <script type="text/javascript">
        function limpar() {
            document.getElementById("txtCodigo").value = "";
            document.getElementById("txtNumQuartos").value = "";
            document.getElementById("txtNumSalas").value = "";
            document.getElementById("txtNumGaragem").value = "";
            document.getElementById("txtTamanho").value = "";
            document.getElementById("txtCodEndereco").value = "";
            document.getElementById("txtPreco").value = "";
            document.getElementById("cmbTipoImovel").selectedIndex = 0;
        }

        function salvar() {
            if (camposValidos()) {
                document.getElementById("acaoCRUD").value = "salvar";
                document.formCadastro.submit();
            }
        }

        function excluir() {
            document.getElementById("acaoCRUD").value = "excluir";
            document.formCadastro.submit();
        }

        function atualizar() {
            document.getElementById("acaoCRUD").value = "atualizar";
            document.formCadastro.submit();
        }

        function clickTabela(cod, indice) {
            document.getElementById("txtCodigo").value = cod;
            document.getElementById("acaoCRUD").value = "carregar";
            document.formCadastro.submit();
        }

        function camposValidos() {
            if (document.getElementById("txtNumQuartos").value === "") {
                alert("Informe o Nº de Quartos do Imóvel");
                return false;
            }
            if (document.getElementById("txtNumSalas").value === "") {
                alert("Informe o Nº de salas");
                return false;
            }
            if (document.getElementById("txtNumGaragem").value === "") {
                alert("Informe o Nº de vagas pra garagem do Imóvel");
                return false;
            }
            if (document.getElementById("txtTamanho").value === "") {
                alert("Informe o Tamanho do Imóvel");
                return false;
            }
            if (document.getElementById("txtCodEndereco").value === "") {
                alert("Informe o Código de Endereço do Imóvel");
                return false;
            }
            if (document.getElementById("txtPreco").value === "") {
                alert("Informe o Preço do Imóvel");
                return false;
            }
            if (document.getElementById("cmbTipoImovel").selectedIndex === 0) {
                alert("Informe o Tipo do Imóvel");
                return false;
            }
            return true;
        }

        function carregacombo(indice) {
            document.getElementById("cmbTipoImovel").selectedIndex = indice;
            alert(x);
        }
    </script>
    <body>
        <div id="container2">
            <div id="header">
                <img src="imagens/logoaph.png"/>
                <div class="tituloPri">Sistema de Cadastro de Imóveis</div>
            </div>
            <div id="leftBar">
                <div class="menu">Cadastros</div>
                <a class="menuItem" href="ServletPrincipal?acao=venda">Venda</a>
                <br/>
                <a class="menuItem" href="ServletPrincipal?acao=aluguel">Aluguel</a>
                <br/>
                <a class="menuSair" href="ServletPrincipal?acao=logout">Sair</a>
            </div>
            <div id="content">
                <form id="formCadastro" name="formCadastro" action="ServletPrincipal" method="post">
                    <div class="tituloCadastros">Cadastro de Imóvel
                        <%
                            String cadastro = (String) request.getAttribute("cadastro");
                            if (cadastro != null) {
                                out.print(String.format("para " + cadastro));
                            }
                            Imovel imov = (Imovel) request.getAttribute("imovel");
                        %>

                    </div>
                    <br/>
                    <label class="labelsEsquerda" for="txtCodigo">Código:</label>
                    <input readonly="true" style="background-color: #afafaf " class="inputsEsquerda" id="txtCodigo" name="txtCodigo" type="text" value="<%=imov != null ? imov.getId() : ""%>"/>
                    <br/>
                    <label class="labelsEsquerda" for="txtNumQuartos">Nº de Quartos:</label>
                    <input class="inputsEsquerda" id="txtNumQuartos" name="txtNumQuartos" type="text" value="<%=imov != null ? imov.getNumQuartos() : ""%>"/>
                    <br/>
                    <label class="labelsEsquerda" for="txtNumSalas">Nº de Salas:</label>
                    <input class="inputsEsquerda" id="txtNumSalas" name="txtNumSalas" type="text" value="<%=imov != null ? imov.getNumGaragem() : ""%>"/>
                    <br/>
                    <label class="labelsEsquerda" for="txtNumGaragem">Vagas Para Garagem:</label>
                    <input class="inputsEsquerda" id="txtNumGaragem" name="txtNumGaragem" type="text" value="<%=imov != null ? imov.getNumGaragem() : ""%>"/>
                    <br/>
                    <label class="labelsDireita" for="txtTamanho">Tamanho(m²):</label>
                    <input class="inputsDireita" id="txtTamanho" name="txtTamanho" type="text" value="<%=imov != null ? imov.getMetragem() : ""%>"/>
                    <br/>
                    <label class="labelsComboCadastro" for="cmbTipoImovel">Tipo:</label>
                    <select class="inputsCombo" id="cmbTipoImovel" name="cmbTipoImovel" >
                        <option></option>
                        <option value="1">Apartamento</option>
                        <option value="2">Casa</option>
                        <option value="3">Casa Pré-Fabricada</option>
                        <option value="4">Imóvel Comercial</option>
                    </select>
                    <br/>
                    <label style="margin-top: -75px;" class="labelsDireita" for="txtCodEndereco">Cod.Endereço:</label>
                    <input style="margin-top: -75px;" class="inputsDireita" id="txtCodEndereco" name="txtCodEndereco" type="text" value="<%=imov != null ? imov.getEndereco().getId() : ""%>"/>
                    <br/>
                    <label style="margin-top: -65px;" class="labelsDireita" for="txtPreco">Preço (R$):</label>
                    <input style="margin-top: -65px;" class="inputsDireita" id="txtPreco" name="txtPreco" type="text" value="<%=imov != null ? imov.getPreco() : ""%>"/>
                    <br/>
                    <input class="buttons" type="button" onclick="limpar()" value="Limpar"/>
                    <input class="buttons" type="button" onclick="salvar()" value="Salvar"/>
                    <input class="buttons" type="button" onclick="excluir()" value="Excluir"/>
                    <input class="buttons" type="submit" value="Atualizar"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" id="acao" name="acao" value="
                           <%
                               String valorAcao = (String) request.getAttribute("cadastro");
                               if (valorAcao != null) {
                                   if (valorAcao.equals("aluguel")) {
                                       out.print("aluguel");
                                   } else if (valorAcao.equals("venda")) {
                                       out.print("venda");
                                   }
                               } else {
                                   out.print("aluguel");
                               }
                           %>" />
                    <br/>
                    <br/>
                    <% Boolean valorEnd = (Boolean) request.getAttribute("EndNulo");
                    if(valorEnd != null){
                        %>
                        <script type="text/javascript"> alert("Não existe no Banco o Cod.Endereço informado para esse imóvel!!"); </script>
                    
                    <%
                    }
                    %>
                    
                    
                    <div class="tabela">
                        <table style="width:100%" border="1">
                            <thead class="cabecalhoTabela">
                                <tr><td style="width: 30px">Código</td>
                                    <td style="width: 30px">Nº de Quartos</td>
                                    <td style="width: 30px">Nº de Salas</td>
                                    <td style="width: 30px">Vagas Para Garagem</td>
                                    <td style="width: 28px">Tipo</td>
                                    <td style="width: 30px">Tamanho m²</td>
                                    <td style="width: 30px">Cod.Endereço:</td>
                                    <td style="width: 74px">Preço R$</td>
                                </tr>
                            </thead>
                            <tbody>                    
                                
                                <%
                                    List<Imovel> listaimoveis = (List<Imovel>) request.getAttribute("ListaImovel");
                                    if (listaimoveis != null) {
                                        for (Imovel imovel : listaimoveis) {
                                            out.print(String.format(""
                                                    + "<tr onClick=\"clickTabela(%d,%d)\" class=\"linhaTabela\">", imovel.getId(), imovel.getTipo()));
                                            String tipoImovel = "";
                                            if (imovel.getTipo() == 1) {
                                                tipoImovel = "Apartamento";
                                            } else if (imovel.getTipo() == 2) {
                                                tipoImovel = "Casa";

                                            } else if (imovel.getTipo() == 3) {
                                                tipoImovel = "Casa Pré-Fabricada";

                                            } else if (imovel.getTipo() == 4) {
                                                tipoImovel = "Imóvel Comercial";
                                            }

                                            out.print(String.format(""
                                                    + "<td class=\"linhaTabela\">%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>"
                                                    + "<td>%s</td>",
                                                    imovel.getId(),
                                                    imovel.getNumQuartos(),
                                                    imovel.getNumSalas(),
                                                    imovel.getNumGaragem(),
                                                    tipoImovel,
                                                    imovel.getMetragem(),
                                                    imovel.getEndereco().getId(),
                                                    imovel.getPreco()));
                                            out.print("</tr>");
                                        }
                                    }
                                %>
                            </tbody>                    
                        </table>
                    </div>
                </form>
            </div>
            <div id="footer">
                <div class="tituloSec">APH Imóveis, aqui você sonha, ela realiza!!!</div>
            </div>
        </div>        
    </body>
</html>