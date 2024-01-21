package com.teamvocealuga.vocealuga.devolucao;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.transacao.Transacao;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class DevolucaoDTO
{
    private Long id;


    private Cliente cliente;


    private Veiculo veiculo;


    private Locacao locacao;


    private Transacao transacao;


    private Date dataPedido;


    private Date dataFimEsperado;


    private Date dataDevolucao;


    private float valorMulta;

    public DevolucaoDTO()
    {

    }

    public DevolucaoDTO(Long id, Cliente cliente, Veiculo veiculo, Locacao locacao, Transacao transacao, Date dataPedido, Date dataFimEsperado, Date dataDevolucao, float valorMulta) {
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

    public DevolucaoDTO(Devolucao devolucao)
    {
        this.id = devolucao.getId();
        this.cliente = devolucao.getCliente();
        this.veiculo = devolucao.getVeiculo();
        this.locacao = devolucao.getLocacao();
        this.transacao = devolucao.getTransacao();
        this.dataPedido = devolucao.getDataPedido();
        this.dataFimEsperado = devolucao.getDataFimEsperado();
        this.dataDevolucao = devolucao.getDataDevolucao();
        this.valorMulta = devolucao.getValorMulta();
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

    public Devolucao converterDtoParaDevolucao()
    {
        Devolucao devolucao = new Devolucao(this);
        return devolucao;
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

        DevolucaoDTO other = (DevolucaoDTO) obj;

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
