package com.teamvocealuga.vocealuga.filial;



import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class FilialDTO
{
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
    private List<Cliente> clienteList = new ArrayList<Cliente>();

    private List<Veiculo> veiculo = new ArrayList<Veiculo>();

    public FilialDTO()
    {

    }
    public FilialDTO(Long id, String nome, String cnpj, String endereco)
    {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
    public FilialDTO(Long id, String nome, String cnpj, String endereco,List<Funcionario> funcionarioList, List<Cliente> clienteList,List<Veiculo> veiculo)
    {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.funcionarioList = funcionarioList;
        this.clienteList = clienteList;
        this.veiculo = veiculo;
    }
    public FilialDTO(Optional<Filial> filial)
    {
        this.id = filial.get().getId();
        this.nome = filial.get().getNome();
        this.cnpj = filial.get().getCnpj();
        this.endereco = filial.get().getEndereco();
        this.funcionarioList = filial.get().getFuncionarioList();
        this.clienteList = filial.get().getClienteList();
        this.veiculo = filial.get().getVeiculo();
    }

    public FilialDTO(Filial filial)
    {
        this.id = filial.getId();
        this.nome = filial.getNome();
        this.cnpj = filial.getCnpj();
        this.endereco = filial.getEndereco();
        this.funcionarioList = filial.getFuncionarioList();
        this.clienteList = filial.getClienteList();
        this.veiculo = filial.getVeiculo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @JsonIgnore
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @JsonIgnore
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @JsonIgnore
    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }

    public Filial converterDtoParaFilial()
    {
        Filial filial = new Filial(id,nome,cnpj,endereco,funcionarioList,clienteList, veiculo);
        return filial;
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

        FilialDTO other = (FilialDTO) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cnpj, other.cnpj) && Objects.equals(this.endereco, other.endereco);
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
