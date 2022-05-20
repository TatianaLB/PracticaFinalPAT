function CreateCancion(){

    try {
        var usuario = localStorage.getItem("accessToken");
        var nombre = document.getElementById("nombre").value;
        var playlist = localStorage.getItem("playlist");
        var artista = document.getElementById("artista").value;
        var album = document.getElementById("album").value;
        var longitud = document.getElementById("longitud").value;

        //PRIMERO VALIDAMOS LOS DATOS
        if (nombre == "") {
            alert("Por favor, introduzca el nombre de la canción");
            nombre.focus();
            return false;
        }

        var artistaRegex = new RegExp("^[a-zA-Z0-9]+$");
        if (artista == "") {
            alert("Por favor, introduzca el nombre del artista");
            artista.focus();
            return false;
        }
        if (!artistaRegex.test(artista))
        {
            alert("El nombre del artista introducido: " + artista + " es inválido, no puede contener caracteres especiales");
            artista.focus();
            return false;
        }

        var albumRegex = new RegExp("^[a-zA-Z0-9]+$");
        if (album == "") {
            alert("Por favor, introduzca el álbum al que pertenece la canción");
            album.focus();
            return false;
        }
        if (!albumRegex.test(album))
        {
            alert("El nombre del álbum introducido: " + album + " es inválido, no puede contener caracteres especiales");
            album.focus();
            return false;
        }

        var longitudRegex = /^[0-9]+$/;
        if (longitud == "") {
            alert("Por favor, introduzca la longitud de la canción");
            longitud.focus();
            return false;
        }
        if(!longitudRegex.test(longitud)){
            alert("La longitud introducida: " + longitud + " es errónea, debe ser un número");
            longitud.focus();
            return false;
        }

        var data = {
                    "id": null,
                    "nombre":nombre,
                    "playlist":playlist,
                    "user":usuario,
                    "artista":artista,
                    "album":album,
                    "longitud":longitud
                    };
        const address = 'api/v1/cancion/create';
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
                    document.location.href="/api/v1/canciones";
                    window.location.href="ListOfCanciones.html"
                } else {
                    alert("Error.");
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}