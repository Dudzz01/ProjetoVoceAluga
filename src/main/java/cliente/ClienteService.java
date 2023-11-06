package cliente;

import filial.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService
{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FilialService filialService;

    public ClienteDTO findClienteById(Long id)
    {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        cliente.orElseThrow(()-> new RuntimeException("Erro ao buscar o cliente"));

        ClienteDTO clienteDTO = cliente.get().converterClienteParaDto();

        return clienteDTO;

    }

    public List<ClienteDTO> findClientesByFilialId(Long id)
    {
        filialService.findFilialById(id); // verifica se existe filial
        List<Cliente> clienteList = clienteRepository.findByFilial_Id(id);
        List<ClienteDTO> clienteDTOList = new ArrayList<ClienteDTO>();

        if(clienteList.isEmpty() || clienteList == null)
        {
            throw new RuntimeException("Erro ao encontrar os clientes que foram cadastrados utilizando o servico de uma filial");
        }
        else
        {
            for(Cliente cliente: clienteList)
            {
                clienteDTOList.add(cliente.converterClienteParaDto());
            }

            return clienteDTOList;
        }
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO)
    {
        filialService.findFilialById(clienteDTO.getFilial().getId()); // verifica se existe filial
        clienteDTO.setId(null);
        Cliente cliente = clienteDTO.converterDtoParaCliente();
        cliente = clienteRepository.save(cliente);
        clienteDTO = cliente.converterClienteParaDto();
        return clienteDTO;
    }

    public ClienteDTO updateTelefoneCliente(ClienteDTO clienteDTO)
    {
        ClienteDTO clienteDtoOld = findClienteById(clienteDTO.getId());
        Cliente cliente = clienteDtoOld.converterDtoParaCliente();
        cliente.setTelefone(clienteDTO.getTelefone());
        clienteRepository.save(cliente);
        clienteDTO = cliente.converterClienteParaDto();
        return clienteDTO;
    }

    public ClienteDTO updateTotalFidelidade(ClienteDTO clienteDTO)
    {
        ClienteDTO clienteDtoOld = findClienteById(clienteDTO.getId());
        Cliente cliente = clienteDtoOld.converterDtoParaCliente();
        cliente.setTotalFidelidade(clienteDTO.getTotalFidelidade());
        clienteRepository.save(cliente);
        clienteDTO = cliente.converterClienteParaDto();
        return clienteDTO;
    }

    public void deleteCliente(Long id)
    {
        findClienteById(id);
        try
        {
            clienteRepository.deleteById(id);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Nao se pode deletar o cliente pois ele é uma chave estrangeira em alguma entidade");
        }

    }
}
