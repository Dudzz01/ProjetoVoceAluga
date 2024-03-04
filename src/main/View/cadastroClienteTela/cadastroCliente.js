document.addEventListener("DOMContentLoaded", function ()

    {
        document.getElementById("cadastroCliente").addEventListener("submit",
            function (ev)
            {
                ev.preventDefault();
                var dataJsonCliente =
                    {
                        id: 0,
                    filial: parseInt(document.getElementById("filial").value),
                    nome: document.getElementById("nome").value,
                    cpf: document.getElementById("cpf").value ,
                    telefone: document.getElementById("telefone").value,
                    dataCadastro: null,
                        totalFidelidade: 0
                    }

                fetch("http://localhost:8080/cliente",
                    {
                         method: "POST",
                         headers: {'Content-Type': 'application/json'},
                         body: JSON.stringify(dataJsonCliente)
                    }
                    ).then(function (response){ if(response.ok){var elementHtml = document.getElementById("textoResultCadastro"); elementHtml.style.display = "block"; elementHtml.innerText="Cliente cadastrado com sucesso"; } else {throw new Error("Erro ao cadastrar o cliente.")}}).catch(function (error){var elementHtml = document.getElementById("textoResultCadastro"); elementHtml.style.display = "block"; elementHtml.innerText= error.substring(0,7);})
            }
        )
    }


)