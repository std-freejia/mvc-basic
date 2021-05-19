<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
    <!-- jsp의 프로퍼티 접근법 ( 모델에 들어있는 데이터를 꺼낼 수 있다) -->
      <li>id=${member.id}</li>
      <li>username=${member.username}</li>
      <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>