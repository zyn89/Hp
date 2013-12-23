<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="javax.xml.parsers.*,org.w3c.dom.*"%>
<%
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
    <title>QUESTION</title>
  </head> 
  <body bgcolor="#79CDCD">
   <%  
    DocumentBuilderFactory questionDBF=DocumentBuilderFactory.newInstance();   
 DocumentBuilder questionDB = questionDBF.newDocumentBuilder();
    Document document=questionDB.parse(pageContext.getServletContext().getResourceAsStream ("question.xml"));
    Element element = document.getDocumentElement();       
    NodeList questions = element.getElementsByTagName("question");
    %>
    <form name="questionform" id="questionform" action="questionsave.jsp" method="POST">     
    <%
    out.println("<input type="+"\"hidden\""+" name="+"\"question_id\""+" value="+"\""+element.getAttribute("id")+"\""+"/>");
    for (int i = 0; i < questions.getLength(); i++) {
   Element questionElement = (Element) questions.item(i);     
   if(Integer.parseInt(questionElement.getAttribute("type"))==1){
    NodeList childNodes = questionElement.getChildNodes();    
    for (int j = 0; j< childNodes.getLength(); j++) {
     //Element answerElement = (Element)answers.item(j);
     if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
      if ("title".equals(childNodes.item(j).getNodeName())) {
       out.println("<h3>"+questionElement.getAttribute("id")+"、"+childNodes.item(j).getFirstChild().getNodeValue()+"</h3>");
       //out.println("<input type="+"\"hidden\""+" name="+"\"question_single_"+questionElement.getAttribute("id")+"\""+" value="+"\""+questionElement.getAttribute("id")+"\""+"/>");  
      }else if ("answer".equals(childNodes.item(j).getNodeName())) {
          NodeList itemNodes=childNodes.item(j).getChildNodes();
       for(int k=0;k<itemNodes.getLength();k++){
        //Element itemElement=(Element)itemNodes.item(k);
        if(itemNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
         if("it".equals(itemNodes.item(k).getNodeName())){
          out.println("<input name="+"\"sing_"+questionElement.getAttribute("id")+"_"+questionElement.getAttribute("type")+"\" "+" type="+"\"radio\""+" value="+"\""
          +itemNodes.item(k).getFirstChild().getNodeValue()+"\""+">"+itemNodes.item(k).getFirstChild().getNodeValue()+"</input>");         
         }
        }
       }     
      }
     }
     }
   }
   if(Integer.parseInt(questionElement.getAttribute("type"))==2){
    NodeList childNodes = questionElement.getChildNodes();    
    for (int j = 0; j< childNodes.getLength(); j++) {    
     if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
      if ("title".equals(childNodes.item(j).getNodeName())) {
       out.println("<h3>"+questionElement.getAttribute("id")+"、"+childNodes.item(j).getFirstChild().getNodeValue()+"</h3>");
       //out.println("<input type="+"\"hidden\""+" name="+"\"question_chebox_"+questionElement.getAttribute("id")+"\""+" value="+"\""+questionElement.getAttribute("id")+"\""+"/>");  
      }else if ("answer".equals(childNodes.item(j).getNodeName())) {
          NodeList itemNodes=childNodes.item(j).getChildNodes();
       for(int k=0;k<itemNodes.getLength();k++){
        //Element itemElement=(Element)itemNodes.item(k);
        if(itemNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
         if("it".equals(itemNodes.item(k).getNodeName())){
          out.println("<input name="+"\"che_"+questionElement.getAttribute("id")+"_"+questionElement.getAttribute("type")+"\" "+" type="+"\"checkbox\""+" value="+"\""
          +itemNodes.item(k).getFirstChild().getNodeValue()+"\""+">"+itemNodes.item(k).getFirstChild().getNodeValue()+"</input>");
         }
        }
       }     
      }
     }
     }
   }
   if(Integer.parseInt(questionElement.getAttribute("type"))==3){
    NodeList childNodes = questionElement.getChildNodes();       
    for (int j = 0; j< childNodes.getLength(); j++) {    
     if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
      if ("title".equals(childNodes.item(j).getNodeName())) {
       out.println("<h3>"+questionElement.getAttribute("id")+"、"+childNodes.item(j).getFirstChild().getNodeValue()+"</h3>");
       //out.println("<input type="+"\"hidden\""+" name="+"\"question_answer_"+questionElement.getAttribute("id")+"\""+" value="+"\""+questionElement.getAttribute("id")+"\""+"/>");
      }else if ("answer".equals(childNodes.item(j).getNodeName())) {
          NodeList itemNodes=childNodes.item(j).getChildNodes();
       for(int k=0;k<itemNodes.getLength();k++){
        //Element itemElement=(Element)itemNodes.item(k);
        if(itemNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
         if("it".equals(itemNodes.item(k).getNodeName())){
          out.println("<textarea name="+"\"te_"+questionElement.getAttribute("id")+"_"+questionElement.getAttribute("type")+"\" "+" rows="+"\"4\""+" cols="+"\"100\""+" value="+"\""
          +itemNodes.item(k).getFirstChild().getNodeValue()+"\""+">"+itemNodes.item(k).getFirstChild().getNodeValue()+"</textarea>"+"<br>");
         }
        }
       }     
      }
     }
     }
   }  
    }
   %>
   <br/>   
     用户名：<input type="text" name="username" id="username" value="" size="20" />&nbsp;&nbsp;                               
          <input type="submit" value="提交" name="submit" />&nbsp;&nbsp;
          <input type="reset" value="重置" name="reset" />              
      </form>
  </body>
</html>
