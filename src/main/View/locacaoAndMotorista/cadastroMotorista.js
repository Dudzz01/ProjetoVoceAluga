document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

        document.getElementById("cadast_motorista").addEventListener("submit", function (ev)

            {
                ev.preventDefault();
                var idCliente = document.getElementById("CodigoDoCliente").value;
                var dataNascimentoJson = moment(document.getElementById("dataNascimento").value).format("YYYY-MM-DD HH:mm:ss.SSS");

                fetch("http://localhost:8080/cliente/"+idCliente).then(response => response.json()).then(function (clienteObject)
                    {
                        if(clienteObject.id != null)
                        {
                            var dataJsonMotorista = {
                                  id: 0,
                                  cliente: clienteObject,
                                  nome: document.getElementById("nome").value,
                                  cpf: document.getElementById("cpf").value,
                                  cnh: document.getElementById("cnh").value,
                                  dataNascimento: dataNascimentoJson
                            }

                            fetch("http://localhost:8080/motorista",
                                {
                                    method: "POST",
                                    headers: {'Content-Type': 'application/json'},
                                    body: JSON.stringify(dataJsonMotorista)
                                }).then(function (response)
                            {
                                if(response.ok)
                                {
                                   var elementHtml = document.getElementById("textCadastro");
                                    elementHtml.style.display = "block";
                                    elementHtml.innerText = "Cadastro com sucesso";
                                }
                                else
                                {
                                    throw new Error("Informações inválidas sobre o motorista");
                                }
                            })

                        }
                        else
                        {
                            throw new Error("Código Cliente inválido");
                        }
                    }

                ).catch(function (error)
                {
                    var elementHtml = document.getElementById("textCadastro");
                    elementHtml.style.display = "block";
                    elementHtml.innerText = error;

                })
            }

        )
    }

)