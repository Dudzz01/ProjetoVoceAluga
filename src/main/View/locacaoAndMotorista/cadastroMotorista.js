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

document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

        document.getElementById("cadast_motorista").addEventListener("submit", function (ev)

            {
                ev.preventDefault();
                var idCliente = document.getElementById("CodigoDoCliente").value;
                var dataNascimentoJson = moment(document.getElementById("dataNascimento").value).format("YYYY-MM-DD HH:mm:ss.SSS");
                let elementHtml = document.getElementById("textCadastro");
                var cpfFormatado = mascaraCPF(document.getElementById("cpf"));
                let checkCpf = validarCPF(cpfFormatado);
                let checkCnh = validarCNH(document.getElementById("cnh").value);
                if(checkCpf && checkCnh) {

                    fetch("http://localhost:8080/cliente/" + idCliente).then(response => response.json()).then(function (clienteObject) {


                            console.log("RESPOSTA 200 CRIAR MOTORISTA");
                            if (clienteObject.id != null) {
                                var dataJsonMotorista = {
                                    id: 0,
                                    cliente: clienteObject,
                                    nome: document.getElementById("nome").value,
                                    cpf: cpfFormatado,
                                    cnh: document.getElementById("cnh").value,
                                    dataNascimento: dataNascimentoJson
                                }

                                fetch("http://localhost:8080/motorista",
                                    {
                                        method: "POST",
                                        headers: {'Content-Type': 'application/json'},
                                        body: JSON.stringify(dataJsonMotorista)
                                    }).then(function (response) {
                                    if (response.ok) {

                                        elementHtml.style.display = "block";
                                        elementHtml.style.color = "rgb(13,188,57)";
                                        elementHtml.innerText = "Cadastro com sucesso";


                                    } else {

                                        elementHtml.style.display = "block";
                                        elementHtml.style.color = "rgb(219, 0, 0)"
                                        elementHtml.innerText = "Erro ao cadastrar";
                                        throw new Error("Informações inválidas sobre o motorista");
                                    }
                                })

                            } else {
                                throw new Error("Código Cliente inválido");
                            }


                            /*.catch(function (error)
                               {

                                   console.log("URL: http://localhost:8080/motorista?cpf="+document.getElementById("cpf").value+"&cnh="+document.getElementById("cnh").value+"&clienteId="+idCliente);
                                   var elementHtml = document.getElementById("textCadastro");
                                   elementHtml.style.display = "block";
                                   elementHtml.style.color = "rgb(219, 0, 0)"
                                   elementHtml.innerText = "Motorista já está cadastrado atrelado a esse cliente.";

                               })*/
                        }
                    ).catch(function (error) {

                        console.log("Log erro cliente");
                        var elementHtml = document.getElementById("textCadastro");
                        elementHtml.style.display = "block";
                        elementHtml.style.color = "rgb(219, 0, 0)"
                        elementHtml.innerText = "Cliente inexistente";

                    })

                }
                else
                {
                    elementHtml.style.display = "block";
                    elementHtml.style.color = "rgb(219, 0, 0)"
                    elementHtml.innerText = "Cpf e/ou Cnh inválido(s)";
                }
            }

        )
    }

)