function validateEmail(input) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (re.test(input.value)) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function validatePassword(input) {
    const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;
    if (re.test(input.value)) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function validateFullName(input) {
    let length = input.value.length;
    if (length > 1 && length < 32) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function validateDescription(input) {
    let length = input.value.length;
    if (length > 50 && length < 512) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function validatePhoneNumber(input) {
    const re = /^(\+\d{1,3}( )?)?((\(\d{3}\))|\d{3})[- .]?\d{3}[- .]?\d{4}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?){2}\d{3}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?)(\d{2}[ ]?){2}\d{2}$/;
    if (re.test(input.value)) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function validateComment(input) {
    let length = input.value.length;
    if (length > 100 && length < 5096) {
        setValid(input);
    } else {
        setInvalid(input);
    }
}

function setValid(input) {
    input.classList.remove("is-invalid");
    input.classList.add("is-valid");
}
function setInvalid(input) {
    input.classList.remove("is-valid");
    input.classList.add("is-invalid");
}

const form = document.querySelector('.form');
form.addEventListener('submit', function(evt) {
    evt.preventDefault();
    let invalidInputs = form.querySelector('.is-invalid');
    if (invalidInputs != null) {
        return;
    }
    let emptyInputs = form.querySelectorAll('.form-control')
    for (let i = 0; i < emptyInputs.length; i++) {
        if (emptyInputs[i].value.length === 0) {
            return;
        }
    }
    this.submit();
});