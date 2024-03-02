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


             ).then(response => response.text()).then(data => {
                                                                                console.log("FUNCIONOUUU");
                                                                                const elementHtmlResponse = document.getElementById("textoLogin");
                                                                                elementHtmlResponse.style.display = "block";
                                                                                elementHtmlResponse.innerText = data;
                                                                               }).catch(function (error){
                                                                                                        console.log("AVISA O EROOOOO");
                                                                                                        const elementHtmlResponse = document.getElementById("textoLogin");
                                                                                                        elementHtmlResponse.style.display = "block";
                                                                                                        elementHtmlResponse.innerText = error;
                                                                                                        })
    }


)

    }


)