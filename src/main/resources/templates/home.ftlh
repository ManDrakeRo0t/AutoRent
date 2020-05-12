<#import "parts/common.ftlh" as c>

<@c.html>
    <div>Home page</div>
    <div><#list cq as item>
            <li>${item.id} ${item.name}</li>
        </#list>
    </div>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.html>
