<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <@c.head "Личный кабинет "></@c.head>

    <div class="section">
        <div class="container md-5 mt-5 text-light bg-dark">
            <div class="card bg-dark">
                <div class="card-title text-center">Персональная информация : </div>
                <div class="card-body">

                            <div><p style="color: #17a2b8;display: inline">Имя : </p>${user.getName()}</div>
                            <div><p style="color: #17a2b8;display: inline">Фамилия : </p>${user.getSurname()}</div>


                            <div><p style="color: #17a2b8;display: inline">Номер Удостоверения : </p>${user.getDriving_license_number()}</div>
                            <div><p style="color: #17a2b8;display: inline">Дата Выдачи Удостоверения : </p>${user.getDriving_license_date()}</div>

                </div>
            </div>

            <div class="mt-5">
                <div class="text-center mt-5">Список ваших заказов : </div>
            </div>
            <#if orders?has_content>
                <div class="container mt-5">
                    <#list orders as order>
                        <div class="row myDark" style="margin-bottom: 5% ; border-radius: 15px" >
                            <div class="col-3 text-justify" >
                                <p style="color: #17a2b8 ; display: inline">Номер заказа : </p>${order.id}<br>
                                <p style="color: #17a2b8; display: inline">Автомобиль : </p>${order.car.mark} - ${order.car.model}<br>
                                <p style="color: #17a2b8; display: inline">Стоимость : </p>${order.price} руб.<br>
                                <p style="color: #17a2b8; display: inline">Статус заказа : </p> <br>
                                <#if order.status>
                                        <p style="color: #3db82e;display: inline"><i class="fas fa-check-circle"></i> Принят</p>
                                    <#else >
                                        <p style="color: #d1172f;display: inline"><i class="fas fa-times-circle"></i> Не Принят</p>
                                </#if>
                                <#if order.payment_status>
                                    <p style="color: #3db82e"><i class="fas fa-check-circle"></i> Оплачен</p>
                                <#else >
                                    <p style="color: #d1172f"><i class="fas fa-times-circle"></i> Не Оплачен</p>
                                </#if>

                            </div>
                            <div class="col-3">
                                <img class="img-fluid" src="/static/res/${order.car.mark}/${order.car.model}/1.jpg">
                            </div>
                            <div class="col-4">
                                <div class="text-info">Детали заказа : </div> ${order.details}
                            </div>
                            <div class="col-2">
                                <form action="/personal/delete/${order.id}" method="post">
                                    <input type="submit" name="name" class="btn btn-primary btn-dark mt-3" value="Отменить заказ">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                </form>

                                <form action="/personal/payment/${order.id}" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <#if order.status>
                                        <button type="submit" class="btn btn-primary btn-dark mt-3">Оплатить</button>
                                    <#else >
                                        <button type="button" class="btn btn-primary btn-dark mt-3 disabled">Оплатить</button>
                                </#if>
                                </form>
                            </div>
                        </div>
                    </#list>
                </div>
                <#else >
                <div>Список заказаов пуст! <a href="/order">Оформить прямо сейчас <i class="fas fa-chevron-circle-right"></i></a></div>
            </#if>

        </div>

    </div>

    <@c.footer></@c.footer>


</@c.html>
