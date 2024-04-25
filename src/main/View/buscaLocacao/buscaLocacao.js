document.addEventListener("DOMContentLoaded", function (ev)


{
    ev.preventDefault();
    document.getElementById("buscaLocacao").addEventListener("submit", function (evv)

        {
            evv.preventDefault();

            var codLocacao = document.getElementById("id").value;

            fetch("http://localhost:8080/locacao/"+codLocacao).then(response => response.json()).then(function (locacaoObject)

                {

                    document.getElementById("codigo").innerText = locacaoObject.id;
                   document.getElementById("motorista").innerText = locacaoObject.motorista.nome;
                    document.getElementById("funcionario").innerText = locacaoObject.funcionario.nome;
                    document.getElementById("cliente").innerText = locacaoObject.cliente.nome;
                    document.getElementById("veiculo").innerText = locacaoObject.veiculo;
                    document.getElementById("data locacao").innerText = locacaoObject.dataLocacao;
                    document.getElementById("data inicio").innerText = locacaoObject.dataInicio;
                    document.getElementById("data fim").innerText = locacaoObject.dataFim;
                    document.getElementById("endereco retirada").innerText = locacaoObject.enderecoRetirada;
                    document.getElementById("endereco devolucao").innerText = locacaoObject.enderecoDevolucao;
                    document.getElementById("Status Locacao").innerText = locacaoObject.statusLocacao;
                }

            ).catch(function (error)

                {
                    console.log(error)
                }

            )



        }

    )

}

)