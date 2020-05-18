<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header></@c.header>

    <@c.head "Регистрация"/>
    <div class="section mt-5 p-md-5 align-items-center" style="padding-top: 200px">
        <div class="container mt-5 text-light ">
            </div>
            <p> ${msg} </p>
                <form action="/registration" method="post" id="RegForm">
                    <div class="row text-light">
                        <div class="col-4">
                            <div class="form-group">
                                <label for="exampleInputEmail1">
                                    Почта :
                                </label>
                                <input name="username" type="email" class="form-control bg-dark w-75 text-light" id="exampleInputEmail1" aria-describedby="emailHelp"/>
<#--                                <small id="emailHelp" class="form-text text-muted">Конфиденциальность клиентов превыше всего</small>-->
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">
                                    Пароль :
                                </label>
                                <input type="password" name="password" class="form-control bg-dark w-75 text-light" id="exampleInputPassword1"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label for="nameF">
                                    Имя :
                                </label>
                                <input name="name" type="text" class="form-control bg-dark w-75 text-light" id="nameF"/>
                            </div>
                            <div class="form-group">
                                <label for="surF">
                                    Фамилия :
                                </label>
                                <input name="surname" type="text" class="form-control bg-dark w-75 text-light" id="surF"/>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label for="numF">
                                    Номер водительского удостоверения  :
                                </label>
                                <input name="driving_license_number" type="number" class="form-control bg-dark w-75 text-light" id="numF"/>
                            </div>
                            <div class="form-group">
                                <label for="dateF">
                                    Дата выдачи водительского удостоверения  :
                                </label>
                                <input name="driving_license_date" type="date" class="form-control bg-dark w-75 text-light" id="dateF"/>
                            </div>
                        </div>
                    </div>
                    <div class="center"><input type="submit" value="Зарегестрироваться" class="btn btn-primary btn-dark " id="sendForm"/></div>
                </form>
        </div>
    </div>

    <@c.footer></@c.footer>
</@c.html>
