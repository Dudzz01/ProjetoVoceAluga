package com.teamvocealuga.vocealuga.transacao;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.motorista.Motorista;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = Transacao.TRANSACAO_TABLE_NAME)
public class Transacao
{
    public static final String TRANSACAO_TABLE_NAME = "transacao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //ONE TO ONE
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "valor_total",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private double valorTotal;

    @Column(name = "data_pagamento",unique = false,nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    @NotEmpty
    private Date dataPagamento;

    @Column(name = "forma_pagamento",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String formaPagamento;

    @Column(name = "numNotaFiscal",unique = false, nullable = false)
    @NotNull
    @NotEmpty
    private String numNotaFiscal;

    @Column(name = "status",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String status;

    @Column(name = "cod_transacao",unique = true,nullable = false)
    @NotNull
    @NotEmpty
    private Long codTransacao;

    public Transacao()
    {

    }

    public Transacao(Long id, Locacao locacao, Cliente cliente, double valorTotal, Date dataPagamento, String formaPagamento, String numNotaFiscal, String status, Long codTransacao) {
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

    public Transacao(TransacaoDTO transacaoDTO)
    {
        this.id = transacaoDTO.getId();
        this.locacao = transacaoDTO.getLocacao();
        this.cliente = transacaoDTO.getCliente();
        this.valorTotal = transacaoDTO.getValorTotal();
        this.dataPagamento = transacaoDTO.getDataPagamento();
        this.formaPagamento = transacaoDTO.getFormaPagamento();
        this.numNotaFiscal = transacaoDTO.getNumNotaFiscal();
        this.status = transacaoDTO.getStatus();
        this.codTransacao = transacaoDTO.getCodTransacao();
    }

    public TransacaoDTO converterTransacaoParaDTO()
    {
        return new TransacaoDTO(this);
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

        Transacao other = (Transacao) obj;

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
