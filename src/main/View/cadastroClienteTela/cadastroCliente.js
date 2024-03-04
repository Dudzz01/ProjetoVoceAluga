document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

            document.getElementById("cadastroCliente").addEventListener("submit",
                function (ev)
                {
                    var idFilial = document.getElementById("filial").value;
                    fetch("http://localhost:8080/filial/"+idFilial).then(response => response.json()).then(function (filialObject)
                    {
                        ev.preventDefault();
                        var dataJsonCliente =
                            {
                                id: 0,
                                filial: filialObject,
                                nome: document.getElementById("nome").value,
                                cpf: document.getElementById("cpf").value ,
                                telefone: document.getElementById("telefone").value,
                                dataCadastro: new Date(),
                                totalFidelidade: 0

                            }

                        fetch("http://localhost:8080/cliente",
                            {
                                method: "POST",
                                headers: {'Content-Type': 'application/json'},
                                body: JSON.stringify(dataJsonCliente)
                            }
                        ).then(function (response){ if(response.ok){console.log(response.ok); var elementHtml = document.getElementById("textoResultCadastro"); elementHtml.style.display = "block"; elementHtml.innerText="Cliente cadastrado com sucesso"; } else {throw new Error("Erro ao cadastrar o cliente.")}}).catch(function (error){var elementHtml = document.getElementById("textoResultCadastro"); elementHtml.style.display = "block"; elementHtml.innerText= error.substring(0,7);})
                    }
                    )

                }
            )





    }


)