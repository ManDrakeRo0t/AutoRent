<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <@c.head "Доступные классы автомобилей"></@c.head>

    <div class="section mt-5 md-5">
        <div class="container mt-5 md-5 text-light center"style="flex-direction: column ; align-items: center">
            <#list  classes as class>
                <a name="${class.name}"></a>
                <div class="card w-50 bg-dark md-5 " style=" margin: 2rem">
                    <div class="card-title text-center">${class.name}<hr style="color: white"></div>
                    <div class="card-body text-center">${class.description}</div>
                    <div class="card-footer text-center"><i class="fas fa-exclamation-circle"></i> Требуемый возраст : ${class.required_age} , Стаж вождения не менее ${class.required_age -19 } лет <i class="fas fa-exclamation-circle"></i></div>
                </div>
            </#list>
        </div>
    </div>

    <@c.footer></@c.footer>


</@c.html>

