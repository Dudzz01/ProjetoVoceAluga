package com.teamvocealuga.vocealuga.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.motorista.Motorista;
import com.teamvocealuga.vocealuga.transacao.Transacao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClienteDTO
{
    private Long id;
    private Filial filial;

    private List<Locacao> locacaoList = new ArrayList<Locacao>();
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataCadastro;
    private double totalFidelidade;
    private List<Motorista> motoristaList;
    private List<Transacao> transacaoList = new ArrayList<Transacao>();

    public ClienteDTO()
    {

    }

    public ClienteDTO(Long id, Filial filial,List<Locacao> locacaoList, String nome, String cpf, String telefone, Date dataCadastro, double totalFidelidade, List<Motorista> motoristaList,List<Transacao> transacaoList)
    {
        this.id = id;
        this.filial = filial;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.totalFidelidade = totalFidelidade;
        this.motoristaList = motoristaList;
        this.locacaoList = locacaoList;
        this.transacaoList = transacaoList;
    }

    public ClienteDTO(Cliente cliente)
    {
        this.id = cliente.getId();;
        this.filial = cliente.getFilial();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataCadastro = cliente.getDataCadastro();
        this.totalFidelidade = cliente.getTotalFidelidade();
        this.motoristaList = cliente.getMotoristaList();
        this.locacaoList = cliente.getLocacaoList();
        this.transacaoList = cliente.getTransacaoList();
    }

    public Cliente converterDtoParaCliente()
    {
        return new Cliente(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getTotalFidelidade() {
        return totalFidelidade;
    }

    public void setTotalFidelidade(double totalFidelidade) {
        this.totalFidelidade = totalFidelidade;
    }

    @JsonIgnore
    public List<Motorista> getMotoristaList() {
        return motoristaList;
    }

    public void setMotoristaList(List<Motorista> motoristaList) {
        this.motoristaList = motoristaList;
    }

    @JsonIgnore
    public List<Locacao> getLocacaoList() {
        return locacaoList;
    }

    public void setLocacaoList(List<Locacao> locacaoList) {
        this.locacaoList = locacaoList;
    }

    @JsonIgnore
    public List<Transacao> getTransacaoList() {
        return transacaoList;
    }

    public void setTransacaoList(List<Transacao> transacaoList) {
        this.transacaoList = transacaoList;
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
        if(!(obj instanceof Filial ))
        {
            return  false;
        }

        ClienteDTO other = (ClienteDTO) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf)  && Objects.equals(this.telefone, other.telefone) && Objects.equals(this.dataCadastro, other.dataCadastro) && Objects.equals(this.totalFidelidade, other.totalFidelidade);
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
