package com.teamvocealuga.vocealuga.veiculo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService
{
    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoDTO findVeiculoById(Long id)
    {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        veiculo.orElseThrow(()-> new RuntimeException("Erro ao buscar o veiculo"));
        VeiculoDTO veiculoDTO = veiculo.get().converterVeiculoParaDTO();
        return veiculoDTO;
    }

    public List<VeiculoDTO> findVeiculosByFilialId(Long id)
    {
        List<Veiculo> veiculoList = veiculoRepository.findByFilial_Id(id);
        List<VeiculoDTO> veiculoDTOList = new ArrayList<VeiculoDTO>();

        if(veiculoList.isEmpty() || veiculoList == null)
        {
            throw new RuntimeException("Erro ao buscar os carros da filial");
        }
        else
        {
            for (Veiculo veiculo: veiculoList)
            {
                veiculoDTOList.add(veiculo.converterVeiculoParaDTO());
            }
            return veiculoDTOList;
        }
    }

    @Transactional
    public VeiculoDTO createVeiculo(VeiculoDTO veiculoDTO)
    {
        veiculoDTO.setId(null);
        Veiculo veiculo = veiculoDTO.converterDTOParaVeiculo();
        veiculo = veiculoRepository.save(veiculo);
        veiculoDTO = veiculo.converterVeiculoParaDTO();
        return veiculoDTO;
    }

    @Transactional
    public VeiculoDTO updateStatus(VeiculoDTO veiculoDTO)
    {
        VeiculoDTO veiculoDTONew = findVeiculoById(veiculoDTO.getId());
        Veiculo veiculo = veiculoDTONew.converterDTOParaVeiculo();
        veiculo.setStatus(veiculoDTO.getStatus());
        veiculoDTO = veiculo.converterVeiculoParaDTO();
        return veiculoDTO;
    }

    public void deleteVeiculo(Long id)
    {
        findVeiculoById(id);

        try {
            veiculoRepository.deleteById(id);
        }catch (Exception exception)
        {
            throw new RuntimeException("Nao da pra deletar o veiculo pois ele e uma chave estrangeira em alguma outra entidade");
        }
    }

}
