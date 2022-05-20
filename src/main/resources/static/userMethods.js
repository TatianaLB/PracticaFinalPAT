async function signUp(){
    try {
        var username = await document.getElementById("usernameUsuario").value;
        var nombre = await document.getElementById("nombreUsuario").value;
        var apellido = await document.getElementById("apellidoUsuario").value;
        var email = await document.getElementById("emailUsuario").value;
        var edad = await document.getElementById("edadUsuario").value;
        var password = await document.getElementById("passwordUsuario").value;

        //PRIMERO VALIDAMOS LOS DATOS.
        if (username == "" && nombre == "" && apellido == "" && email == "" && edad == "" && password == "") {
            alert("Por favor, rellena los campos del formulario para darte de alta en la aplicación");
            return false;
        }

        var usernameRegex = new RegExp("^[a-zA-Z0-9]+$");

        if (username == "") {
            alert("Por favor, rellena el nombre de usuario");
            usernameUsuario.focus();
            return false;
        }
        if(!usernameRegex.test(username))
        {
            alert("El nombre de usuario de usuario no acepta caracteres especiales, introduzca uno válido");
            usernameUsuario.focus();
            return false;
        }

        var nombreApellidoRegex = new RegExp("^[a-zA-Z]+$");

        if (nombre == "") {
            alert("Por favor, introduzca su nombre");
            nombreUsuario.focus();
            return false;
        }
        if (!nombreApellidoRegex.test(nombre))
        {
            alert("El nombre introducido: " + nombre + " es inválido, sólo debe contener letras");
            nombreUsuario.focus();
            return false;
        }

        if (apellido == ""){
            alert("Por favor, introduzca su apellido");
            apellidoUsuario.focus();
            return false;
        }
        if (!nombreApellidoRegex.test(apellido))
        {
            alert("El apellido introducido: " + apellido + " es inválido, sólo debe contener letras");
            apellidoUsuario.focus();
            return false;
        }

        var emailRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

        if (email == "")
        {
            alert("Por favor, introduzca un email");
            emailUsuario.focus();
            return false;
        }
        if (!emailRegex.test(email))
        {
             alert("Por favor, introduzca un email válido");
             emailUsuario.focus();
             return false;
        }

        var edadRegex = /^[0-9]+$/;
        if (edad == "") {
            alert("Por favor, introduzca su edad");
            edadUsuario.focus();
            return false;
        }
        if(!edadRegex.test(edad)){
            alert("Por favor, la edad debe ser un número");
            edadUsuario.focus();
            return false;
        }
        if(edad<16){
             alert("Su edad debe ser superior a 16 años para el uso de esta app");
             edadUsuario.focus();
             return false;
        }

        if (password == "") {
            alert("Por favor, establezca una contraseña");
            passwordUsuario.focus();
            return false;
        }

        var data = {"username": username,
                    "nombre":nombre,
                    "apellido":apellido,
                    "email":email,
                    "edad":edad,
                    "password":password
                    };
        console.log(data);
        const address = 'api/v1/signup';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    window.location.href="login.html";
                } else {
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;

}

async function LogIn(){
    try {
        var usuario = await document.getElementById("usuarioLogin").value;
        var password = await document.getElementById("passwordLogin").value;



        var data = {"username": usuario,
                    "password":password
                    };
        const address = 'api/v1/login';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    console.log(data.accessToken);
                    localStorage.setItem("user", usuario);
                    localStorage.setItem("accessToken", data.accessToken);
                    document.location.href="home.html";
                } else {
                    alert(data.result);
                    if(data.result == "User doesn't exist. Please Sign Up."){
                        document.location.href="signUp.html";
                    }else{
                        document.location.href = "logIn.html";
                    }
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;

}

