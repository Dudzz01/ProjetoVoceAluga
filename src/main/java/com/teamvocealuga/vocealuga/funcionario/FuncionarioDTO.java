package funcionario;

import filial.Filial;



import java.util.Objects;

public class FuncionarioDTO
{
        private Long id;


        private Filial filial;


        private String nome;


        private String cpf;


        private String funcao;

        private String password;


        private int status; // 0: desativado 1: ativado 2: ferias

    public FuncionarioDTO()
    {

    }

    public FuncionarioDTO(Long id, Filial filial, String nome, String cpf, String funcao, String password, int status)
    {
        this.id = id;
        this.filial = filial;
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.password = password;
        this.status = status;
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

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getPassword()
        {
            return password;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Funcionario converterDtoParaFuncionario()
        {
            Funcionario funcionario = new Funcionario(id,filial,nome,cpf,funcao,password,status);
            return funcionario;
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

            FuncionarioDTO other = (FuncionarioDTO) obj;

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

            return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome) && Objects.equals(this.cpf, other.cpf) && Objects.equals(this.funcao, other.funcao) && Objects.equals(this.password, other.password)&& Objects.equals(this.status, other.status);
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
