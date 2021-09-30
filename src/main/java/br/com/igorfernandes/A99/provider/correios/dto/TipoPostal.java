
package br.com.igorfernandes.A99.provider.correios.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoPostal {

    @SerializedName("sigla")
    @Expose
    private String sigla;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("categoria")
    @Expose
    private String categoria;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
