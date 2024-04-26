package com.teamvocealuga.vocealuga.locacao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.configs.CustomDateSerializer;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.motorista.Motorista;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class LocacaoDTO
{
    public static final String LOCACAO_TABLE_NAME = "locacao";


    private Long id;


    private Motorista motorista;


    private Funcionario funcionario;


    private Cliente cliente;


    private Veiculo veiculo;


    private int codLocacao;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dataLocacao;


    private String categoriaVeiculo;



    private String cnhMotorista;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dataPedido;


    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dataInicio;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dataFim;


    private String  enderecoRetirada;


    private String  enderecoDevolucao;


    private String statusLocacao;


    private boolean contratoLocacao;

    public LocacaoDTO()
    {

    }

    public LocacaoDTO(Long id, Motorista motorista, Funcionario funcionario, Cliente cliente, Veiculo veiculo, int codLocacao, Date dataLocacao, String categoriaVeiculo, String cnhMotorista, Date dataPedido, Date dataInicio, Date dataFim, String enderecoRetirada, String enderecoDevolucao, String statusLocacao, boolean contratoLocacao)
    {
        this.id = id;
        this.motorista = motorista;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.codLocacao = codLocacao;
        this.dataLocacao = dataLocacao;
        this.categoriaVeiculo = categoriaVeiculo;
        this.cnhMotorista = cnhMotorista;
        this.dataPedido = dataPedido;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.enderecoRetirada = enderecoRetirada;
        this.enderecoDevolucao = enderecoDevolucao;
        this.statusLocacao = statusLocacao;
        this.contratoLocacao = contratoLocacao;
    }

    public LocacaoDTO(Locacao locacao)
    {
        this.id = locacao.getId();
        this.motorista = locacao.getMotorista();
        this.funcionario = locacao.getFuncionario();
        this.cliente = locacao.getCliente();
        this.veiculo = locacao.getVeiculo();
        this.codLocacao = locacao.getCodLocacao();
        this.dataLocacao = locacao.getDataLocacao();
        this.categoriaVeiculo = locacao.getCategoriaVeiculo();
        this.cnhMotorista = locacao.getCnhMotorista();
        this.dataPedido = locacao.getDataPedido();
        this.dataInicio = locacao.getDataInicio();
        this.dataFim = locacao.getDataFim();
        this.enderecoRetirada = locacao.getEnderecoRetirada();
        this.enderecoDevolucao = locacao.getEnderecoDevolucao();
        this.statusLocacao = locacao.getStatusLocacao();
        this.contratoLocacao = locacao.getContratoLocacao();
    }

    public Locacao converterDtoParaLocacao()
    {
        Locacao locacao = new Locacao(this);
        return locacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(int codLocacao) {
        this.codLocacao = codLocacao;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public String getCnhMotorista() {
        return cnhMotorista;
    }

    public void setCnhMotorista(String cnhMotorista) {
        this.cnhMotorista = cnhMotorista;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getEnderecoRetirada() {
        return enderecoRetirada;
    }

    public void setEnderecoRetirada(String enderecoRetirada) {
        this.enderecoRetirada = enderecoRetirada;
    }

    public String getEnderecoDevolucao() {
        return enderecoDevolucao;
    }

    public void setEnderecoDevolucao(String enderecoDevolucao) {
        this.enderecoDevolucao = enderecoDevolucao;
    }

    public String getStatusLocacao() {
        return statusLocacao;
    }

    public void setStatusLocacao(String statusLocacao) {
        this.statusLocacao = statusLocacao;
    }

    public boolean getContratoLocacao() {
        return contratoLocacao;
    }

    public void setContratoLocacao(boolean contratoLocacao) {
        this.contratoLocacao = contratoLocacao;
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

        LocacaoDTO other = (LocacaoDTO) obj;

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


        return Objects.equals(this.id,other.id) && Objects.equals(this.motorista,other.motorista) && Objects.equals(this.funcionario, other.funcionario) && Objects.equals(this.cliente, other.cliente) && Objects.equals(this.veiculo, other.veiculo) && Objects.equals(this.codLocacao, other.codLocacao) && Objects.equals(this.dataLocacao, other.dataLocacao) && Objects.equals(this.categoriaVeiculo, other.categoriaVeiculo) && Objects.equals(this.cnhMotorista, other.cnhMotorista) && Objects.equals(this.dataPedido, other.dataPedido) && Objects.equals(this.dataInicio, other.dataInicio) && Objects.equals(this.dataFim, other.dataFim) && Objects.equals(this.enderecoRetirada, other.enderecoRetirada) && Objects.equals(this.enderecoDevolucao, other.enderecoDevolucao) && Objects.equals(this.statusLocacao, other.statusLocacao) && Objects.equals(this.contratoLocacao, other.contratoLocacao);
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
