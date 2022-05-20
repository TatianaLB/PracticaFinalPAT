window.onload = async function(){

    let accessToken = localStorage.getItem("accessToken");
    let url = 'api/v1/cantantes/'+accessToken;

    fetch(url).then((response) => {
            response.json().then((data) => {

                const list = document.querySelector("#list");
                if(data.length == 0){
                    const text = document.createElement("h3");
                    text.textContent = "Aún no tienes ningún artista favorito";
                    list.appendChild(text);
                }else{
                    for(i=0;i<data.length;i++){
                        const button = document.createElement("button");
                        let nombre = data[i].nombre;
                        let album = data[i].album;
                        button.textContent = "Artista: " + nombre + ". Álbum favorito: " + album;
                        button.setAttribute("class", "list-group-item");
                        button.addEventListener("click", function(){
                            console.log("HELLO WORLD!");

                            });
                        list.appendChild(button);
                    }
                }

            });
       });



}


