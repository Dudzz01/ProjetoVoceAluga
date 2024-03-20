package com.teamvocealuga.vocealuga.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.motorista.Motorista;
import com.teamvocealuga.vocealuga.transacao.Transacao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Cliente.CLIENTE_NAME_TABLE)
public class Cliente
{
    public static final String CLIENTE_NAME_TABLE = "cliente";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;


    @Column(name = "nome", unique = false, nullable = false)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "cpf", unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String cpf;

    @Column(name = "telefone",unique = true,nullable = false)
    @NotNull
    @NotEmpty
    private String telefone;


    @Column(name = "data_cadastro",unique = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataCadastro;

    @Column(name = "totalFidelidade",unique = false,nullable = true)
    private double totalFidelidade;

    public Cliente()
    {

    }

    public Cliente(Long id, Filial filial, String nome, String cpf, String telefone, Date dataCadastro, double totalFidelidade,List<Motorista> motoristaList, List<Locacao> locacaoList,List<Transacao> transacaoList)
    {
        this.id = id;
        this.filial = filial;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.totalFidelidade = totalFidelidade;


    }

    public Cliente(ClienteDTO clienteDTO)
    {
        this.id = clienteDTO.getId();;
        this.filial = clienteDTO.getFilial();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.telefone = clienteDTO.getTelefone();
        this.dataCadastro = clienteDTO.getDataCadastro();
        this.totalFidelidade = clienteDTO.getTotalFidelidade();


    }

    public ClienteDTO converterClienteParaDto()
    {
        return new ClienteDTO(this);
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date getDataCadastro() {

        System.out.println("DEBUG DATACADASTRO: "+dataCadastro);

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

        Cliente other = (Cliente) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf)   && Objects.equals(this.telefone, other.telefone) && Objects.equals(this.dataCadastro, other.dataCadastro) && Objects.equals(this.totalFidelidade, other.totalFidelidade) && Objects.equals(this.filial, other.filial);
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
