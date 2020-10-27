<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <@c.head "Админ панель"></@c.head>

    <div class="section">
        <div class="container text-light mt-5 md-5 ">
            <div class="row">
                <div class="col-1"></div>
                <div id="a1" onclick="changeButtons(this)" class="text-center col-4 btn-dark btn-primary " style="border-radius:5px">
                    Управление заказами
                </div>
                <div class="col-2"></div>
                <div id="a2" onclick="changeButtons(this)" class=" text-center col-4 btn btn-primary " style="border-radius:5px">
                    Управление автопарком
                </div>
            </div>
            <hr color="white">
            <div id="orderspanel">
                <div class="row">
                <div class="col-2">
                    <a href="/administration" class="btn btn-primary btn-dark mt-3">Показать все</a>
                </div>
                <div class="col-3">
                    <a href="/administration?view=notReviewed" class="btn btn-primary btn-dark mt-3" >Показать не рассмотренные</a>
                </div>
                <div class="col-4"><form method="post" action="/administration/deleteOrders">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn-dark btn-primary btn" >Удалить просроченные заказы</button>
                    </form></div>
                <div class="col-3">
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
                </div>
                </div>

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
                                    <form action="/administration/delete/${order.id}" method="post">
                                        <input type="submit" value="Удалить заказ" class="btn btn-primary mr-5" style="float: right"/>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    </form>
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
                                            <p style="color: #17a2b8; display: inline"> Другие заказы на этот автомобиль : </p><br>
                                            <#if mapOrders?has_content>
                                                <#if mapOrders[order.id?abs+"o"]?has_content>
                                                  <p>${mapOrders[order.id?abs+"o"]}</p>
                                                 </#if>
                                            </#if>

                                            <br><p style="color: #17a2b8; display: inline"> Класс автомобиля : </p>${order.car.carClass.name}<br>
                                            <p style="color: #17a2b8; display: inline"> Необходимый возраст : </p>${order.car.carClass.required_age}<br>
                                            <p style="color: #17a2b8; display: inline"> Необходимый стаж : </p>${order.car.carClass.required_age - 19}<br>
                                            <a target="_blank" href="https://xn--90adear.xn--p1ai/check/driver#${order.user.driving_license_number}+${order.user.parseDate()}">Проверить на Госавтоинспекции</a>
                                        </div>
                                        <div class="col-6">
                                            <form method="post" action="/administration/editOrder">
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
            <div id="carspanel">
                    <div>Добавлние автомобиля</div>
                    <form action="/administration/addCar" style="margin-bottom: 40px;" method="post" enctype="multipart/form-data">
                        <div class="row bg-dark">
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="mark">
                                        Марка :
                                    </label>
                                    <input type="text" name="mark" class="form-control bg-dark w-75 text-light" id="mark"/>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                </div>
                                <div class="form-group">
                                    <label for="model">
                                        Модель :
                                    </label>
                                    <input type="text" name="model" class="form-control bg-dark w-75 text-light" id="model"/>
                                </div>
                                <div class="form-group">
                                    <label for="price">
                                        Цена :
                                    </label>
                                    <input type="number" name="price" class="form-control bg-dark w-75 text-light" id="price"/>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="places">
                                        Места :
                                    </label>
                                    <input type="number" name="places" class="form-control bg-dark w-75 text-light" id="places"/>
                                </div>
                                <div class="form-group">
                                    <label for="gearbox">
                                        Коробка :
                                    </label>
                                    <input type="text" name="gearbox" class="form-control bg-dark w-75 text-light" id="gearbox"/>
                                </div>
                                <div class="form-group">
                                    <label for="fuel">
                                        Топливо :
                                    </label>
                                    <input type="text" name="fuel" class="form-control bg-dark w-75 text-light" id="fuel"/>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="carClass">
                                        Класс :
                                    </label>
                                    <select id="carClass"  name="carClass" class="bg-dark text-light">
                                        <#if classes?has_content>
                                            <#list classes as class>
                                                <option>${class.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="description">
                                        Описание :
                                    </label>
                                    <input type="text" name="description" class="form-control bg-dark w-75 text-light" id="description"/>
                                </div>
                                <div>
                                    <p>Загрузите фото автомобиля</p>
                                    <p><input type="file" name="file" multiple accept="image/*,image/jpeg">
                                </div>
                            </div>
                            <div class="col-3">
                                <button type="submit"  class="btn btn-primary mt-3">Добавить</button><br>
                            </div>
                        </div>
                    </form>
                <#if cars?has_content>
                    <#list cars as car >
                    <div class="row bg-dark" style="margin-bottom: 15px ; border-radius: 15px">
                        <div class="col-3">
                            ${car.mark + " " + car.model}<br>
                            <img class="img-fluid" src="/static/res/${car.mark}/${car.model}/1.jpg">
                        </div>
                        <div class="col-6">
                            <form action="/administration/editCar" method="post">
                                <input type="hidden" name="id" value="${car.id}">
                                <label><input type="number" name="price" value="${car.getNormalPrice()}">Цена за час</label>
                                <button type="submit" class="btn btn-primary btn mt-3">Сохранить</button><br>
                                <#list cities as city>

                                    <label><input type="checkbox" name="${"c"+city.id}" ${mapCars[car.mark + ":" + car.model]?seq_contains(city.name)?string("checked","")}>${city.name}</label><br>

                                </#list>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </form>
                        </div>
                        <div>
                            <form action="/administration/deleteCar/${car.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit"  class="btn btn-primary mt-3">Удалить</button><br>
                            </form>
                        </div>
                    </div>
                </#list>
                </#if>
                </div>
            </div>

    </div>


    <@c.footer></@c.footer>


</@c.html>
