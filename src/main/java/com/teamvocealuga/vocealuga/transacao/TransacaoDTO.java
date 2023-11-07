package com.teamvocealuga.vocealuga.transacao;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import java.util.Date;
import java.util.Objects;

public class TransacaoDTO
{
    private Long id;


    private Locacao locacao;


    private Cliente cliente;


    private double valorTotal;


    private Date dataPagamento;


    private String formaPagamento;


    private String numNotaFiscal;


    private String status;


    private Long codTransacao;

    public TransacaoDTO()
    {

    }

    public TransacaoDTO(Long id, Locacao locacao, Cliente cliente, double valorTotal, Date dataPagamento, String formaPagamento, String numNotaFiscal, String status, Long codTransacao) {
        this.id = id;
        this.locacao = locacao;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.numNotaFiscal = numNotaFiscal;
        this.status = status;
        this.codTransacao = codTransacao;
    }

    public TransacaoDTO(Transacao transacao)
    {
        this.id = transacao.getId();
        this.locacao = transacao.getLocacao();
        this.cliente = transacao.getCliente();
        this.valorTotal = transacao.getValorTotal();
        this.dataPagamento = transacao.getDataPagamento();
        this.formaPagamento = transacao.getFormaPagamento();
        this.numNotaFiscal = transacao.getNumNotaFiscal();
        this.status = transacao.getStatus();
        this.codTransacao = transacao.getCodTransacao();
    }

    public Transacao converterDTOParaTransacao()
    {
        return new Transacao(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getNumNotaFiscal() {
        return numNotaFiscal;
    }

    public void setNumNotaFiscal(String numNotaFiscal) {
        this.numNotaFiscal = numNotaFiscal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCodTransacao() {
        return codTransacao;
    }

    public void setCodTransacao(Long codTransacao) {
        this.codTransacao = codTransacao;
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

        TransacaoDTO other = (TransacaoDTO) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.locacao,other.locacao) && Objects.equals(this.cliente, other.cliente) && Objects.equals(this.valorTotal, other.valorTotal) && Objects.equals(this.dataPagamento, other.dataPagamento) && Objects.equals(this.formaPagamento, other.formaPagamento) && Objects.equals(this.numNotaFiscal, other.numNotaFiscal) && Objects.equals(this.status, other.status) && Objects.equals(this.codTransacao, other.codTransacao);
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
