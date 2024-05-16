document.addEventListener("DOMContentLoaded",function (ev)
{
    ev.preventDefault();
    document.getElementById("buscaMotorista").addEventListener("submit",function (ev)
    {
        ev.preventDefault();

        var cpfMotorista = document.getElementById("cpf_mot").value;

        fetch("http://localhost:8080/motorista/cpf/"+cpfMotorista).then(response => response.json()).then(function (motoristaObject)

            {
                document.getElementById("cod_motorista").innerText = motoristaObject.id
                document.getElementById("nome_motorista").innerText = motoristaObject.nome
                document.getElementById("cpf_motorista").innerText = motoristaObject.cpf
                document.getElementById("cnh_motorista").innerText = motoristaObject.cnh
                document.getElementById("data_nascimento").innerText = motoristaObject.dataNascimento
                document.getElementById("cpf_cliente").innerText = motoristaObject.cliente.cpf



            }

        )
    })
})