function CreateCancion(){

    try {
        var usuario = localStorage.getItem("accessToken");
        var nombre = document.getElementById("nombre").value;
        var playlist = document.getElementById("playlist").value;
        var artista = document.getElementById("artista").value;
        var album = document.getElementById("album").value;
        var longitud = document.getElementById("longitud").value;

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
                    alert("Canción Creada");
                    document.location.href="/api/v1/canciones";
                    window.location.href="ListOfPlaylists.html"
                } else {
                    alert("Error.");
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}