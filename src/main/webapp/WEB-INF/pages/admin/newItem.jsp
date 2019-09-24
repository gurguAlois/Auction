<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My registration page</title>
    <link rel="stylesheet" type="text/css" href="/css/admin.css">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
            crossorigin="anonymous"></script>


    <script src="/js/login.js"></script>
</head>
<body>
<div class="adminArea">
    <h2>Add new item</h2>
    <form:form method="POST" action="newItem" modelAttribute="itemForm">
        <form:input path="name" id="name" type="text" class="normalInput"
                    placeholder="Item name"/>
        <p class="error"><form:errors path="name"/></p>
        <form:input path="description" id="description" type="text" class="normalInput"
                    placeholder="Item description here"/>
        <p class="error"><form:errors path="description"/></p>
        <form:input path="startingPrice" id="startingPrice" type="number" class="normalInput" placeholder="Starting price"/>
        <p class="error"><form:errors path="startingPrice"/></p>
        <form:input path="startDate" id="startDate" type="date" class="normalInput"
                    placeholder="Start date"/>
        <p class="error"><form:errors path="startDate"/></p>
        <form:input path="endDate" id="endDate" type="date" class="normalInput"
                    placeholder="End date"/>
        <p class="error"><form:errors path="endDate"/></p>
        <button>Add</button>

        <h2>${successMessage}</h2>
        <ul class="adminFooter">
            <li><a href="/admin/home">Home</a></li>
<%--            <li><a href="#">Forgot Password</a></li>--%>
        </ul>

    </form:form>
</div>
</body>
</html>
