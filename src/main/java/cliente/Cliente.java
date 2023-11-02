package cliente;

import filial.Filial;

import javax.persistence.*;

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
}
