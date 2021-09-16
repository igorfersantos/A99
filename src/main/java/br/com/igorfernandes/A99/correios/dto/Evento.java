
package br.com.igorfernandes.A99.correios.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evento {

    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("dtHrCriado")
    @Expose
    private String dtHrCriado;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("unidade")
    @Expose
    private Unidade unidade;
    @SerializedName("unidadeDestino")
    @Expose
    private Unidade unidadeDestino;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("icone")
    @Expose
    private String icone;
    @SerializedName("descricaoFrontEnd")
    @Expose
    private String descricaoFrontEnd;
    @SerializedName("finalizador")
    @Expose
    private String finalizador;
    @SerializedName("rota")
    @Expose
    private String rota;
    @SerializedName("descricaoWeb")
    @Expose
    private String descricaoWeb;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDtHrCriado() {
        return dtHrCriado;
    }

    public void setDtHrCriado(String dtHrCriado) {
        this.dtHrCriado = dtHrCriado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidadeDestino() {
        return unidadeDestino;
    }

    public void setUnidadeDestino(Unidade unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDescricaoFrontEnd() {
        return descricaoFrontEnd;
    }

    public void setDescricaoFrontEnd(String descricaoFrontEnd) {
        this.descricaoFrontEnd = descricaoFrontEnd;
    }

    public String getFinalizador() {
        return finalizador;
    }

    public void setFinalizador(String finalizador) {
        this.finalizador = finalizador;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getDescricaoWeb() {
        return descricaoWeb;
    }

    public void setDescricaoWeb(String descricaoWeb) {
        this.descricaoWeb = descricaoWeb;
    }

}
