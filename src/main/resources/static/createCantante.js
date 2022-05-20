function CreateCantante(){
    try {
        var nombre = document.getElementById("nombre").value;
        var album = document.getElementById("album").value;
        var user = localStorage.getItem("accessToken");
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