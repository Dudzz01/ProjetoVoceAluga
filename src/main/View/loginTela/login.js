function validarCPF(cpf)
{

    // Verifica se o valor possui 17 caracteres
    if (cpf.length !== 11) {
        return false;
    }

    // Expressão regular para verificar o formato num1num2num3.num4num5num6.num7num8num9num10num11num12-num13num14
    const regexCnh = /^\d{11}$/;

    // Verifica se o valor corresponde ao padrão da expressão regular
    return regexCnh.test(cpf);
}







document.addEventListener("DOMContentLoaded", function() {

document.getElementById("loginForm").addEventListener("submit",

    function (ev)
    {
        ev.preventDefault();
        var cpfFormatado = document.getElementById("cpf").value;
        var checkCpf = validarCPF(cpfFormatado);
        var formData = {
                cpf: cpfFormatado,
            password: document.getElementById("password").value
        }
        if(checkCpf) {

            fetch("http://localhost:8080/funcionario/login",

                {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(formData)
                }
            ).then(response => {
                if (response.status === 401) {
                    response.text().then(errorMessage => {
                        throw new Error(errorMessage)
                    })
                }
                return response.text();
            }).then(data => {
                console.log("FUNCIONOUUU");
                const elementHtmlResponse = document.getElementById("textoLogin");
                elementHtmlResponse.style.display = "block";
                elementHtmlResponse.innerText = data;
                elementHtmlResponse.style.color = "rgb(13,188,57)";
                setTimeout(function() {
                    window.location.href = "../Menu/MenuVoceAluga.html";
                }, 2000);
            }).catch(function () {
                console.log("AVISA O EROOOOO");
                const elementHtmlResponse = document.getElementById("textoLogin");
                elementHtmlResponse.style.display = "block";
                elementHtmlResponse.style.color = "rgb(219, 0, 0)";
                elementHtmlResponse.innerText = "Cpf ou Senha inválido(s)";

            })
        }
        else
        {
            const elementHtmlResponse = document.getElementById("textoLogin");
            elementHtmlResponse.style.display = "block";
            elementHtmlResponse.style.color = "rgb(219, 0, 0)";
            elementHtmlResponse.innerText = "Cpf Inválido";
        }
    }


)

    }


)