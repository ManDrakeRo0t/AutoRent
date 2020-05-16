<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <@c.head "Список автомобилей"></@c.head>



    <div class="section">
        <div class="container mt-5 md-5 text-light">
            <form method="post" action="/filter" >
                <div class="form-group">
                    <label for="max">
                        Цена аренды до :
                    </label>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input name="maxPrice" type="number" class="form-control bg-dark w-25 text-light" id="max"/>
                </div>
                <div><input type="submit" value="Выбрать" class="btn btn-primary btn-dark"/></div>
            </form>
            <#if cars?has_content>
            <#list cars as car>
                <div class="card bg-dark md-5 mt-5">

                        <h4 class="text-center">${car.mark} : ${car.model}<br>${car.carClass.name}</h4>

<#--                        <img class="card-img-top w-50" src="static/res/${car.mark}/${car.model}/1.jpg"/>-->

<#--                        <div class="card-text">-->
<#--                            <span>${car.description}</span>-->
<#--                        </div>-->
                    <div class="row">
                        <div class="col-6">
                            <img class="img-fluid w-100" src="static/res/${car.mark}/${car.model}/1.jpg"/>
                        </div>
                        <div class="col-6">
                            <span>${car.description}</span>
                        </div>
                    </div>

                    <div class="card-footer text-right">
                        <p>Цена за час аренды : ${car.price} руб.</p><a href="/cars?car=${car.mark}+${car.model}">Подробнее <i class="fas fa-chevron-circle-right"></i></a>
                    </div>


                </div>
            </#list>

            <#else>
                <span class="mt-5 md-5 text-light text-center">Машин не найдено</span>
            </#if>
        </div>
    </div>


    <@c.footer></@c.footer>


</@c.html>
