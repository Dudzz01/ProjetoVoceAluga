document.addEventListener("DOMContentLoaded",function (ev)
{
    ev.preventDefault();
    document.getElementById("devolucaoBusca").addEventListener("submit",function (ev)
    {
        ev.preventDefault();

        var codDevolucao = document.getElementById("buscaDevolucao").value;

        fetch("http://localhost:8080/devolucao/"+codDevolucao).then(response => response.json()).then(function (devolucaoObject)

            {
                if(devolucaoObject.id != null)
                {

                    document.getElementById("cod_devol").innerText = devolucaoObject.id
                    document.getElementById("cpf_cliente").innerText = devolucaoObject.cliente.cpf
                    document.getElementById("cpf_motorista").innerText = devolucaoObject.locacao.motorista.cpf
                    document.getElementById("codigo_veiculo").innerText = devolucaoObject.veiculo.id
                    document.getElementById("nome_veiculo").innerText = devolucaoObject.veiculo.nome
                    document.getElementById("data_pedido").innerText = devolucaoObject.dataPedido
                    document.getElementById("cod_locacao").innerText = devolucaoObject.locacao.id
                    document.getElementById("end_retirada").innerText = devolucaoObject.locacao.enderecoRetirada
                    document.getElementById("end_devolucao").innerText = devolucaoObject.locacao.enderecoDevolucao
                    document.getElementById("cod_transacao").innerText = devolucaoObject.transacao.id
                    document.getElementById("data_fim_esperado").innerText = devolucaoObject.dataFimEsperado
                    document.getElementById("data_devolucao").innerText = devolucaoObject.dataDevolucao
                }
                else
                {
                    document.getElementById("cod_devol").innerText = "Devolução Inexistente"
                    document.getElementById("cpf_cliente").innerText =  "Devolução Inexistente"
                    document.getElementById("cpf_motorista").innerText = "Devolução Inexistente"
                    document.getElementById("codigo_veiculo").innerText = "Devolução Inexistente"
                    document.getElementById("nome_veiculo").innerText = "Devolução Inexistente"
                    document.getElementById("data_pedido").innerText = "Devolução Inexistente"
                    document.getElementById("cod_locacao").innerText = "Devolução Inexistente"
                    document.getElementById("end_retirada").innerText = "Devolução Inexistente"
                    document.getElementById("end_devolucao").innerText = "Devolução Inexistente"
                    document.getElementById("cod_transacao").innerText = "Devolução Inexistente"
                    document.getElementById("data_fim_esperado").innerText = "Devolução Inexistente"
                    document.getElementById("data_devolucao").innerText = "Devolução Inexistente"
                }




            }

        )
    })
})