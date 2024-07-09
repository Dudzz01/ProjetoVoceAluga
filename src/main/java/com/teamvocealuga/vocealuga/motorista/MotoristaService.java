package com.teamvocealuga.vocealuga.motorista;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService
{
    @Autowired
    private MotoristaRepository motoristaRepository;


    public MotoristaDTO findMotoristaById(Long id)
    {
        Optional<Motorista> motorista = motoristaRepository.findById(id);

        motorista.orElseThrow(()-> new RuntimeException("Erro ao encontrar o motorista"));

        MotoristaDTO motoristaDTO = motorista.get().converterMotoristaParaDTO();

        return motoristaDTO;
    }

    public boolean findMotoristaByCpfAndCnhAndCliente(String cpf, String cnh, Long cliente_id)
    {
        Optional<Motorista> motorista = motoristaRepository.findByCpfAndCnhAndCliente_Id(cpf,cnh, cliente_id);

        if(motorista.isEmpty())
        {
            return false;
        }

            return true;


    }

    public MotoristaDTO findMotoristaByCnh(String cnh)
    {
        Optional<Motorista> motorista = motoristaRepository.findByCnh(cnh);

        if(motorista.isEmpty() || motorista == null)
        {
            return null;
        }

        MotoristaDTO motoristaDTO = motorista.get().converterMotoristaParaDTO();

        return motoristaDTO;
    }

    public boolean checkMotoristaCanCreate(String cpf, String cnh)
    {
        MotoristaDTO motoristaCpf = this.findMotoristaByCpf(cpf);
        MotoristaDTO motoristaCnh = this.findMotoristaByCnh(cnh);

        if(motoristaCpf == null && motoristaCnh == null)
        {
            return true;
        }
        else
        {
            if(motoristaCpf != null && motoristaCpf.getCnh().equals(cnh))
            {
                return true;
            }

            if(motoristaCnh != null && motoristaCnh.getCpf().equals(cpf))
            {
                return true;
            }
        }

        return false;


    }

    public MotoristaDTO findMotoristaByCpf(String cpf)
    {
        Optional<Motorista> motorista = motoristaRepository.findByCpf(cpf);

        if(motorista.isEmpty() || motorista == null)
        {
            return null;
        }

        MotoristaDTO motoristaDTO = motorista.get().converterMotoristaParaDTO();

        return motoristaDTO;
    }

    public List<MotoristaDTO> findMotoristasByClientId(Long id)
    {
        List<Motorista> motoristaList = motoristaRepository.findByCliente_Id(id);
        List<MotoristaDTO> motoristaDTOList = new ArrayList<MotoristaDTO>();

        if(motoristaList.isEmpty() || motoristaList == null)
        {
            for(Motorista motorista: motoristaList)
            {
                motoristaDTOList.add(motorista.converterMotoristaParaDTO());
            }
        }
        return motoristaDTOList;
    }
    @Transactional
    public MotoristaDTO createMotorista(MotoristaDTO motoristaDto)
    {
        motoristaDto.setId(null);
        Motorista motorista = motoristaDto.converterDTOParaMotorista();
        motorista = motoristaRepository.save(motorista);
        motoristaDto = motorista.converterMotoristaParaDTO();
        return motoristaDto;
    }

    //Nao quero atualizar nada do motorista

    public void deleteMotorista(Long id)
    {
        findMotoristaById(id);

        try
        {
            motoristaRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new RuntimeException("Nao pode ser deletado por ser uma chave estrangeira de outra entidade");
        }
    }



}
