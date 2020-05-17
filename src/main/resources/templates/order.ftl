<#import "parts/common.ftl" as c>


<@c.html>

<@c.header></@c.header>

<@c.head "Оформление заказа"></@c.head>

    <div class="section mt-5 p-md-5 align-items-center">
        <div class="container mt-5 text-light">
<#--            <form action="/order" method="post" id="OrderForm">-->
<#--                <div class="form-group">-->
<#--                    -->
<#--                </div>   -->
<#--            </form>-->
            <#list cities as city>
                <div>
                    <a href="/order?city=${city.name}">${city.name}</a>
                </div>
            </#list>
            <#if puncts?has_content>
            <#list puncts as punct>
                <div> ${punct.address}</div>
            </#list>
            </#if>
            <#if cars?has_content>
                <#list cars as car>
                    <div class="ml-5">${car.mark} : ${car.model}</div>
                </#list>
            </#if>

        </div>
    </div>

    <@c.footer></@c.footer>

</@c.html>
