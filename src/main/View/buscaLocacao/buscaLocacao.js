document.addEventListener("DOMContentLoaded", function (ev)


{
    ev.preventDefault();
    document.getElementById("buscaLocacao").addEventListener("submit", function (evv)

        {
            evv.preventDefault();

            var codLocacao = document.getElementById("id").value;
            let elementHTML = document.getElementById("resultadoBusca");

            fetch("http://localhost:8080/locacao/"+codLocacao).then(response => response.json()).then(function (locacaoObject)

                {

                    fetch("http://localhost:8080/transacao/locacao/"+codLocacao).then(response => response.json()).then(function (transacaoObject)
                    {


                    document.getElementById("codigo").innerText = locacaoObject.id;
                   document.getElementById("motorista").innerText = locacaoObject.motorista.nome;
                    document.getElementById("funcionario").innerText = locacaoObject.funcionario.nome;
                    document.getElementById("cliente").innerText = locacaoObject.cliente.nome;
                    document.getElementById("veiculo").innerText = locacaoObject.veiculo.nome;
                    document.getElementById("data locacao").innerText = locacaoObject.dataLocacao;
                    document.getElementById("data inicio").innerText = locacaoObject.dataInicio;
                    document.getElementById("data fim").innerText = locacaoObject.dataFim;
                    document.getElementById("endereco retirada").innerText = locacaoObject.enderecoRetirada;
                    document.getElementById("endereco devolucao").innerText = locacaoObject.enderecoDevolucao;
                    document.getElementById("Status Locacao").innerText = locacaoObject.statusLocacao;
                    document.getElementById("Código Transação").innerText = transacaoObject.id;
                    elementHTML.style.display = "block";
                    elementHTML.style.color = "rgb(13,188,57)";
                    elementHTML.innerText = "Locação encontrada!"

                    }).catch(function (error)

                    {
                        console.log("NAO ACHO LOCACAO")
                        document.getElementById("codigo").innerText = "";
                        document.getElementById("motorista").innerText = "";
                        document.getElementById("funcionario").innerText = "";
                        document.getElementById("cliente").innerText = "";
                        document.getElementById("veiculo").innerText = "";
                        document.getElementById("data locacao").innerText = "";
                        document.getElementById("data inicio").innerText = "";
                        document.getElementById("data fim").innerText = "";
                        document.getElementById("endereco retirada").innerText = "";
                        document.getElementById("endereco devolucao").innerText = "";
                        document.getElementById("Status Locacao").innerText = "";
                        elementHTML.style.display = "block";
                        elementHTML.style.color = "rgb(219, 0, 0)";
                        elementHTML.innerText = "Locação inexistente."
                    }

                )

                }

            ).catch(function (error)

                {
                    console.log("NAO ACHO LOCACAO")
                    document.getElementById("codigo").innerText = "";
                    document.getElementById("motorista").innerText = "";
                    document.getElementById("funcionario").innerText = "";
                    document.getElementById("cliente").innerText = "";
                    document.getElementById("veiculo").innerText = "";
                    document.getElementById("data locacao").innerText = "";
                    document.getElementById("data inicio").innerText = "";
                    document.getElementById("data fim").innerText = "";
                    document.getElementById("endereco retirada").innerText = "";
                    document.getElementById("endereco devolucao").innerText = "";
                    document.getElementById("Status Locacao").innerText = "";
                    elementHTML.style.display = "block";
                    elementHTML.style.color = "rgb(219, 0, 0)";
                    elementHTML.innerText = "Locação inexistente."
                }

            )



        }

    )

}

)