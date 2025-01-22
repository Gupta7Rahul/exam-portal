<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css"></link>
</head>
<body class="body_bg">
   <h4>Start test</h4>
   
   <form action="/test/submit" method="post">
        <c:forEach var="question" items="${questions}">
            <div>
                <p>${question.questionTitle}</p><br>
                <label><input type="radio" name="answers${question.id}" value="${question.option1}">${question.option1}</label><br><br>
                <label><input type="radio" name="answers_${question.id}" value="${question.option2}">${question.option2}</label><br><br>
                <label><input type="radio" name="answers_${question.id}" value="${question.option3}">${question.option3}</label><br><br>
                <label><input type="radio" name="answers_${question.id}" value="${question.option}">${question.option4}</label><br><br>
            </div>
        </c:forEach>
        <button type="submit">Submit</button>
    </form>
    
</body>
</html>
