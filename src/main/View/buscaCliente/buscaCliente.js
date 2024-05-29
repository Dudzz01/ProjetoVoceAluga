document.addEventListener("DOMContentLoaded", function (ev)

{

    document.getElementById("busca-cliente-form").addEventListener("submit", function (ev)
    {
        ev.preventDefault();



        var cpf = document.getElementById("cpf").value;
        let elementHTML = document.getElementById("resultadoBusca");


        fetch("http://localhost:8080/cliente/cpf/"+cpf).then(response => response.json()).then
        (
            function (clienteObject)
            {
                console.log(clienteObject);
                document.getElementById("filial_name").innerText = clienteObject.filial.nome;
                document.getElementById("cliente_name").innerText = clienteObject.nome;
                document.getElementById("cliente_cpf").innerText = clienteObject.cpf;
                document.getElementById("cliente_telefone").innerText = clienteObject.telefone;
                document.getElementById("cliente_dtCadastro").innerText = clienteObject.dataCadastro;
                elementHTML.style.display = "block";
                elementHTML.style.color = "rgb(13,188,57)";
                elementHTML.innerText = "Cliente encontrado!"
            }

        ).catch(function (error)
        {
            document.getElementById("filial_name").innerText = "";
            document.getElementById("cliente_name").innerText = "";
            document.getElementById("cliente_cpf").innerText = "";
            document.getElementById("cliente_telefone").innerText = "";
            document.getElementById("cliente_dtCadastro").innerText = "";
            elementHTML.style.display = "block";
            elementHTML.style.color = "rgb(219, 0, 0)";
            elementHTML.innerText = "Cliente não existente."
        })



    }
    )

}

)