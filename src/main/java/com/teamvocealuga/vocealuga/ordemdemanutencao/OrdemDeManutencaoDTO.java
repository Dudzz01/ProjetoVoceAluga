package com.teamvocealuga.vocealuga.ordemdemanutencao;

import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

public class OrdemDeManutencaoDTO
{
    private Long id;


    private Funcionario funcionario;


    private Veiculo veiculo;


    private Date dataEntrada;


    private Date previsaoSaida;

    public OrdemDeManutencaoDTO()
    {

    }

    public OrdemDeManutencaoDTO(Long id, Funcionario funcionario, Veiculo veiculo, Date dataEntrada, Date previsaoSaida)
    {
        this.id = id;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
        this.dataEntrada = dataEntrada;
        this.previsaoSaida = previsaoSaida;
    }

    public OrdemDeManutencaoDTO(OrdemDeManutencao ordemDeManutencao)
    {
        this.id = ordemDeManutencao.getId();
        this.funcionario = ordemDeManutencao.getFuncionario();
        this.veiculo = ordemDeManutencao.getVeiculo();
        this.dataEntrada = ordemDeManutencao.getDataEntrada();
        this.previsaoSaida = ordemDeManutencao.getPrevisaoSaida();
    }

    public OrdemDeManutencao converterDTOParaOrdemDeManutencao()
    {
        return new OrdemDeManutencao(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getPrevisaoSaida() {
        return previsaoSaida;
    }

    public void setPrevisaoSaida(Date previsaoSaida) {
        this.previsaoSaida = previsaoSaida;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(obj == null)
        {
            return  false;
        }
        if(!(obj instanceof Filial))
        {
            return  false;
        }

        OrdemDeManutencaoDTO other = (OrdemDeManutencaoDTO) obj;

        if(this.id == null)
        {
            if(other.id!=null)
            {
                return false;
            }
            else if(!(this.id.equals(other.id)))
            {
                return false;
            }

        }

        return Objects.equals(this.id,other.id) && Objects.equals(this.funcionario,other.funcionario) && Objects.equals(this.veiculo, other.veiculo) && Objects.equals(this.dataEntrada, other.dataEntrada) && Objects.equals(this.previsaoSaida, other.previsaoSaida);
    }

    @Override
    public int hashCode()
    {
        final int pararNumber = 31; //numero parametro definido como 31
        int hashCode = 1;

        hashCode = pararNumber * hashCode + (this.id == null ? 0 : this.id.hashCode()); //operador ternario

        return hashCode;

    }
}
