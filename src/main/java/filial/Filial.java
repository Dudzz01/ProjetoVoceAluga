package filial;



import cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import funcionario.Funcionario;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Filial.FILIAL_TABLE_NAME)
public class Filial
{
    public static final String FILIAL_TABLE_NAME = "filial";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",unique = false,nullable = false,length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 0,max = 255)
    private String nome;

    @Column(name = "cnpj",unique = true, nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 255)
    private String cnpj;

    @Column(name = "endereco", unique = false, nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 255)
    private String endereco;

    @OneToMany(mappedBy = "filial")
    private List<Funcionario> funcionarioList = new ArrayList<Funcionario>();

    @OneToMany(mappedBy = "filial")
    private List<Cliente> clienteList = new ArrayList<Cliente>();


    public Filial()
    {

    }
    public Filial(Long id, String nome, String cnpj, String endereco)
    {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Filial(Long id, String nome, String cnpj, String endereco, List<Funcionario> funcionarioList, List<Cliente> clienteList)
    {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.funcionarioList = funcionarioList;
        this.clienteList = clienteList;
    }

    public Filial(FilialDTO filialDTO)
    {
        this.id = filialDTO.getId();
        this.nome = filialDTO.getNome();
        this.cnpj = filialDTO.getCnpj();
        this.endereco = filialDTO.getEndereco();
        this.funcionarioList = filialDTO.getFuncionarioList();
        this.clienteList = filialDTO.getClienteList();
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

    public FilialDTO converterFilialParaDto()
    {
        FilialDTO filialDTO = new FilialDTO(id,nome,cnpj,endereco,funcionarioList,clienteList);
        return filialDTO;
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

        Filial other = (Filial) obj;

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
