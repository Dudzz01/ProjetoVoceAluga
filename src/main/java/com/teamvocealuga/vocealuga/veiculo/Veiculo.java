package com.teamvocealuga.vocealuga.veiculo;

import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.ordemdemanutencao.OrdemDeManutencao;
import com.teamvocealuga.vocealuga.transacao.Transacao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Veiculo.VEICULO_TABLE_NAME)
public class Veiculo
{
    public static final String VEICULO_TABLE_NAME = "veiculo";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @Column(name = "status",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String status;



    @Column(name = "nome",unique = false, nullable = false)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "categoria",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String categoria;

    @Column(name = "kmtotal",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private double kmTotal;

    @Column(name = "preco",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private double preco;

    public Veiculo()
    {

    }

    public Veiculo(Long id, Filial filial,String nome, String status, String categoria, double kmTotal, double preco) {
        this.id = id;

        this.filial = filial;
        this.status = status;
        this.categoria = categoria;
        this.kmTotal = kmTotal;
        this.nome = nome;
        this.preco = preco;
    }

    public Veiculo(VeiculoDTO veiculoDTO)
    {
        this.id = veiculoDTO.getId();

        this.filial = veiculoDTO.getFilial();
        this.status = veiculoDTO.getStatus();
        this.categoria = veiculoDTO.getCategoria();
        this.kmTotal = veiculoDTO.getKmTotal();
        this.nome = veiculoDTO.getNome();
        this.preco = veiculoDTO.getPreco();
    }

    public VeiculoDTO converterVeiculoParaDTO()
    {
        return new VeiculoDTO(this);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getKmTotal() {
        return kmTotal;
    }

    public void setKmTotal(double kmTotal) {
        this.kmTotal = kmTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco( double preco) {
        this.preco = preco;
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

        Veiculo other = (Veiculo) obj;

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

        return Objects.equals(this.id,other.id)  && Objects.equals(this.filial, other.filial) && Objects.equals(this.status, other.status) && Objects.equals(this.categoria, other.categoria) && Objects.equals(this.kmTotal, other.kmTotal) && Objects.equals(this.nome, other.nome) && Objects.equals(this.preco, other.preco);
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
