function validarCNH(cnh)
{

    // Verifica se o valor possui 17 caracteres
    if (cnh.length !== 11) {
        return false;
    }

    // Expressão regular para verificar o formato num1num2num3.num4num5num6.num7num8num9num10num11num12-num13num14
    const regexCnh = /^\d{11}$/;

    // Verifica se o valor corresponde ao padrão da expressão regular
    return regexCnh.test(cnh);
}


document.addEventListener("DOMContentLoaded",function (ev)
{
    ev.preventDefault();
    document.getElementById("buscaMotorista").addEventListener("submit",function (ev) {
        ev.preventDefault();

        var cnhMotorista = document.getElementById("cnh_mot").value;
        var checkCnh = validarCNH(cnhMotorista);
        let elementHTML = document.getElementById("resultadoBusca");
        if(checkCnh)
        {
            fetch("http://localhost:8080/motorista/cnh/" + cnhMotorista).then(response => response.json()).then(function (motoristaObject) {

                document.getElementById("nome_motorista").innerText = motoristaObject.nome;
                document.getElementById("cpf_motorista").innerText = motoristaObject.cpf;
                document.getElementById("cnh_motorista").innerText = motoristaObject.cnh;
                document.getElementById("data_nascimento").innerText = motoristaObject.dataNascimento;
                elementHTML.style.display = "block";
                elementHTML.style.color = "rgb(13,188,57)";
                elementHTML.innerText = "Motorista encontrada!";
            }
            ).catch(function (error) {

                document.getElementById("nome_motorista").innerText = "";
                document.getElementById("cpf_motorista").innerText = "";
                document.getElementById("cnh_motorista").innerText = "";
                document.getElementById("data_nascimento").innerText = "";

                elementHTML.style.display = "block";
                elementHTML.style.color = "rgb(219, 0, 0)";
                elementHTML.innerText = "Motorista inexistente."
            }
            )

        }
        else
        {
            document.getElementById("nome_motorista").innerText = "";
            document.getElementById("cpf_motorista").innerText = "";
            document.getElementById("cnh_motorista").innerText = "";
            document.getElementById("data_nascimento").innerText = "";

            elementHTML.style.display = "block";
            elementHTML.style.color = "rgb(219, 0, 0)";
            elementHTML.innerText = "Cnh Inválida."
        }

    })
})