package br.com.igorfernandes.A99.provider.correios.dto;

import br.com.igorfernandes.A99.view.shipping.IShipping;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonAdapter(CorreiosShippingStatusDeserializer.class)
public class CorreiosShippingStatus implements IShipping {

    @SerializedName("codObjeto")
    @Expose
    private String codObjeto;
    @SerializedName("tipoPostal")
    @Expose
    private TipoPostal tipoPostal;
    @SerializedName("dtPrevista")
    @Expose
    private String dtPrevista;
    @SerializedName("modalidade")
    @Expose
    private String modalidade;
    @SerializedName("eventos")
    @Expose
    private List<Evento> eventos = null;
    @SerializedName("situacao")
    @Expose
    private String situacao;
    @SerializedName("autoDeclaracao")
    @Expose
    private Boolean autoDeclaracao;
    @SerializedName("encargoImportacao")
    @Expose
    private Boolean encargoImportacao;
    @SerializedName("percorridaCarteiro")
    @Expose
    private Boolean percorridaCarteiro;
    @SerializedName("bloqueioObjeto")
    @Expose
    private Boolean bloqueioObjeto;
    @SerializedName("arEletronico")
    @Expose
    private Boolean arEletronico;

    public CorreiosShippingStatus() {
    }

    public CorreiosShippingStatus(String codObjeto, TipoPostal tipoPostal, String dtPrevista, String modalidade,
                                  List<Evento> eventos, String situacao, Boolean autoDeclaracao,
                                  Boolean encargoImportacao, Boolean percorridaCarteiro, Boolean bloqueioObjeto,
                                  Boolean arEletronico) {
        this.codObjeto = codObjeto;
        this.tipoPostal = tipoPostal;
        this.dtPrevista = dtPrevista;
        this.modalidade = modalidade;
        this.eventos = eventos;
        this.situacao = situacao;
        this.autoDeclaracao = autoDeclaracao;
        this.encargoImportacao = encargoImportacao;
        this.percorridaCarteiro = percorridaCarteiro;
        this.bloqueioObjeto = bloqueioObjeto;
        this.arEletronico = arEletronico;
    }

    public String getCodObjeto() {
        return codObjeto;
    }

    public void setCodObjeto(String codObjeto) {
        this.codObjeto = codObjeto;
    }

    public TipoPostal getTipoPostal() {
        return tipoPostal;
    }

    public void setTipoPostal(TipoPostal tipoPostal) {
        this.tipoPostal = tipoPostal;
    }

    public String getDtPrevisaoEntrega() {
        return dtPrevista;
    }

    public void setDtPrevisaoEntrega(String dtPrevisaoEntrega) {
        this.dtPrevista = dtPrevisaoEntrega;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Boolean getAutoDeclaracao() {
        return autoDeclaracao;
    }

    public void setAutoDeclaracao(Boolean autoDeclaracao) {
        this.autoDeclaracao = autoDeclaracao;
    }

    public Boolean getEncargoImportacao() {
        return encargoImportacao;
    }

    public void setEncargoImportacao(Boolean encargoImportacao) {
        this.encargoImportacao = encargoImportacao;
    }

    public Boolean getPercorridaCarteiro() {
        return percorridaCarteiro;
    }

    public void setPercorridaCarteiro(Boolean percorridaCarteiro) {
        this.percorridaCarteiro = percorridaCarteiro;
    }

    public Boolean getBloqueioObjeto() {
        return bloqueioObjeto;
    }

    public void setBloqueioObjeto(Boolean bloqueioObjeto) {
        this.bloqueioObjeto = bloqueioObjeto;
    }

    public Boolean getArEletronico() {
        return arEletronico;
    }

    public void setArEletronico(Boolean arEletronico) {
        this.arEletronico = arEletronico;
    }
}
