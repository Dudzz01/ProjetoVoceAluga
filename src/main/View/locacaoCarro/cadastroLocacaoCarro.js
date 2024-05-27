document.addEventListener("DOMContentLoaded", function (ev) {
    ev.preventDefault();

    document.getElementById("cadastroLocacao").addEventListener("submit", function (ev) {
        ev.preventDefault();

        var leftForm = document.getElementById("cadastroLocacao");
        var rightForm = document.getElementById("transacaoLocacao");

        var cnhMotorista = document.getElementById("motorista").value;
        console.log("CNH: " + cnhMotorista)
        if (leftForm.checkValidity() && rightForm.checkValidity()) {
            fetch("http://localhost:8080/motorista/cnh/" + cnhMotorista)
                .then(response => response.json())
                .then(function (motoristaObject) {
                    var cpfCliente = document.getElementById("cliente").value;
                    console.log("CNH: " + cnhMotorista);
                    console.log("motoristaObject.cliente.id: " + motoristaObject.cliente.id);
                    console.log("motoristaObject.id: " + motoristaObject.id);
                    console.log("motoristaObject.cliente.cpf: " + motoristaObject.cliente.cpf);
                    console.log("cpfCliente: " + cpfCliente)
                    if (motoristaObject.cliente.id != null && motoristaObject.id != null && motoristaObject.cliente.cpf == cpfCliente) {
                        var codVeiculo = document.getElementById("veiculo").value;
                        fetch("http://localhost:8080/veiculo/" + codVeiculo)
                            .then(response => response.json())
                            .then(function (veiculoObject) {
                                if (veiculoObject.id != null) {
                                    fetch("http://localhost:8080/funcionario/1")
                                        .then(response => response.json())
                                        .then(function (funcionarioObject) {
                                            if (funcionarioObject.id != null) {
                                                var dataFimJson = moment(document.getElementById("dataFim").value).format("YYYY-MM-DD HH:mm:ss.SSS");
                                                var dataLocacaoJson = moment(document.getElementById("dataLocacao").value).format("YYYY-MM-DD HH:mm:ss.SSS");

                                                var dataJsonLocacao = {
                                                    id: 0,
                                                    motorista: motoristaObject,
                                                    funcionario: funcionarioObject,
                                                    cliente: motoristaObject.cliente,
                                                    veiculo: veiculoObject,
                                                    codLocacao: 0,
                                                    dataLocacao: dataLocacaoJson,
                                                    categoriaVeiculo: veiculoObject.categoria,
                                                    cnhMotorista: motoristaObject.cnh,
                                                    dataPedido: dataLocacaoJson,
                                                    dataInicio: dataLocacaoJson,
                                                    dataFim: dataFimJson,
                                                    enderecoRetirada: document.getElementById("enderecoRetirada").value,
                                                    enderecoDevolucao: document.getElementById("enderecoDevolucao").value,
                                                    statusLocacao: "A",
                                                    contratoLocacao: true
                                                };

                                                fetch("http://localhost:8080/locacao", {
                                                    method: "POST",
                                                    headers: {'Content-Type': 'application/json'},
                                                    body: JSON.stringify(dataJsonLocacao)
                                                })
                                                    .then(response => {
                                                        if (!response.ok) {
                                                            throw new Error('Erro ao salvar Locacao');
                                                        }
                                                        return response.json();
                                                    })
                                                    .then(function (locacaoObjectJson) {
                                                        var dataPagamentoJson = moment(document.getElementById("dataPagamento").value).format("YYYY-MM-DD HH:mm:ss.SSS");

                                                        var dataJsonTransacao = {
                                                            id: 0,
                                                            locacao: locacaoObjectJson,
                                                            cliente: locacaoObjectJson.cliente,
                                                            valorTotal: document.getElementById("valorTotal").value,
                                                            dataPagamento: dataPagamentoJson,
                                                            formaPagamento: document.getElementById("formaDePagamento").value,
                                                            numNotaFiscal: document.getElementById("numNotaFiscal").value,
                                                            status: "concluída",
                                                            codTransacao: 0
                                                        };

                                                        fetch("http://localhost:8080/transacao", {
                                                            method: "POST",
                                                            headers: {'Content-Type': 'application/json'},
                                                            body: JSON.stringify(dataJsonTransacao)
                                                        }).then(function (response) {
                                                            if (response.ok) {
                                                                var elementHtml = document.getElementById("texto-resultado-locacao");
                                                                elementHtml.style.display = "block";
                                                                elementHtml.innerText = "Locacao cadastrada com sucesso";
                                                            } else {
                                                                throw new Error('Erro ao salvar Transacao');
                                                            }
                                                        });
                                                    })
                                                    .catch(function (error) {
                                                        var elementHtml = document.getElementById("texto-resultado-locacao");
                                                        elementHtml.style.display = "block";
                                                        elementHtml.innerText = "Erro ao salvar Locacao: " + error.message;
                                                    });
                                            } else {
                                                throw new Error("Funcionário em conflito");
                                            }
                                        });
                                } else {
                                    throw new Error("Veículo não encontrado");
                                }
                            });
                    } else {
                        throw new Error("Motorista e/ou Cliente inexistente(s)");
                    }
                })
                .catch(function (error) {
                    var elementHtml = document.getElementById("texto-resultado-locacao");
                    elementHtml.style.display = "block";
                    elementHtml.innerText = error.message;
                });
        } else {
            var elementHtml = document.getElementById("texto-resultado-locacao");
            elementHtml.style.display = "block";
            elementHtml.innerText = "Preencha o formulário de transação";
        }
    });
});
