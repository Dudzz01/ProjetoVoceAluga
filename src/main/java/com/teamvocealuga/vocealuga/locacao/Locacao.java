package com.teamvocealuga.vocealuga.locacao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.configs.CustomDateDeserializer;
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

@Entity
@Table(name = Locacao.LOCACAO_TABLE_NAME)
public class Locacao
{
    public static final String LOCACAO_TABLE_NAME = "locacao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(name = "codLocacao",nullable = false, unique = false)
    @NotNull
    private int codLocacao;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "dataLocacao", nullable = false,unique = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataLocacao;

    @Column(name = "categoriaVeiculo", nullable = false, unique = false)
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 255)
    private String categoriaVeiculo;


    @Column(name = "cnhMotorista",nullable = false,unique = false)
    @NotNull
    @NotEmpty
    @Size(min = 0,max = 255)
    private String cnhMotorista;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "dataPedido",nullable = false,unique = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataPedido;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "dataInicio",nullable = false, unique = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataInicio;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "dataFim",nullable = false, unique = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private Date dataFim;

    @Column(name = "enderecoRetirada",nullable = false, unique = false)
    @NotNull
    @NotEmpty
    private String  enderecoRetirada;

    @Column(name = "enderecoDevolucao",nullable = false, unique = false)
    @NotNull
    @NotEmpty
    private String  enderecoDevolucao;

    @Column(name = "statusLocacao",nullable = false,unique = false)
    @NotNull
    @NotEmpty
    private String statusLocacao;

    @Column(name = "contratolocacao",nullable = false,unique = false)
    @NotNull

    private boolean contratoLocacao;

    public Locacao()
    {

    }

    public Locacao(Long id, Motorista motorista, Funcionario funcionario, Cliente cliente, Veiculo veiculo, int codLocacao, Date dataLocacao, String categoriaVeiculo, String cnhMotorista, Date dataPedido, Date dataInicio, Date dataFim, String enderecoRetirada, String enderecoDevolucao, String statusLocacao, boolean contratoLocacao)
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

    public Locacao(LocacaoDTO locacaoDTO)
    {
        this.id = locacaoDTO.getId();
        this.motorista = locacaoDTO.getMotorista();
        this.funcionario = locacaoDTO.getFuncionario();
        this.cliente = locacaoDTO.getCliente();
        this.veiculo = locacaoDTO.getVeiculo();
        this.codLocacao = locacaoDTO.getCodLocacao();
        this.dataLocacao = locacaoDTO.getDataLocacao();
        this.categoriaVeiculo = locacaoDTO.getCategoriaVeiculo();
        this.cnhMotorista = locacaoDTO.getCnhMotorista();
        this.dataPedido = locacaoDTO.getDataPedido();
        this.dataInicio = locacaoDTO.getDataInicio();
        this.dataFim = locacaoDTO.getDataFim();
        this.enderecoRetirada = locacaoDTO.getEnderecoRetirada();
        this.enderecoDevolucao = locacaoDTO.getEnderecoDevolucao();
        this.statusLocacao = locacaoDTO.getStatusLocacao();
        this.contratoLocacao = locacaoDTO.getContratoLocacao();
    }

    public LocacaoDTO converterLocacaoParaDTO()
    {
        LocacaoDTO locacaoDTO = new LocacaoDTO(this);
        return locacaoDTO;
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

        Locacao other = (Locacao) obj;

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
