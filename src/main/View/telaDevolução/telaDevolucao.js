
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
        let elementHtml = document.getElementById("textoDevolucao")
        console.log("http://localhost:8080/locacao/"+codLocacao);



        fetch("http://localhost:8080/locacao/"+codLocacao).then(response => response.json()).then(function (locacaoObject)

            {
                console.log("VALOR DO ID DA LOCACAO OBJECT: " | locacaoObject.id );
                if(locacaoObject.id != null && locacaoObject.cliente.id != null)
                {
                    console.log("locacao é diferente de nulo, valor: " | locacaoObject.id);
                    fetch("http://localhost:8080/cliente/cpf/"+cpfCliente).then(response => response.json()).then(function (clienteObject)
                    {
                        if(clienteObject.id != null && clienteObject.id === locacaoObject.cliente.id)
                        {
                            fetch("http://localhost:8080/veiculo/"+codVeiculo).then(response => response.json()).then(function (veiculoObject)

                                {
                                    if(veiculoObject.id != null && veiculoObject.filial.id !== null && locacaoObject.veiculo.id === veiculoObject.id)
                                    {
                                        fetch("http://localhost:8080/transacao/"+codTransacao).then(response => response.json()).then(function (transacaoObject)

                                            {






                                                if(transacaoObject.cliente.id === locacaoObject.cliente.id && transacaoObject.locacao.id === locacaoObject.id)
                                                {

                                                    var pedidoData = moment(locacaoObject.dataPedido).format("YYYY-MM-DD HH:mm:ss.SSS");
                                                    var fimData = moment(locacaoObject.dataFim).format("YYYY-MM-DD HH:mm:ss.SSS");
                                                    var devolucaoData = moment(document.getElementById("dataFim").value).format("YYYY-MM-DD HH:mm:ss.SSS");
                                                    console.log("valor data devolucao document id" + document.getElementById("dataFim").value);
                                                    var dataJsonDevolucao = {

                                                        id: 0,
                                                        cliente: clienteObject,
                                                        veiculo: veiculoObject,
                                                        locacao: locacaoObject,
                                                        transacao: transacaoObject,
                                                        dataPedido: pedidoData,
                                                        dataFimEsperado: fimData,
                                                        dataDevolucao:  devolucaoData,
                                                        valorMulta: document.getElementById("valorMulta").value



                                                    }
                                                    console.log("clienteObject: " +  dataJsonDevolucao.cliente);
                                                    console.log("veiculoObject: " +  dataJsonDevolucao.veiculo);
                                                    console.log("locacaoObject: " +  dataJsonDevolucao.locacao);
                                                    console.log("transacaoObject: " +  dataJsonDevolucao.transacao);


                                                    console.log("DATA PEDIDO: " + dataJsonDevolucao.dataPedido);
                                                    console.log("DATA FIM: " + dataJsonDevolucao.dataFimEsperado);
                                                    console.log("DATA DEVOLUCAO: " + dataJsonDevolucao.dataDevolucao);
                                                    console.log("valorMulta: " + dataJsonDevolucao.valorMulta);

                                                    console.log("body: " + dataJsonDevolucao);
                                                    fetch("http://localhost:8080/devolucao",
                                                        {
                                                            method: "POST",
                                                            headers: {'Content-Type': 'application/json'},
                                                            body: JSON.stringify(dataJsonDevolucao)
                                                        }
                                                    ).then(response => {
                                                        if(response.ok)
                                                        {
                                                            /*var elementHtml = document.getElementById("textoDevolucao")*/
                                                            elementHtml.style.display = "block";
                                                            elementHtml.style.color = "rgb(13,188,57)";
                                                            elementHtml.innerText = "Devolução cadastrada.";
                                                            console.log("FINALIZADO COM SUCESSO");
                                                        }

                                                    })


                                                }
                                                else
                                                {
                                                    elementHtml.style.display = "block";
                                                    elementHtml.style.color = "rgb(219, 0, 0)"
                                                    elementHtml.innerText = "Falha na transação";
                                                    /*throw new Error("Falha na transação");*/
                                                }
                                            }

                                        )
                                    }
                                    else
                                    {
                                        elementHtml.style.display = "block";
                                        elementHtml.style.color = "rgb(219, 0, 0)"
                                        elementHtml.innerText = "Veiculo Inválido";
                                        /*throw new Error("Veiculo Inválido");*/
                                    }
                                }

                            )
                        }
                        else
                        {
                            elementHtml.style.display = "block";
                            elementHtml.style.color = "rgb(219, 0, 0)"
                            elementHtml.innerText = "Cliente inválido";
                        }


                    })


                }

                else
                {
                    elementHtml.style.display = "block";
                    elementHtml.style.color = "rgb(219, 0, 0)"
                    elementHtml.innerText = "Locação Inexistente";
                    /*throw new Error("Locação Inexistente");*/
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