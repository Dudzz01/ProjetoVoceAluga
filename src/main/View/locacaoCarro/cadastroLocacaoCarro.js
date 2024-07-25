function mascaraCPF(campo) {
    console.log("Valor CPF: " + campo.value);
    let v = campo.value.replace(/\D/g, ""); // Remove todos os caracteres não numéricos
    v = v.replace(/^(\d{3})(\d)/, "$1.$2"); // Adiciona um ponto após os três primeiros dígitos
    v = v.replace(/(\d{3})(\d)/, "$1.$2"); // Adiciona um ponto após os três dígitos seguintes
    v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2"); // Adiciona um hífen antes dos últimos dois dígitos
    console.log("Valor CPF formatado na mascara: " + campo.value);
    return v; // Retorna o valor formatado
}

function validarCPF(cpf) {
    console.log("Valor CPF Formatado na validacao: " + cpf)
    // Verifica se o valor possui 17 caracteres
    if (cpf.length !== 14) {
        return false;
    }

    // Expressão regular para verificar o formato num1num2num3.num4num5num6.num7num8num9num10num11num12-num13num14
    const regexCPF = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;

    // Verifica se o valor corresponde ao padrão da expressão regular
    return regexCPF.test(cpf);
}

function validarCNH(cnh)
{

    // Verifica se o valor possui 17 caracteres
    if (cnh.length !== 11) {
        return false;
    }

    // Expressão regular para verificar o formato num1num2num3.num4num5num6.num7num8num9num10num11num12-num13num14
    const regexCnh = /^\d{11}$/;

    // Verifica se o valor corresponde ao padrão da expressão regular
    return regexCnh.test(cnh);
}


document.addEventListener("DOMContentLoaded", function (ev) {
    ev.preventDefault();

    document.getElementById("cadastroLocacao").addEventListener("submit", function (ev) {
        ev.preventDefault();

        var leftForm = document.getElementById("cadastroLocacao");
        var rightForm = document.getElementById("transacaoLocacao");
        var cpfCliente = document.getElementById("cliente").value;
        var cnhMotorista = document.getElementById("motorista").value;
        var cpfFormatado = mascaraCPF(document.getElementById("cliente"));
        let checkCpf = validarCPF(cpfFormatado);
        let checkCnh = validarCNH(cnhMotorista);

        let elementHtml = document.getElementById("texto-resultado-locacao");
        console.log("CNH: " + cnhMotorista)
        if (leftForm.checkValidity() && rightForm.checkValidity()) {
            if(checkCpf && checkCnh) {

                fetch("http://localhost:8080/motorista/cnh/" + cnhMotorista)
                    .then(response => response.json())
                    .then(function (motoristaObject) {

                        console.log("CNH: " + cnhMotorista);
                        console.log("motoristaObject.cliente.id: " + motoristaObject.cliente.id);
                        console.log("motoristaObject.id: " + motoristaObject.id);
                        console.log("motoristaObject.cliente.cpf: " + motoristaObject.cliente.cpf);
                        console.log("cpfCliente: " + cpfFormatado)
                        if (motoristaObject.cliente.id != null && motoristaObject.id != null) {
                            if (motoristaObject.cliente.cpf === cpfFormatado) {
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
                                                                    if (response.status === 422) {
                                                                        throw new Error('Datas em conflitos com locações existentes do cliente e motorista');
                                                                    }
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
                                                                        /* var elementHtml = document.getElementById("texto-resultado-locacao");*/
                                                                        elementHtml.style.display = "block";
                                                                        elementHtml.style.color = "rgb(13,188,57)";
                                                                        elementHtml.innerText = "Locacao cadastrada com sucesso";
                                                                    } else {
                                                                        elementHtml.style.display = "block";
                                                                        elementHtml.style.color = "rgb(219, 0, 0)";
                                                                        elementHtml.innerText = "Erro ao salvar Transacao";

                                                                    }
                                                                });
                                                            })
                                                            .catch(function (error) {
                                                                /* var elementHtml = document.getElementById("texto-resultado-locacao");*/
                                                                elementHtml.style.display = "block";
                                                                elementHtml.style.color = "rgb(219, 0, 0)";
                                                                elementHtml.innerText = error.message;
                                                            });
                                                    } else {
                                                        elementHtml.style.display = "block";
                                                        elementHtml.style.color = "rgb(219, 0, 0)"
                                                        elementHtml.innerText = "Funcionário em conflito";

                                                    }
                                                });
                                        } else {
                                            elementHtml.style.display = "block";
                                            elementHtml.style.color = "rgb(219, 0, 0)"
                                            elementHtml.innerText = "Veículo não encontrado";

                                        }

                                    });
                            } else {
                                elementHtml.style.display = "block";
                                elementHtml.style.color = "rgb(219, 0, 0)"
                                elementHtml.innerText = "Motorista não está cadastrado com esse cliente no sistema. Retorne a tela de cadastro do motorista e cadastre-o com o cliente desejado";
                            }
                        } else {
                            elementHtml.style.display = "block";
                            elementHtml.style.color = "rgb(219, 0, 0)"
                            elementHtml.innerText = "Motorista e/ou Cliente inexistente(s)";

                        }
                    })
                    .catch(function (error) {
                        /* var elementHtml = document.getElementById("texto-resultado-locacao");*/
                        elementHtml.style.display = "block";
                        elementHtml.style.color = "rgb(219, 0, 0)"
                        elementHtml.innerText = "CNH INVÁLIDA";
                    });

            }
        } else {
           /* var elementHtml = document.getElementById("texto-resultado-locacao"); */
            elementHtml.style.display = "block";
            elementHtml.style.color = "rgb(219, 0, 0)"
            elementHtml.innerText = "Preencha o formulário de transação";
        }
    });
});
