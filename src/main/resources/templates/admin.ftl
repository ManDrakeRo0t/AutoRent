<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <@c.head "Управение заказами"></@c.head>

    <div class="section">
        <div class="container text-light mt-5 md-5 ">

            <a href="/administration" class="btn btn-primary btn-dark mt-3">Показать все</a>
            <a href="/administration?view=notReviewed" class="btn btn-primary btn-dark mt-3">Показать не рассмотренные</a>
            <form method="post" action="/administration" >
                <div class="form-group">
                    <label for="id">
                        Найти по номеру заказа :
                    </label>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input name="maxPrice" type="number" class="form-control bg-dark w-25 text-light" id="id"/>
                </div>
                <div><input type="submit" value="Выбрать" class="btn btn-primary btn-dark"/></div>
            </form>
            <#if msg??>
                <p style="color: #17a2b8; display: inline"> Ничего не найдено </p>
                <#else >
                    <#if orders?has_content>
                        <#list orders as order >
                            <div class="row myDark" style="margin-top: 4%; border-radius: 15px">
                                <div class="col-2">
                                    <p style="color: #17a2b8; display: inline">Статус заказа : </p> <br>
                                    <#if order.review>
                                        <p style="color: #3db82e;display: inline"><i class="fas fa-check-circle"></i> Рассмотрен<br></p>
                                    <#else >
                                        <p style="color: #d1172f;display: inline"><i class="fas fa-times-circle"></i> Не Рассмотрен<br></p>
                                    </#if>
                                    <#if order.status>
                                        <p style="color: #3db82e;display: inline"><i class="fas fa-check-circle"></i> Принят<br></p>
                                    <#else >
                                        <p style="color: #d1172f;display: inline"><i class="fas fa-times-circle"></i> Не Принят<br></p>
                                    </#if>
                                    <#if order.payment_status>
                                        <p style="color: #3db82e"><i class="fas fa-check-circle"></i> Оплачен</p>
                                    <#else >
                                        <p style="color: #d1172f"><i class="fas fa-times-circle"></i> Не Оплачен</p>
                                    </#if>
                                </div>
                                <div class="col-4">
                                    <p style="color: #17a2b8; display: inline"> Номер : </p>${order.id}<br>
                                    <p style="color: #17a2b8; display: inline">Стоимость : </p>${order.price} руб.<br>
                                    <p style="color: #17a2b8; display: inline">Автомобиль : </p>${order.car.mark} - ${order.car.model}<br>
                                    <p style="color: #17a2b8; display: inline">Адрес : </p>${order.punct_from.city.name} ${order.punct_from.address}
                                </div>
                                <div class="col-4">
                                    <p style="color: #17a2b8; display: inline"> Детали заказа : </p><br>${order.details}
                                </div>
                                <div class="col-2">
                                    <#if order.review = false>
                                        <p>
                                            <button class="btn btn-primary btn-dark mt-5" type="button" data-toggle="collapse" data-target="#collapseExample${order.id}" aria-expanded="false" aria-controls="collapseExample${order.id}">
                                                Рассмотреть
                                            </button>
                                        </p>
                                    </#if>
                                </div>
                            </div>
                            <div class="collapse" id="collapseExample${order.id}">
                                <div class="card text-light bg-dark">
                                    <div class="row">
                                        <div class="col-6">
                                            <p style="color: #17a2b8; display: inline"> Имя : </p>${order.user.name}<br>
                                            <p style="color: #17a2b8; display: inline"> Фамилия : </p>${order.user.surname}<br>
                                            <p style="color: #17a2b8; display: inline"> Почта : </p>${order.user.username}<br>
                                            <p style="color: #17a2b8; display: inline"> Срок : </p>${order.getDate()}<br>
                                            <p style="color: #17a2b8; display: inline"> Класс автомобиля : </p>${order.car.carClass.name}<br>
                                            <p style="color: #17a2b8; display: inline"> Необходимый возраст : </p>${order.car.carClass.required_age}<br>
                                            <p style="color: #17a2b8; display: inline"> Необходимый стаж : </p>${order.car.carClass.required_age - 19}<br>
                                            <a target="_blank" href="https://xn--90adear.xn--p1ai/check/driver#${order.user.driving_license_number}+${order.user.parseDate()}">Проверить на Госавтоинспекции</a>
                                        </div>
                                        <div class="col-6">
                                            <form method="post" action="/administration/edit">
                                                <div class="form-group">
                                                    <label for="details" class="text-center">
                                                        Детали заказа :
                                                    </label>
                                                    <input type="text" name="details" class="form-control bg-dark text-light" id="details" value="${order.details}"/>
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                </div>
                                                    <label for="status" class="text-center">
                                                        Принять заказ :
                                                    </label>
                                                    <input type="checkbox" name="status" class="form-control bg-dark text-light" id="status"/>
                                                    <input type="hidden" name="id" value="${order.id}">
                                                    <input type="submit" value="Отправить" class="btn btn-primary mr-5" style="float: right"/>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </#list>
                    <#else >
                        <p style="color: #17a2b8; display: inline"> Ничего не найдено </p>
                    </#if>
            </#if>





        </div>
    </div>


    <@c.footer></@c.footer>


</@c.html>
