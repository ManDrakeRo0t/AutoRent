<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.html>

<@c.header></@c.header>

<@c.head "Оформление заказа"></@c.head>

    <div class="section mt-5 p-md-5 align-items-center">
        <div class="container mt-5 text-light bg-dark">
            <#if msg??>
                <div class="text-light">${msg}</div>
                <#else >

                <div class="text-center">Выберите город<hr color="white"></div>
                <#list cities as city>
                    <div class="col">
                        <#if crnCity??>
                            <#if crnCity == city.name>
                                <a href="/order?city=${city.name}">Выбран  - ${city.name} <i class="fas fa-check-square"></i></a>
                                <#else>
                                    <a href="/order?city=${city.name}">${city.name}</a>
                            </#if>
                            <#else>
                                <a href="/order?city=${city.name}">${city.name}</a>
                        </#if>
                    </div>
                </#list>
                <#if cars?has_content && puncts?has_content>
                    <script>
                        let CarPriceMap = new Map()
                        <#list cars as car>
                            CarPriceMap.set('${car.mark} : ${car.model}','${car.price}')
                        </#list>
                    </script>
                    <div class="text-center">Форма заказа<hr color="white"></div>
                    <form action="/order" method="post" id="OrderForm">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="user" value="${name}">
                        <div class="row">
                            <div class="form-group col-6">
                                <label for="punctSelect">
                                    Выберите пукт получения :
                                </label>
                                <select id="punctSelect" name="punct" class="bg-dark text-light">
                                    <#list puncts as punct>
                                        <option>${punct}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group col-6" id="Select">
                                <label for="carSelect">
                                    Выберите автомобиль :
                                </label>
                                <select id="carSelect" class="carSel" name="car" class="bg-dark text-light">
                                    <#list cars as car>
                                        <option>${car.mark} : ${car.model}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="text-light" id="Price">Цена аренды за час : </div>
                                <div class="text-light" id="FinalPrice"></div>
                            </div>
                            <div class="form-group col-3">
                                <label for="dateStart">
                                    Начало аренды :
                                    <input type="date" name="date_from" id="dateStart" class="bg-dark text-light" onchange="calculatePrice()" >
                                    <select name="time_from" id="timeStart" class="bg-dark text-light" onchange="calculatePrice()">
                                        <#list 0..23 as x>
                                            <option value="${x}">${x}:00</option>
                                        </#list>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group col-3">
                                <label for="dateEnd">
                                     Конец аренды :
                                    <input type="date" name="date_to" id="dateEnd" class="bg-dark text-light" onchange="calculatePrice()">
                                    <select name="time_to" id="timeEnd" class="bg-dark text-light" onchange="calculatePrice()">
                                        <#list 0..23 as x>
                                            <option value="${x}">${x}:00</option>
                                        </#list>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="center"><input type="submit"  value="Оформить заказ" class="btn btn-primary btn-dark" id="orderForm"/></div>
                    </form>
                </#if>
            </#if>
        </div>
    </div>

    <@c.footer></@c.footer>

</@c.html>
