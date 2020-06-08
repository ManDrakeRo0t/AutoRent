let myMap;
let searchControl;
let FinalPrice;
let orderForm = document.querySelector('#orderForm');
setTimeout(init,1500);
getCurrentTime();
changePriceLabel();
 $( "#carSelect" ).change(changePriceLabel);


function changeButtons(a) {
 if(a.id === "a1"){
    a.classList.add("btn-dark")
     a.classList.remove("btn")
     document.querySelector("#a2").classList.add("btn")
     document.querySelector("#a2").classList.remove("btn-dark")
     document.querySelector("#carspanel").style.display = "none"
     document.querySelector("#orderspanel").style.display = "block"
 }else{
     a.classList.add("btn-dark")
     a.classList.remove("btn")
     document.querySelector("#a1").classList.add("btn")
     document.querySelector("#a1").classList.remove("btn-dark")
     document.querySelector("#carspanel").style.display = "block"
     document.querySelector("#orderspanel").style.display = "none"
 }
}
function date_diff_indays(date1, date2) {
    let dt1 = new Date(date1);
    let dt2 = new Date(date2);
    return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
}


function calculatePrice(event) {
    let carName = document.querySelector('#carSelect').value;
    let price = CarPriceMap.get(carName)
    let start = document.querySelector('#dateStart').value;
    let end = document.querySelector('#dateEnd').value;
    let Tstart = document.querySelector('#timeStart').value;
    let Tend = document.querySelector('#timeEnd').value;
    document.querySelector('#dateEnd').min = start;
    if(start > end ) document.querySelector('#dateEnd').value = start;
    if(start === end){
        let c = ++Tstart
        Tstart-=1
        for(let i = 0;i<c;i++){
            document.querySelector('#timeEnd').options[i].disabled = true;
            if(Tstart > document.querySelector('#timeEnd').options[document.querySelector('#timeEnd').selectedIndex].value) document.querySelector('#timeEnd').selectedIndex = 23
            Tend = document.querySelector('#timeEnd').value;
        }
    }else{
        for(let i = 0;i<24;i++){
            document.querySelector('#timeEnd').options[i].disabled = false
        }
    }
    let Start = start.replace('-','/').replace('-','/');
    let End = end.replace('-','/').replace('-','/');
    console.log(Start,End);
    console.log(Tstart , Tend);
    let diff = date_diff_indays(Start,End);
    let res;
    if(diff === 0){
        res = Tend - Tstart;
    }else{
        res = (diff * 24) + (Tend - Tstart) * 1  ;
    }
    price = price.replace( /\s/g,"");
    console.log(price)
    res *= price
    FinalPrice = res;
    console.log(res)
    document.querySelector('#FinalPrice').innerHTML = 'Игововая стоимость аренды : '  + res + ' руб.' ;
}


function changePriceLabel(){
    let carName = document.querySelector('#carSelect').value;
    //alert(carName + " " +  CarPriceMap.get(carName))
    document.querySelector('#Price').innerHTML = 'Цена аренды за час : ' + CarPriceMap.get(carName) + ' руб.';
    calculatePrice();
}


function init() {
    myMap = new ymaps.Map('map', {
        center: [55.74, 37.58],
        zoom: 13,
        controls: []
    });

    searchControl = new ymaps.control.SearchControl({
        options: {
            provider: 'yandex#search'
        }
    });

    myMap.controls.add(searchControl);

}




function getCurrentTime() {
    let now = new Date()
    let year  = now.getFullYear();
    let month = now.getMonth()+1;
    let day   = now.getDate();
    if(day.toString().length === 1) {
        day = '0'+day;
    }
    if(month.toString().length === 1) {
        month = '0'+month;
    }

    document.querySelector('#dateStart').min = year +'-'+ month +'-'+ day;
    document.querySelector('#dateStart').value = year +'-'+ month +'-'+ day;
    document.querySelector('#dateEnd').min = year +'-'+ month +'-'+ day;

}
orderForm.onclick = function (event) {
    event.preventDefault();
    console.log("cheeeee")
    let form = document.querySelector('#OrderForm');
    if(isNaN(FinalPrice) || FinalPrice <= 0) alert("Заполните форму корректно");
    else form.submit();
};

