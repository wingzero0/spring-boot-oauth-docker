package kit.personal.ssoserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class GPS {
    @Id
    private Integer funcNo;
    private String passwd;
    private String nome;
    private String nomec;
    private String unorg;
    private String email;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomec() {
        return nomec;
    }
    public void setNomec(String nomec) {
        this.nomec = nomec;
    }
    public Integer getFuncNo() {
        return funcNo;
    }
    public void setFuncNo(Integer funcNo) {
        this.funcNo = funcNo;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getUnorg() {
        return unorg;
    }
    public void setUnorg(String unorg) {
        this.unorg = unorg;
    }
    public String getEmail() {
        return email;
    }
    public GPS setEmail(String email) {
        this.email = email;
        return this;
    }
}