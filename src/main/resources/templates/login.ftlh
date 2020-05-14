<#import "parts/common.ftlh" as c>

<@c.html>

    <@c.header></@c.header>
    <div class="section mt-5 p-md-5 align-items-center" style="padding-top: 200px">
        <div class="container mt-5 text-light ">
            <a href="registration">Регистрация</a>
                <div>
                    <form action="/login" method="post">
                        <div class="form-group">
                            <label for="exampleInputEmail1">
                                Почта :
                            </label>
                            <input name="username" type="email" class="form-control bg-dark w-25 text-light" id="exampleInputEmail1" aria-describedby="emailHelp"/>
                            <small id="emailHelp" class="form-text text-muted">Конфиденциальность клиентов превыше всего</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">
                                Пароль :
                            </label>
                            <input type="password" name="password" class="form-control bg-dark w-25 text-light" id="exampleInputPassword1"/>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <div><input type="submit" value="Войти" class="btn btn-primary btn-dark"/></div>
                    </form>
                </div>
        </div>
    </div>
    <@c.footer></@c.footer>
</@c.html>
