package com.teamvocealuga.vocealuga.devolucao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.configs.CustomDateSerializer;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.transacao.Transacao;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = Devolucao.DEVOLUCAO_TABLE_NAME)
public class Devolucao
{
    public static final String DEVOLUCAO_TABLE_NAME = "devolucao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @OneToOne
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "datapedido",unique = false,nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataPedido;

    @JsonSerialize(using = CustomDateSerializer.class)
   @Column(name = "datafimesperado",unique = false,nullable = false)
   @Temporal(value = TemporalType.TIMESTAMP)
   @NotNull
   private Date dataFimEsperado;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "datadevolucao",unique = false,nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataDevolucao;

    @Column(name = "valorMulta",unique = false,nullable = true)
    @NotNull
    private float valorMulta;

    public Devolucao()
    {

    }

    public Devolucao(Long id, Cliente cliente, Veiculo veiculo, Locacao locacao, Transacao transacao, Date dataPedido, Date dataFimEsperado, Date dataDevolucao, float valorMulta) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.locacao = locacao;
        this.transacao = transacao;
        this.dataPedido = dataPedido;
        this.dataFimEsperado = dataFimEsperado;
        this.dataDevolucao = dataDevolucao;
        this.valorMulta = valorMulta;
    }

    public Devolucao(DevolucaoDTO devolucaoDTO)
    {
        this.id = devolucaoDTO.getId();
        this.cliente = devolucaoDTO.getCliente();
        this.veiculo = devolucaoDTO.getVeiculo();
        this.locacao = devolucaoDTO.getLocacao();
        this.transacao = devolucaoDTO.getTransacao();
        this.dataPedido = devolucaoDTO.getDataPedido();
        this.dataFimEsperado = devolucaoDTO.getDataFimEsperado();
        this.dataDevolucao = devolucaoDTO.getDataDevolucao();
        this.valorMulta = devolucaoDTO.getValorMulta();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataFimEsperado() {
        return dataFimEsperado;
    }

    public void setDataFimEsperado(Date dataFimEsperado) {
        this.dataFimEsperado = dataFimEsperado;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public float getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(float valorMulta) {
        this.valorMulta = valorMulta;
    }

    public DevolucaoDTO converterDevolucaoParaDTO()
    {
        DevolucaoDTO devolucaoDTO = new DevolucaoDTO(this);
        return devolucaoDTO;
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
        if(!(obj instanceof Devolucao ))
        {
            return  false;
        }

        Devolucao other = (Devolucao) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.cliente,other.cliente) && Objects.equals(this.veiculo, other.veiculo) && Objects.equals(this.locacao, other.locacao) && Objects.equals(this.dataPedido, other.dataPedido) && Objects.equals(this.dataFimEsperado, other.dataFimEsperado) && Objects.equals(this.dataDevolucao, other.dataDevolucao)&& Objects.equals(this.valorMulta, other.valorMulta);
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
