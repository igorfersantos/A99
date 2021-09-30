
package br.com.igorfernandes.A99.provider.correios.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unidade {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("codSro")
    @Expose
    private String codSro;
    @SerializedName("codMcu")
    @Expose
    private String codMcu;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("uf")
    @Expose
    private String uf;
    @SerializedName("cidade")
    @Expose
    private String cidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodSro() {
        return codSro;
    }

    public void setCodSro(String codSro) {
        this.codSro = codSro;
    }

    public String getCodMcu() {
        return codMcu;
    }

    public void setCodMcu(String codMcu) {
        this.codMcu = codMcu;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
