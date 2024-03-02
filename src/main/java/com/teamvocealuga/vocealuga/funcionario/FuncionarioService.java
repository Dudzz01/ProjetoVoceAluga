package com.teamvocealuga.vocealuga.funcionario;

import com.teamvocealuga.vocealuga.filial.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService
{
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FilialService filialService;

    public FuncionarioDTO findFuncionarioById(Long id)
    {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isEmpty())
        {
            throw new RuntimeException("Funcionário não encontrado!");
        }
        else
        {
            FuncionarioDTO funcionarioDTO = funcionario.get().converterFuncionarioParaDTO();
            return funcionarioDTO;
        }
    }

    public FuncionarioDTO findFuncionarioByCpf(String cpf)
    {
        Optional<Funcionario> funcionario = funcionarioRepository.findByCpf(cpf);

        if(funcionario.isEmpty())
        {
            return null;
        }


        FuncionarioDTO funcionarioDTO = funcionario.get().converterFuncionarioParaDTO();

        return funcionarioDTO;
    }

    public List<FuncionarioDTO> findFuncionariosByFilialId(Long id)
    {
        List<Funcionario> funcionarioList = funcionarioRepository.findByFilial_Id(id);
        List<FuncionarioDTO> funcionarioDTOList = new ArrayList<FuncionarioDTO>();

        if(funcionarioList.isEmpty() || funcionarioList == null)
        {
            throw new RuntimeException("Lista de funcionários nessa filial não foi encontrada");
        }
        else
        {
            for(Funcionario funcionario: funcionarioList)
            {
                funcionarioDTOList.add(funcionario.converterFuncionarioParaDTO());
            }

            return  funcionarioDTOList;
        }
    }

    @Transactional
    public FuncionarioDTO createFuncionario(FuncionarioDTO funcionarioDTO)
    {
        filialService.findFilialById(funcionarioDTO.getFilial().getId()); // verifica se a filial existe no sistema
        funcionarioDTO.setId(null);
        Funcionario funcionario = funcionarioDTO.converterDtoParaFuncionario();
        funcionario = funcionarioRepository.save(funcionario);
        return funcionario.converterFuncionarioParaDTO();
    }

    @Transactional
    public String createAcessLogin(FuncionarioDTO funcionarioDTO)
    {
        System.out.println("FUNCIONARIO DO JSON(FRONTEND) PASSWORD ANTES DO IF: "+funcionarioDTO.getPassword());
        if(findFuncionarioByCpf(funcionarioDTO.getCpf()) != null)
        {
            FuncionarioDTO funcionario = findFuncionarioByCpf(funcionarioDTO.getCpf());

            System.out.println("FUNCIONARIO DO BANCO PASSWORD: "+ funcionario.getPassword());
            System.out.println("FUNCIONARIO DO JSON(FRONTEND) PASSWORD: "+funcionarioDTO.getPassword());
            System.out.println("FUNCIONARIO DO JSON(FRONTEND) CPF: "+funcionarioDTO.getCpf());

            if(funcionarioDTO.getCpf().equals(funcionario.getCpf()) && funcionarioDTO.getPassword().equals(funcionario.getPassword()))
            {
                return "Login autorizado com sucesso";
            }
        }


            return "Senha ou Cpf incorreto(s)";


    }

    @Transactional
    public FuncionarioDTO updatePasswordFuncionario(FuncionarioDTO funcionarioDTO)
    {
        FuncionarioDTO funcionarioDTONew = findFuncionarioById(funcionarioDTO.getId()); // procura o funcionario com esse id que já esta no banco de dados
        Funcionario funcionarioNew = funcionarioDTONew.converterDtoParaFuncionario(); // converte esse funcionárioDTO que buscou no banco de dados para um modelo
        funcionarioNew.setPassword(funcionarioDTO.getPassword()); // seta a senha do nosso funcionario que esta no banco de dados com um novo valor de senha que é o valor da senha do objeto que é passado no parametro da funcao
        funcionarioRepository.save(funcionarioNew); //apos alterar a senha, salva o objeto com esse novo valor de senha no banco de dados
        funcionarioDTONew = funcionarioNew.converterFuncionarioParaDTO(); //converte esse objeto para um DTO novamente para retornar a funcao
        return funcionarioDTONew;
    }

    @Transactional
    public FuncionarioDTO updateFuncaoFuncionario(FuncionarioDTO funcionarioDTO)
    {
        FuncionarioDTO funcionarioDtoNew = findFuncionarioById(funcionarioDTO.getId());
        Funcionario funcionarioNew = funcionarioDtoNew.converterDtoParaFuncionario();
        funcionarioNew.setFuncao(funcionarioDTO.getFuncao());
        funcionarioRepository.save(funcionarioNew);
        funcionarioDtoNew = funcionarioNew.converterFuncionarioParaDTO();
        return funcionarioDtoNew;
    }

    @Transactional
    public  FuncionarioDTO updateStatusFuncionario(FuncionarioDTO funcionarioDTO)
    {
        FuncionarioDTO funcionarioDtoNew = findFuncionarioById(funcionarioDTO.getId());
        Funcionario funcionarioNew = funcionarioDtoNew.converterDtoParaFuncionario();
        funcionarioNew.setStatus(funcionarioDTO.getStatus());
        funcionarioRepository.save(funcionarioNew);
        funcionarioDtoNew = funcionarioNew.converterFuncionarioParaDTO();
        return funcionarioDtoNew;
    }

    public void deleteFuncionario(Long id)
    {
        findFuncionarioById(id);

        try
        {
            funcionarioRepository.deleteById(id);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Nao pode ser deletado pois é a chave estrangeira de alguma tabela");
        }
    }


}
