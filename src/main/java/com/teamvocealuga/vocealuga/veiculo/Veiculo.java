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

    @OneToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @Column(name = "status",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String status;

    @Column(name = "categoria",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String categoria;

    @Column(name = "kmtotal",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private double kmTotal;

    public Veiculo()
    {

    }

    public Veiculo(Long id, Locacao locacao, Filial filial, String status, String categoria, double kmTotal) {
        this.id = id;
        this.locacao = locacao;
        this.filial = filial;
        this.status = status;
        this.categoria = categoria;
        this.kmTotal = kmTotal;
    }

    public Veiculo(VeiculoDTO veiculoDTO)
    {
        this.id = veiculoDTO.getId();
        this.locacao = veiculoDTO.getLocacao();
        this.filial = veiculoDTO.getFilial();
        this.status = veiculoDTO.getStatus();
        this.categoria = veiculoDTO.getCategoria();
        this.kmTotal = veiculoDTO.getKmTotal();
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

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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

        return Objects.equals(this.id,other.id) && Objects.equals(this.locacao,other.locacao) && Objects.equals(this.filial, other.filial) && Objects.equals(this.status, other.status) && Objects.equals(this.categoria, other.categoria) && Objects.equals(this.kmTotal, other.kmTotal);
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
