package com.teamvocealuga.vocealuga.funcionario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.locacao.Locacao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Funcionario.FUNCIONARIO_TABLE_NAME)
public class Funcionario
{
    public static final String FUNCIONARIO_TABLE_NAME = "funcionario";

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
    private List<Locacao> locacaoList = new ArrayList<Locacao>();

    @Column(name = "nome",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "cpf",unique = true,nullable = false)
    @NotNull
    @NotEmpty
    @JsonSerialize(using = ToStringSerializer.class)
    private String cpf;

    @Column(name = "funcao",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private String funcao;

    @Column(name = "password",unique = false, nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @Column(name = "status",unique = false,nullable = false)
    @NotNull
    @NotEmpty
    private int status; // 0: desativado 1: ativado 2: ferias

    public Funcionario()
    {

    }

    public Funcionario(Long id, Filial filial,List<Locacao> locacaoList, String nome, String cpf, String funcao,String password, int status)
    {
        this.id = id;
        this.filial = filial;
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.password = password;
        this.status = status;
        this.locacaoList = locacaoList;
    }

    public Funcionario(FuncionarioDTO funcionarioDTO)
    {
        this.id = funcionarioDTO.getId();
        this.filial = funcionarioDTO.getFilial();
        this.nome = funcionarioDTO.getNome();
        this.cpf = funcionarioDTO.getCpf();
        this.funcao = funcionarioDTO.getFuncao();
        this.password = funcionarioDTO.getPassword();
        this.status = funcionarioDTO.getStatus();
        this.locacaoList = funcionarioDTO.getLocacaoList();
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIgnore
    public List<Locacao> getLocacaoList() {
        return locacaoList;
    }
    @JsonIgnore
    public void setLocacaoList(List<Locacao> locacaoList) {
        this.locacaoList = locacaoList;
    }

    public FuncionarioDTO converterFuncionarioParaDTO()
    {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(this);
        return funcionarioDTO;
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

        Funcionario other = (Funcionario) obj;

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

        return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf) && Objects.equals(this.funcao, other.funcao) && Objects.equals(this.password, other.password) && Objects.equals(this.status, other.status) && Objects.equals(this.filial, other.filial);
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
