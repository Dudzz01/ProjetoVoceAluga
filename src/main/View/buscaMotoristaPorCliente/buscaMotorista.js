document.addEventListener("DOMContentLoaded",function (ev)
{
    ev.preventDefault();
    document.getElementById("buscaMotorista").addEventListener("submit",function (ev)
    {
        ev.preventDefault();

        var cnhMotorista = document.getElementById("cnh_mot").value;
        let elementHTML = document.getElementById("resultadoBusca");

        fetch("http://localhost:8080/motorista/cnh/"+cnhMotorista).then(response => response.json()).then(function (motoristaObject)

            {

                document.getElementById("nome_motorista").innerText = motoristaObject.nome;
                document.getElementById("cpf_motorista").innerText = motoristaObject.cpf;
                document.getElementById("cnh_motorista").innerText = motoristaObject.cnh;
                document.getElementById("data_nascimento").innerText = motoristaObject.dataNascimento;
                elementHTML.style.display = "block";
                elementHTML.style.color = "rgb(13,188,57)";
                elementHTML.innerText = "Motorista encontrada!";
            }

        ).catch(function (error)

            {

                document.getElementById("nome_motorista").innerText = "";
                document.getElementById("cpf_motorista").innerText = "";
                document.getElementById("cnh_motorista").innerText = "";
                document.getElementById("data_nascimento").innerText = "";

                elementHTML.style.display = "block";
                elementHTML.style.color = "rgb(219, 0, 0)";
                elementHTML.innerText = "Motorista inexistente."
            }

        )

    })
})