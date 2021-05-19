<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<html>

<!--
jsp가 서블릿으로 변환되기 때문에 request, response 는 문법상 지원 된다.
자바 코드를 그대로 다 사용할 수 있다.
-->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<body>
<ul>
      <li>id=<%=member.getId()%></li>
      <li>username=<%=member.getUsername()%></li>
      <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</head>
</html>