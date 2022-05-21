function CreateCantante(){
    try {
        var nombre = document.getElementById("nombre").value;
        var album = document.getElementById("album").value;
        var user = localStorage.getItem("accessToken");

        //PRIMERO VALIDAMOS LOS DATOS
        var nombreRegex = new RegExp("^[a-zA-Z0-9 ]+$");
        if (nombre == "") {
            alert("Por favor, introduzca el nombre del artista que desea añadir a favoritos");
            nombre.focus();
            return false;
        }
        if (!nombreRegex.test(nombre))
        {
            alert("El nombre del artista introducido: " + nombre + " es inválido, no puede contener caracteres especiales");
            nombre.focus();
            return false;
        }

        var albumRegex = new RegExp("^[a-zA-Z0-9 ]+$");
        if (album == "") {
            alert("Por favor, introduzca el álbum favorito del artista");
            album.focus();
            return false;
        }
        if (!albumRegex.test(album))
        {
            alert("El nombre del álbum introducido: " + album + " es inválido, no puede contener caracteres especiales");
            album.focus();
            return false;
        }

        var data = {
                    "id": null,
                    "nombre":nombre,
                    "album":album,
                    "user":user
                    };
        const address = 'api/v1/cantantes/create';
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
                    //document.location.href="/api/v1/playlists";
                    window.location.href = "ListOfCantantesFavoritos.html";
                }else {
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}