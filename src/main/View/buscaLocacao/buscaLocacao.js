document.addEventListener("DOMContentLoaded", function (ev)


{
    ev.preventDefault();
    document.getElementById("buscaLocacao").addEventListener("submit", function (evv)

        {
            evv.preventDefault();

            var codLocacao = document.getElementById("id").value;

            fetch("http://localhost:8080/locacao/"+codLocacao).then(response => response.json()).then(function (locacaoObject)

                {

                    document.getElementById("codigo").value = locacaoObject.id;
                    document.getElementById("motorista").value = locacaoObject.motorista;
                    document.getElementById("funcionario").value = locacaoObject.funcionario;
                    document.getElementById("cliente").value = locacaoObject.cliente;
                    document.getElementById("veiculo").value = locacaoObject.veiculo;
                    document.getElementById("data locacao").value = locacaoObject.dataLocacao;
                    document.getElementById("data inicio").value = locacaoObject.dataInicio;
                    document.getElementById("data fim").value = locacaoObject.dataFim;
                    document.getElementById("endereco retirada").value = locacaoObject.enderecoRetirada;
                    document.getElementById("endereco devolucao").value = locacaoObject.enderecoDevolucao;
                    document.getElementById("Status Locacao").value = locacaoObject.statusLocacao;
                }

            ).catch(function (error)

                {
                    
                }

            )



        }

    )

}

)