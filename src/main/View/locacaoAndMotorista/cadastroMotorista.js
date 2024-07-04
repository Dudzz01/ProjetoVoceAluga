document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

        document.getElementById("cadast_motorista").addEventListener("submit", function (ev)

            {
                ev.preventDefault();
                var idCliente = document.getElementById("CodigoDoCliente").value;
                var dataNascimentoJson = moment(document.getElementById("dataNascimento").value).format("YYYY-MM-DD HH:mm:ss.SSS");
                let elementHtml = document.getElementById("textCadastro");

                fetch("http://localhost:8080/cliente/"+idCliente).then(response => response.json()).then(function (clienteObject)
                    {
                        fetch("http://localhost:8080/motorista?cpf="+document.getElementById("cpf").value+"&cnh="+document.getElementById("cnh").value+"&clienteId="+idCliente).then(response => response.json()).then(function (responseBody)
                        {
                            if(responseBody.ok) {


                                if (clienteObject.id != null) {
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
                                        }).then(function (response) {
                                        if (response.ok) {

                                            elementHtml.style.display = "block";
                                            elementHtml.style.color = "rgb(13,188,57)";
                                            elementHtml.innerText = "Cadastro com sucesso";
                                        } else {

                                            elementHtml.style.display = "block";
                                            elementHtml.style.color = "rgb(219, 0, 0)"
                                            elementHtml.innerText = "Cpf/Cnh já cadastrado no sistema";
                                            throw new Error("Informações inválidas sobre o motorista");
                                        }
                                    })

                                } else {
                                    throw new Error("Código Cliente inválido");
                                }
                            }
                            else
                            {
                                console.log("URL: http://localhost:8080/motorista?cpf="+document.getElementById("cpf").value+"&cnh="+document.getElementById("cnh").value+"&clienteId="+idCliente);
                                var elementHtml = document.getElementById("textCadastro");
                                elementHtml.style.display = "block";
                                elementHtml.style.color = "rgb(219, 0, 0)"
                                elementHtml.innerText = "Motorista já está cadastrado atrelado a esse cliente.";
                            }
                        }
                     )/*.catch(function (error)
                        {

                            console.log("URL: http://localhost:8080/motorista?cpf="+document.getElementById("cpf").value+"&cnh="+document.getElementById("cnh").value+"&clienteId="+idCliente);
                            var elementHtml = document.getElementById("textCadastro");
                            elementHtml.style.display = "block";
                            elementHtml.style.color = "rgb(219, 0, 0)"
                            elementHtml.innerText = "Motorista já está cadastrado atrelado a esse cliente.";

                        })*/
                    }

                ).catch(function (error)
                {

                    console.log("Log erro cliente");
                    var elementHtml = document.getElementById("textCadastro");
                    elementHtml.style.display = "block";
                    elementHtml.style.color = "rgb(219, 0, 0)"
                    elementHtml.innerText = "Cliente inexistente";

                })
            }

        )
    }

)