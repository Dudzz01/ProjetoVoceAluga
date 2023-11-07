package com.teamvocealuga.vocealuga.motorista;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;


import java.util.Date;
import java.util.Objects;

public class MotoristaDTO
{

    private Long id;


    private Cliente cliente; //Many Motorista(1,N) To One Cliente(1)

    private Locacao locacao;


    private String nome;



    private String cpf;


    private String cnh;


    private Date dataNascimento;

    public MotoristaDTO()
    {

    }

    public MotoristaDTO(Long id, Cliente cliente,Locacao locacao, String nome, String cpf, String cnh, Date dataNascimento)
    {
        this.id = id;
        this.cliente = cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.dataNascimento = dataNascimento;
        this.locacao = locacao;
    }

    public MotoristaDTO(Motorista motorista)
    {
        this.id = motorista.getId();;
        this.cliente = motorista.getCliente();
        this.nome = motorista.getNome();
        this.cpf = motorista.getCpf();
        this.cnh = motorista.getCnh();
        this.dataNascimento = motorista.getDataNascimento();
        this.locacao = motorista.getLocacao();
    }

    public Motorista converterDTOParaMotorista()
    {
        return new Motorista(this);
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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

        MotoristaDTO other = (MotoristaDTO) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf) && Objects.equals(this.cnh, other.cnh) && Objects.equals(this.cliente, other.cliente) && Objects.equals(this.locacao, other.locacao) && Objects.equals(this.dataNascimento, other.dataNascimento);
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
