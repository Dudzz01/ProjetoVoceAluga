document.addEventListener("DOMContentLoaded", function() {

document.getElementById("loginForm").addEventListener("submit",

    function (ev)
    {
        ev.preventDefault();
        var formData = {
                cpf: document.getElementById("cpf").value,
            password: document.getElementById("password").value
        }

        fetch("http://localhost:8080/funcionario/login",

            {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            }


             ).then(response => {if(response.status === 401)
                                          {
                                              response.text().then(errorMessage => {throw new Error(errorMessage)})
                                          }
                                            return response.text();
                                          }).then(data => {
                                                                                console.log("FUNCIONOUUU");
                                                                                const elementHtmlResponse = document.getElementById("textoLogin");
                                                                                elementHtmlResponse.style.display = "block";
                                                                                elementHtmlResponse.innerText = data;
                                                                                elementHtmlResponse.style.color = "rgb(13,188,57)";
                                                                               }).catch(function (){
                                                                                                        console.log("AVISA O EROOOOO");
                                                                                                        const elementHtmlResponse = document.getElementById("textoLogin");
                                                                                                        elementHtmlResponse.style.display = "block";
                                                                                                        elementHtmlResponse.style.color = "rgb(219, 0, 0)";
                                                                                                        elementHtmlResponse.innerText = "Cpf ou Senha inválido(s)";
         
                                                                                                         })
    }


)

    }


)