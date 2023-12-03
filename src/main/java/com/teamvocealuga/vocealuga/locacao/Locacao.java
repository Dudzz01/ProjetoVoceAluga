package com.teamvocealuga.vocealuga.locacao;

import com.teamvocealuga.vocealuga.cliente.Cliente;
import com.teamvocealuga.vocealuga.funcionario.Funcionario;
import com.teamvocealuga.vocealuga.motorista.Motorista;
import com.teamvocealuga.vocealuga.transacao.Transacao;
import com.teamvocealuga.vocealuga.veiculo.Veiculo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Locacao.LOCACAO_TABLE_NAME)
public class Locacao
{
    public static final String LOCACAO_TABLE_NAME = "locacao";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(mappedBy = "locacao")
    private Veiculo veiculo;


}
