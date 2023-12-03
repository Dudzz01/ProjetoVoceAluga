package com.teamvocealuga.vocealuga.devolucao;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.locacao.Locacao;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = Devolucao.DEVOLUCAO_TABLE_NAME)
public class Devolucao
{
    public static final String DEVOLUCAO_TABLE_NAME = "devolucao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @Column(name = "datapedido",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private Date dataPedido;

    @Column(name = "datadevolucao",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private Date dataDevolucao;

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
