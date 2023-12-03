package com.teamvocealuga.vocealuga.ordemdemanutencao;

import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.transacao.Transacao;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = OrdemDeManutencao.ORDEMDEMANUTENCAO_TABLE_NAME)
public class OrdemDeManutencao
{
    public static final String ORDEMDEMANUTENCAO_TABLE_NAME = "ordemdemanutencao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(name = "data_entrada",unique = false,nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataEntrada;

    @Column(name = "previsao_saida",unique = false,nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date previsaoSaida;

    public OrdemDeManutencao()
    {

    }

    public OrdemDeManutencao(Long id, Funcionario funcionario, Veiculo veiculo, Date dataEntrada, Date previsaoSaida)
    {
        this.id = id;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
        this.dataEntrada = dataEntrada;
        this.previsaoSaida = previsaoSaida;
    }

    public OrdemDeManutencao(OrdemDeManutencaoDTO ordemDeManutencaoDTO)
    {
        this.id = ordemDeManutencaoDTO.getId();
        this.funcionario = ordemDeManutencaoDTO.getFuncionario();
        this.veiculo = ordemDeManutencaoDTO.getVeiculo();
        this.dataEntrada = ordemDeManutencaoDTO.getDataEntrada();
        this.previsaoSaida = ordemDeManutencaoDTO.getPrevisaoSaida();
    }

    public OrdemDeManutencaoDTO converterOrdemDeManutencaoParaDTO()
    {
        return new OrdemDeManutencaoDTO(this);
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

        OrdemDeManutencao other = (OrdemDeManutencao) obj;

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
