package com.teamvocealuga.vocealuga.motorista;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.configs.CustomDateDeserializer;
import com.teamvocealuga.vocealuga.configs.CustomDateSerializer;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = Motorista.MOTORISTA_TABLE_NAME)
public class Motorista
{
    public static final String MOTORISTA_TABLE_NAME = "motorista";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; //Many Motorista(1,N) To One Cliente(1)

    @Column(name = "nome",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "cpf",unique = true,nullable = false)
    @NotNull
    @NotEmpty
    private String cpf;

    @Column(name = "cnh",unique = true,nullable = false)
    @NotNull
    @NotEmpty
    private String cnh;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "dt_nascimento",unique = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataNascimento;

    public Motorista()
    {

    }

    public Motorista(Long id, Cliente cliente,String nome, String cpf, String cnh, Date dataNascimento)
    {
        this.id = id;
        this.cliente = cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.dataNascimento = dataNascimento;

    }

    public Motorista(MotoristaDTO motoristaDTO)
    {
        this.id = motoristaDTO.getId();;
        this.cliente = motoristaDTO.getCliente();
        this.nome = motoristaDTO.getNome();
        this.cpf = motoristaDTO.getCpf();
        this.cnh = motoristaDTO.getCnh();
        this.dataNascimento = motoristaDTO.getDataNascimento();

    }

    public MotoristaDTO converterMotoristaParaDTO()
    {
        return new MotoristaDTO(this);
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

        Motorista other = (Motorista) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf) && Objects.equals(this.cnh, other.cnh) && Objects.equals(this.cliente, other.cliente) &&  Objects.equals(this.dataNascimento, other.dataNascimento);
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
