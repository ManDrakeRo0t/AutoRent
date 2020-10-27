<#import "parts/common.ftl" as c>

<@c.html>

    <@c.header ></@c.header>

    <#if car??>

        <@c.head "Подробнее о  ${car.mark} : ${car.model}"></@c.head>


        <div class="section">
            <div class="container mt-5 md-5 text-light">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <#if car.pictures?number != 0>
                            <#list 1..car.pictures?number as x >
                                <#if x == 1>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <#else >
                                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                                </#if>
                            </#list>
                        </#if>
                    </ol>
                    <div class="carousel-inner mt-5 md-5">
                        <#if car.pictures?number != 0>
                            <#list 1..car.pictures?number as x >
                                <#if x == 1>
                                    <div class="carousel-item active">
                                        <img class="d-block w-100 img-fluid" src="/static/res/${car.mark}/${car.model}/${x?abs}.jpg" alt="Первый слайд">
                                    </div>
                                <#else >
                                    <div class="carousel-item">
                                        <img class="d-block w-100 img-fluid" src="/static/res/${car.mark}/${car.model}/${x?abs}.jpg" alt="Первый слайд">
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                        <#--                    <div class="carousel-item ">-->
                        <#--                        <img class="d-block w-100 img-fluid" src="/static/res/${car.mark}/${car.model}/1.jpg" alt="Первый слайд">-->
                        <#--                    </div>-->
                        <#--                    <div class="carousel-item">-->
                        <#--                        <img class="d-block w-100 img-fluid" src="/static/res/${car.mark}/${car.model}/2.jpg" alt="Второй слайд">-->
                        <#--                    </div>-->
                        <#--                    <div class="carousel-item">-->
                        <#--                        <img class="d-block w-100 img-fluid" src="/static/res/${car.mark}/${car.model}/3.jpg" alt="Третий слайд">-->
                        <#--                    </div>-->
                        <#--                    <div class="carousel-item">-->
                        <#--                        <img class="d-block w-100 img-fluid"  src="/static/res/${car.mark}/${car.model}/4.jpg" alt="Четвертый слайд">-->
                        <#--                    </div>-->
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

                <div class="container text-light mt-5 md-5 bg-dark">
                    <div class="row mt-5">
                        <div class="col-3">
                            <span>
                                <i class="fas fa-chair"></i> Места : ${car.places}
                            </span>
                        </div>
                        <div class="col-3 ">
                            <span>
                                <i class="fas fa-cogs"></i> Коробка : ${car.gearbox}
                            </span>
                        </div>
                        <div class="col-3">
                            <span>
                                <i class="fas fa-gas-pump"></i> Топливо : ${car.fuel}
                            </span>
                        </div>
                        <div class="col-3">
                            <span>
                                <i class="fas fa-car"></i> Класс : ${car.carClass.name}
                            </span>
                        </div>
                    </div>
                    <div class="row mt-5 mb-5">
                        <span class="text-center">
                            <i class="fas fa-audio-description"></i> Описание : ${car.description}
                        </span>
                    </div>
                    <div class="row mt-5 text-center">
                        <div class="text-center">
                            <i class="fas fa-money-bill-wave-alt"></i> Цена за 1 час аренды : ${car.price} руб. / Цена за сутки ${car.price * 24} руб.
                        </div>
                    </div>
                    <div class="mt-5">
                        <div class="text-center">Можно заказать в этих городах : </div>
                        <ul class="list-group text-center">
                            <#list cities as city>
                                <li class="list-group-item bg-dark">
                                    ${city}
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>

            </div>
        </div>

        <#else >
            <@c.head "Машина не найдена"></@c.head>

    </#if>



    <@c.footer></@c.footer>


</@c.html>
