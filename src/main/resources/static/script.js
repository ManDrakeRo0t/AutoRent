let sendForm = document.querySelector('#sendForm');
sendForm.onclick = function (event) {
    event.preventDefault();

    let form = document.querySelector('#RegForm');

    let counter = 0;
    for(let i = form.elements.length - 1;i>= 0;i--){
        console.log(form.elements[i]);
        if(form.elements[i].type === 'submit' || form.elements[i].type === 'hidden'){
            continue;
        }
        if(form.elements[i].value.length !== 0 && form.elements[i].value !== " ") counter++
    }
    if(counter === 6) form.submit();
    else alert("Заполните форму коректно");
}