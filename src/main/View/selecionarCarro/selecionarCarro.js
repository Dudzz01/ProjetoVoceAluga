document.addEventListener("DOMContentLoaded",function (ev)
{
    ev.preventDefault();

   /* let count_id = 1;
    let bool_increment = false;
    while(count_id <= 6)
    {
        fetch("http://localhost:8080/veiculo/"+count_id).then(response => response.json()).then(function (veiculoObject)
            {
                console.log( veiculoObject.categoria);
                console.log(veiculoObject.preco);
                console.log(veiculoObject.id);
                console.log("QUANTAS VEZES: "+count_id);
                document.getElementById("categ_"+count_id).innerText = veiculoObject.categoria;
                document.getElementById("preco_"+count_id).innerText = veiculoObject.preco;
                document.getElementById("carro"+count_id).innerText = veiculoObject.id;
                console.log(document.getElementById("categ_"+count_id).value);
                console.log(document.getElementById("preco_"+count_id).value);
                console.log(document.getElementById("carro"+count_id).value);
                bool_increment = true;

            }
        )

        console.log(count_id);
        if(bool_increment === true)
        {
            count_id++;
            bool_increment = false;
        }


    }*/

    async function fetchVeiculos() {
        for (let count_id = 1; count_id < 7; count_id++) {
            try {
                const response = await fetch("http://localhost:8080/veiculo/" + count_id);
                const veiculoObject = await response.json();

                console.log(veiculoObject.categoria);
                console.log(veiculoObject.preco);
                console.log(veiculoObject.id);
                console.log("QUANTAS VEZES: " + count_id);

                const categElem = document.getElementById("categ_" + count_id);
                const precoElem = document.getElementById("preco_" + count_id);
                const carroElem = document.getElementById("carro" + count_id);
                const nomeCarroElem = document.getElementById("nome_carro"+ count_id)

                if (categElem) categElem.innerText = veiculoObject.categoria;
                if (precoElem) precoElem.innerText = veiculoObject.preco;
                if (carroElem) carroElem.innerText = veiculoObject.id;
                if(nomeCarroElem) nomeCarroElem.innerText = veiculoObject.nome;

                if (count_id === 6) {
                    break;
                }

            } catch (error) {
                console.error("Erro ao buscar veículo com id " + count_id, error);
            }
        }
    }


    window.onload = function() {
        fetchVeiculos();
    };



}
)