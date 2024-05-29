document.addEventListener("DOMContentLoaded", function (ev)

    {
        ev.preventDefault();

            document.getElementById("cadastroCliente").addEventListener("submit",
                function (ev)
                {
                    ev.preventDefault();
                    var idFilial = document.getElementById("filial").value;
                    console.log("Id filial javascript: "+idFilial);
                    fetch("http://localhost:8080/filial/"+idFilial).then(response => response.json()).then(function (filialObject)
                    {
                        console.log(filialObject)
                        if(filialObject.id != null)
                        {

                            console.log("Entrando no post");
                            var dataCadastroJson = moment(new Date()).format("YYYY-MM-DD HH:mm:ss.SSS");

                            var dataJsonCliente =
                                {
                                    id: 0,
                                    filial: filialObject,
                                    nome: document.getElementById("nome").value,
                                    cpf: document.getElementById("cpf").value ,
                                    telefone: document.getElementById("telefone").value,
                                    dataCadastro: dataCadastroJson,
                                    totalFidelidade: 0

                                }

                                console.log(dataJsonCliente.dataCadastro);

                            fetch("http://localhost:8080/cliente",
                                {
                                    method: "POST",
                                    headers: {'Content-Type': 'application/json'},
                                    body: JSON.stringify(dataJsonCliente)
                                }
                            ).then(function (response){

                                console.log(response.status)

                                if(response.ok)
                                {
                                    console.log(response.ok);
                                    var elementHtml = document.getElementById("textoResultCadastro");
                                    elementHtml.style.display = "block";
                                    elementHtml.style.color = "rgb(13,188,57)";
                                    elementHtml.innerText="Cliente cadastrado com sucesso";
                                }
                                else
                                {
                                    if(response.status === 409)
                                    {
                                        console.log("Cadastro ENTROU NO 409");
                                        throw new Error("Cpf e/ou Telefone já cadastrado(s)");
                                    }
                                    else
                                    {
                                        console.log("NAO ENTREI NO 409");
                                        throw new Error("Conflito ao cadastrar cliente");
                                    }

                                }
                                }).catch(function (error)
                                {
                                    let elementHtml = document.getElementById("textoResultCadastro");
                                    elementHtml.innerText = error;
                                    elementHtml.style.display = "block";
                                    elementHtml.style.color = "rgb(219, 0, 0)"


                                })

                        }
                        else
                        {
                            throw new Error("Erro de Cadastro: Filial Inexistente");
                        }
                    }

                    ).catch(function (error){
                        var elementHtml = document.getElementById("textoResultCadastro");
                        elementHtml.style.display = "block";
                        elementHtml.style.color = "rgb(219, 0, 0)"
                        elementHtml.innerText = error;
                    })

                }
            )





    }


)