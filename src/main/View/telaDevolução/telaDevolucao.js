document.addEventListener("DOMContentLoaded", function (ev)

{
    ev.preventDefault();

    document.getElementById("criarDevolucao").addEventListener("submit", function (evt)
    {
        evt.preventDefault();

        var codLocacao = document.getElementById("locacao").value;
        var codVeiculo = document.getElementById("veiculo").value;
        var cpfCliente = document.getElementById("cliente").value;
        var codTransacao = document.getElementById("transacao").value;




        fetch("http://localhost:8080/locacao/"+codLocacao).then(function (locacaoObject)

            {
                if(locacaoObject.id != null || locacaoObject.cliente.id != null)
                {

                    fetch("http://localhost:8080/cliente/cpf/"+cpfCliente).then(function (clienteObject)
                    {
                        if(clienteObject.id != null || clienteObject.id !== locacaoObject.cliente.id)
                        {
                            fetch("http://localhost:8080/veiculo/"+codVeiculo).then(function (veiculoObject)

                                {
                                    if(veiculoObject.id != null || locacaoObject.veiculo.id !== veiculoObject.id|| veiculoObject.filial.id !== null)
                                    {
                                        fetch("http://localhost:8080/veiculo/"+codTransacao).then(function (transacaoObject)

                                            {
                                                if(transacaoObject.cliente.id !== locacaoObject.cliente.id || transacaoObject.locacao.id !== locacaoObject.id)
                                                {

                                                    var dataJsonDevolucao = {

                                                        id: 0,
                                                        cliente: clienteObject,
                                                        veiculo: veiculoObject,
                                                        locacao: locacaoObject,
                                                        transacao: transacaoObject,
                                                        dataPedido: locacaoObject.dataPedido,
                                                        dataFimEsperado: locacaoObject.dataFim,
                                                        dataDevolucao:  document.getElementById("dataFim").value,
                                                        valorMulta: document.getElementById("valorMulta").value



                                                    }

                                                    fetch("http://localhost:8080/devolucao",
                                                        {
                                                            method: "POST",
                                                            headers: {'Content-Type': 'application/json'},
                                                            body: JSON.stringify(dataJsonDevolucao)
                                                        }
                                                    ).then(response => {
                                                        if(response.ok)
                                                        {
                                                            var elementHtml = document.getElementById("textoDevolucao")
                                                            elementHtml.style.display = "block";
                                                            elementHtml.innerText = "Devolução cadastrada.";
                                                        }

                                                    })


                                                }
                                                else
                                                {
                                                    throw new Error("Falha na transação");
                                                }
                                            }

                                        )
                                    }
                                    else
                                    {
                                        throw new Error("Veiculo Inválido");
                                    }
                                }

                            )
                        }


                    })


                }

                else
                {
                    throw new Error("Locação Inexistente");
                }
            }

        ).catch(function (reason)
            {
                console.log(reason)
            }
        )




    }


    )
}


)