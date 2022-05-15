window.onload = async function(){

    //let playlist = localStorage.getItem("playlist");
    //localStorage.removeItem("playlist");
   // console.log(playlist);
    //document.getElementById("titleCanciones").innerHTML = playlist;

    let url = 'api/v1/top50global';

    fetch(url).then((response) => {
            console.log(response);
            response.json().then((data) => {
                // PROCESAR LA INFORMACIÓN OBTENIDA DE LA TOP50GLOBAL, CREAR EL HTML.
                const list = document.querySelector("#list");
                if(data.length == 0)
                {
                    const text = document.createElement("h3");
                    text.textContent = "No hay canciones.";
                    list.appendChild(text);
                }else{
                    for(i=0;i<data.length;i++){
                        const button = document.createElement("button");
                        let nombre = data[i].nombre;
                        let artista = data[i].artista;
                        let album = data[i].album;
                        let longitud = data[i].longitud;

                        button.textContent = (i+1) + "-  " + nombre + ". Artista: " + artista + ". Álbum: " + album + ". Duración: " + longitud + "min";
                        button.setAttribute("class", "list-group-item");
                        //button.setAttribute("onclick", 'GoToCanciones()')
                        button.addEventListener("click", function(){
                           try {
                                   //var playlist = localStorage.getItem("playlist");
                                   //var playlist = "mis cosas";
                                   var playlist = document.getElementById("playlist").value;
                                   console.log(playlist);

                                   var data = {
                                               "id": null,
                                               "nombre":nombre,
                                               "playlist":playlist,
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
                                               window.location.href = 'ListOfCanciones.html';
                                           } else {
                                               alert("Error.");
                                           }
                                       });

                               } catch (err) {
                                   console.error(err.message);
                               }
                               return false;

                           })

                        //button.setAttribute("onclick", 'CreateCancionTopGlobal("'nombre + artista + album + longitud'")');
                        list.appendChild(button);
                    }
                }
            });
       });



};



