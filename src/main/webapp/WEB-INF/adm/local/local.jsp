<%@page import="app.model.Local"%>
<%@page import="java.util.List"%>
<%@page import="app.zelper.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Local> locales = (List<Local>) request.getAttribute("locales");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title> Locales </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="/public/header.jsp" %>
    </head>

    <body>

        <%@include file="/public/menuGeneral.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">

                <%@include file="/public/menuAdm.jsp" %>

                <div class="span9">
                    <div class="row">
                        <a class=" btn btn-primary pull-right" href="<%=contextPath%>/adm/locales?action=<%=Constants.ACTION_CREATE%>"> Nuevo </a>
                        <h1> Locales </h1>
                    </div>
                    
                    <% if (!locales.isEmpty()) {%>
                    <table class="table table-striped table-hover"> 
                        <thead>
                        <th> Descripción </th>
                        <th> Dirección </th>
                        <th> Teléfono </th>
                        </thead>
                        <tbody>
                            <% for (Local local : locales) {%>
                            <tr>
                                <td> <%=local.getDescripcion()%> </td>
                                <td> <%=local.getDireccion()%> </td>
                                <td> <%=local.getTelefono()%> </td>
                                <td> 
                                    <div class="btn-group">
                                        <a class="dropdown-toggle" data-toggle="dropdown" role="menu"  href="#">
                                          <i class="icon-cog"></i>
                                        </a>
                                        <ul class="dropdown-menu pull-right">
                                            <li>
                                                <a href="<%=contextPath%>/adm/locales?action=<%=Constants.ACTION_UPDATE%>&id=<%=local.getId()%>">
                                                Editar<a>
                                            </li>
                                            <li>
                                                <a href="<%=contextPath%>/adm/locales?action=<%=Constants.ACTION_DELETE%>&id=<%=local.getId()%>">
                                                    Eliminar<a>
                                            </li>
                                        </ul>
                                      </div>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                        
                    <% }%>
                </div>
            </div>


            <hr>
        </div>

        <%@include file="/public/footer.jsp" %>
    </body>
</html>




