<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

        <@c.head "Пункты получения автомобилей"></@c.head>

        <div class="section " style="padding-bottom: 10%">
            <div class="container text-light mt-5 md-5 bg-dark">
                <#list cities as city >
                <div>
                    <ul><a href="/order?city=${city.name}">оформить</a> в ${city.name}</ul>
                    <#list city.puncts as punct>

                        <li class="ml-5"><a href="##" onclick="searchControl.search('${city.name} ${punct.address}')">${punct.address} <i class="fas fa-search-location"></i></a></li>
                    </#list>
                    <hr color="white">
                </div>
                </#list>

                <div class="container center" style="padding-bottom: 5%">
                    <div class="md-5" id="map" style="width: 800px; height: 400px"></div>
                </div>

            </div>
        </div>

    <@c.footer></@c.footer>


</@c.html>



