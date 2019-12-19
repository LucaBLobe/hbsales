package br.com.hbsis.funcionario;

public class FuncionarioDTO {

    private Long id;
    private String nomeFuncionario;
    private String email;
    private String uuid;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nomeFuncionario, String email, String uuid) {
    }

    public static FuncionarioDTO of(Funcionario funcionario) {
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNomeFuncionario(),
                funcionario.getEmail(),
                funcionario.getUuid()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) { nomeFuncionario = nomeFuncionario; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", Nome Funcionario ='" + nomeFuncionario + '\'' +
                ", email='" + email + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }


}