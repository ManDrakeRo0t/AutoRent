let myMap
let searchControl
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

setTimeout(init,1500)

$(function(){
    window.setTimeout(function(){
        $('#my-alert').alert('close');
    },5000);
});

let sendForm = document.querySelector('#sendForm');
sendForm.onclick = function (event) {
    event.preventDefault();

    let form = document.querySelector('#RegForm');

    let counter = 0;
    for(let i = form.elements.length - 1;i>= 0;i--){
        //console.log(form.elements[i]);
        if(form.elements[i].type === 'submit' || form.elements[i].type === 'hidden'){
            continue;
        }
        if(form.elements[i].name === 'username'){
           let mail = form.elements[i].value;
           let parts = mail.split("@");
           if(parts.length === 2 && parts[0].length !== 0 && parts[1].length !== 0 && parts[1].includes(".") && parts[1].charAt(parts[1].length-1) !== ".") counter++
        }else{
            if(form.elements[i].value.length !== 0 && form.elements[i].value !== " ") counter++
        }

    }
    console.log(counter)
    if(counter === 6) form.submit();
    else alert("Заполните форму корректно");
}

