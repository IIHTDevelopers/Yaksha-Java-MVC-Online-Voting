<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Login</title>
</head>
<body>
<div class="container">
  <h2>Admin Login</h2>
  <form action="/admin/handleForm" method="post">
    <div class="form-group">
      <label for="adminUsername">Username:</label>
      <input type="text" class="form-control" id="adminUsername" placeholder="Enter admin username" required name="username">
    </div>
    <div class="form-group">
      <label for="adminPassword">Admin Password:</label>
      <input type="password" class="form-control" id="adminPassword" placeholder="Enter admin password" required name="password">
    </div>
    <br>
    <button type="submit" class="btn btn-primary btn-block">Login</button>
    <a href="/voter/login" class="btn btn-primary btn-block">Login as Voter</a>
  </form>
</div>
</body>
</html>
