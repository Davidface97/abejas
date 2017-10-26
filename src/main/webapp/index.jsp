<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.co.sergio.mundo.vo.*"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <style type="text/css">
        body {
            background-image:
                url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
        }
    </style>
    <script src="https://cdn.onesignal.com/sdks/OneSignalSDK.js" async='async'></script>
  <script>
    var OneSignal = window.OneSignal || [];
    OneSignal.push(["init", {
      appId: "e9e9af49-494f-4337-87cb-16705b3dbd57",
      autoRegister: true, /* Set to true to automatically prompt visitors */
      notifyButton: {
          enable: true /* Set to false to hide */
      }
    }]);
  </script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Dashboard</title>
    </head>
    <body>
        <div align="center" style="margin-top: 50px;">
            <h2>Dashboard</h2>
        </div>

        <%
           if( request.getAttribute("departamentos")!=null){
              List<Departamento> departamentos  = (List<Departamento>)request.getAttribute("departamentos");
               for (Departamento departamento : departamentos) {
        %>      
        <h1> <%=departamento.getNom_departamento()%> </h1><br/> 
        <%      
         }
      }  
      
    
        %>
            <div align="center" style="width: 500">
                <img src="BarServlet" />
                <img style="margin-left: 160px" src="ChartServlet" />
            </div>


        Descargar Excel <a href="HSSFCreate">Descargar</a> 

    </body>
</html>
