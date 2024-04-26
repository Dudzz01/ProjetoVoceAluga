package com.teamvocealuga.vocealuga.veiculo;

import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;


import java.util.Objects;

public class VeiculoDTO
{
    private Long id;





    private Filial filial;


    private String status;


    private String categoria;


    private double kmTotal;

    private String nome;

    public VeiculoDTO()
    {

    }

    public VeiculoDTO(Long id, Filial filial, String nome,String status, String categoria, double kmTotal) {
        this.id = id;

        this.filial = filial;
        this.status = status;
        this.categoria = categoria;
        this.kmTotal = kmTotal;
        this.nome = nome;
    }

    public VeiculoDTO(Veiculo veiculo)
    {
        this.id = veiculo.getId();

        this.filial = veiculo.getFilial();
        this.status = veiculo.getStatus();
        this.categoria = veiculo.getCategoria();
        this.kmTotal = veiculo.getKmTotal();
        this.nome = veiculo.getNome();
    }

    public Veiculo converterDTOParaVeiculo()
    {
        return new Veiculo(this);
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

        VeiculoDTO other = (VeiculoDTO) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.filial, other.filial) && Objects.equals(this.status, other.status) && Objects.equals(this.categoria, other.categoria) && Objects.equals(this.kmTotal, other.kmTotal)  && Objects.equals(this.nome, other.nome);
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
