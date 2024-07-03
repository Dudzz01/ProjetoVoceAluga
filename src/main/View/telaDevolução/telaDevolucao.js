document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

        document.getElementById("criarDevolucao").addEventListener("submit", function (evt)
            {
                evt.preventDefault();


                var codTransacao = document.getElementById("transacao").value;
                let elementHtml = document.getElementById("textoDevolucao")





                fetch("http://localhost:8080/transacao/"+codTransacao).then(response => response.json()).then(function (transacaoObject)

                    {


                        if(transacaoObject.id != null)
                        {

                            var pedidoData = moment(transacaoObject.locacao.dataPedido).format("YYYY-MM-DD HH:mm:ss.SSS");
                            var fimData = moment(transacaoObject.locacao.dataFim).format("YYYY-MM-DD HH:mm:ss.SSS");
                            var devolucaoData = moment(document.getElementById("dataFim").value).format("YYYY-MM-DD HH:mm:ss.SSS");
                            console.log("valor data devolucao document id" + document.getElementById("dataFim").value);
                            var dataJsonDevolucao = {

                                id: 0,
                                cliente: transacaoObject.cliente,
                                veiculo: transacaoObject.locacao.veiculo,
                                locacao: transacaoObject.locacao,
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
                                else
                                {
                                    console.log("entrou no erro");
                                    elementHtml.style.display = "block";
                                    elementHtml.style.color = "rgb(219, 0, 0)"
                                    elementHtml.innerText = "Transação sem devoluções pendentes";
                                }


                            })


                        }
                        else
                        {
                            elementHtml.style.display = "block";
                            elementHtml.style.color = "rgb(219, 0, 0)"
                            elementHtml.innerText = "Transação Inválida";
                            /*throw new Error("Falha na transação");*/
                        }
                    }

                )





            }


        )
    }


)