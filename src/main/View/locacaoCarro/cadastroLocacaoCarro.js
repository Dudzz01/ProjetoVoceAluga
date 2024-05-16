document.addEventListener("DOMContentLoaded", function (ev)
{
    ev.preventDefault();

    document.getElementById("cadastroLocacao").addEventListener("submit",function (ev)
    {
        ev.preventDefault();

        var cnhMotorista = document.getElementById("motorista").value;

        fetch("http://localhost:8080/motorista/cnh/"+cnhMotorista).then(response => response.json()).then(function (motoristaObject)

            {
                var cpfCliente = document.getElementById("cliente").value;
                if(motoristaObject.cliente.id != null && motoristaObject.id != null && motoristaObject.cliente.cpf === cpfCliente)
                {
                    var codVeiculo = document.getElementById("veiculo").value;
                    fetch("http://localhost:8080/veiculo/"+codVeiculo).then(response => response.json()).then(function (veiculoObject)
                    {
                        if(veiculoObject.id != null)
                        {
                          fetch("http://localhost:8080/funcionario/1").then(response => response.json()).then(function (funcionarioObject)

                              {
                                  if(funcionarioObject.id != null)
                                  {


                                      var dataFimJson= moment(document.getElementById("dataFim").value).format("YYYY-MM-DD HH:mm:ss.SSS");
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


                                      }

                                      fetch("http://localhost:8080/locacao",
                                          {
                                              method: "POST",
                                              headers: {'Content-Type': 'application/json'},
                                              body: JSON.stringify(dataJsonLocacao)
                                          }).then(function (response)
                                          {
                                              if(response.ok)
                                              {
                                                var elementHtml = document.getElementById("texto-resultado-locacao");
                                                elementHtml.style.display = "block";
                                                elementHtml.innerText = "Locacao cadastrada com sucesso";
                                              }
                                              else
                                              {

                                              }
                                          }

                                      )
                                  }
                                  else
                                  {
                                      throw new Error("Funcionário em conflito")
                                  }
                              }

                          )

                        }
                        else
                        {
                            throw new Error("Veículo não encontrado")
                        }
                    })
                }
                else
                {
                    throw new Error("Motorista e/ou Cliente inexistente(s)")
                }



            }

        ).catch(function (error)
        {
            var elementHtml = document.getElementById("texto-resultado-locacao");
            elementHtml.style.display = "block";
            elementHtml.innerText= error;
        })

    })
})