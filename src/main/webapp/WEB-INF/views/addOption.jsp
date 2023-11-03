<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Option</title>
    <script>
        function ValidationEvent() {
            var name = document.getElementById("optionName").value;
            if (name == '' || name.length < 3 || name.length > 50) {
                alert("Option should be 3 to 50 letters");
                return false;
            }
            return true;
        }
        </script>
</head>
<body>
<div class="container">
    <h2 class="text-center mt-4">Add Option for ${poll.pollName}</h2>
    <table class="table table-striped option-table">
        <thead>
        <tr>
            <th>Option Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${options}" var="option">
            <tr>
                <td>${option.optionName}</td>
                <td>
                    <button class="btn btn-danger btn-sm">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="/pollOption/handleForm" method="post" onsubmit="return ValidationEvent()">
        <div class="form-group">
            <label for="optionName">Add Option</label>
            <input type="text" class="form-control" id="optionName" placeholder="Enter Option Name" required name="optionName" style="width: 200px;">
        </div>
        <input type="hidden" required name="pollId" value="${poll.pollId}">
        <button type="submit" class="btn btn-primary">Add</button>
        <a href="/admin/home" class="btn btn-outline-primary">Back</a>
    </form>
</div>

</body>
</html>
