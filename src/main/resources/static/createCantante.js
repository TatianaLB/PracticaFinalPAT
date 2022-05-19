function CreateCantante(){
    try {
        var nombre = document.getElementById("nombre").value;
        var album = document.getElementById("album").value;
        var usuario = localStorage.getItem("accessToken");
        var data = {
                    "id": null,
                    "nombre":nombre,
                    "user":usuario,
                    "album":album
                    };
        const address = 'api/v1/cantantes/create'+"?user="+usuario;
        fetch(address, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.result == "OK") {
                    alert("Artista Añadido");
                    //document.location.href="/api/v1/playlists";
                    window.location.href = "home.html";
                }else {
                    alert(data.result);
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}