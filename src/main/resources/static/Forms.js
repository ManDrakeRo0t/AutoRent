
let sendForm = document.querySelector('#sendForm');

// orderForm.onclick = function (event) {
//     event.preventDefault();
//     console.log("cheeeee")
//     let form = document.querySelector('#OrderForm');
//     if(isNaN(FinalPrice) || FinalPrice <= 0) alert("Заполните форму корректно");
//     else form.submit();
// };

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
        }else if(form.elements[i].name === 'driving_license_number'){
            let number = form.elements[i].value;
            if(number.length === 10) counter++
        }else{
            if(form.elements[i].value.length !== 0 && form.elements[i].value !== " ") counter++
        }

    }
    console.log(counter)
    if(counter === 6) form.submit();
    else alert("Заполните форму корректно");
};