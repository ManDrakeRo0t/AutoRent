<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Spring Security Example </title>
</head>
<body>
add new user
${msg}
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Name: <input type="text" name="name"/> </label></div>
    <div><label> surname: <input type="text" name="surname"/> </label></div>
    <div><label> Number of DL: <input type="number" name="driving_license_number"/> </label></div>
    <div><label> Date of DL: <input type="date" name="driving_license_date"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>