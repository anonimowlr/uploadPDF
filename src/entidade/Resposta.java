/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author f5078775
 */


@Entity
public class Resposta {
   
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(name = "nm_msgm")
   private String mensagemResposta;
   @Column(name = "aof")
   private String aof;
   @Column(name = "dt_reagendamento")
   @Temporal(TemporalType.DATE)
   private Calendar dataReagendamento;
   @Column(name = "obs", length = 300)
   private String obs;
   
   @Column(name = "dt_imp_txt")
   @Temporal(TemporalType.DATE)
   private Calendar dataImportacaoTxt;
   @Column(name = "dt_pesq_pasta")
   @Temporal(TemporalType.DATE)
   private Calendar dataPesquisaPasta;
   @Column(name = "usu_func")
   private String   funcionarioResponsavel;
   @Column(name = "qtd_documento")
   private Integer qtdDoc;
   
   @Column(name = "env_portal")
   private String enviadoPortal;
   
   
   @ManyToOne
   @JoinColumn(name = "id_data_exec", referencedColumnName = "id")
   private DataExecucao dataDeExecucao;
   
   
   
   @OneToMany(mappedBy = "resposta",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Documento> listaDocumentos = new ArrayList<>() ;
   
   
   
   public void adicionarDocumento(Documento  documento){
       documento.setResposta(this);
        getListaDocumentos().add(documento);
   }
  
   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the mensagemResposta
     */
    public String getMensagemResposta() {
        return mensagemResposta;
    }

    /**
     * @param mensagemResposta the mensagemResposta to set
     */
    public void setMensagemResposta(String mensagemResposta) {
        this.mensagemResposta = mensagemResposta;
    }

    /**
     * @return the aof
     */
    public String getAof() {
        return aof;
    }

    /**
     * @param aof the aof to set
     */
    public void setAof(String aof) {
        this.aof = aof;
    }

    /**
     * @return the dataReagendamento
     */
    public Calendar getDataReagendamento() {
        return dataReagendamento;
    }

    /**
     * @param dataReagendamento the dataReagendamento to set
     */
    public void setDataReagendamento(Calendar dataReagendamento) {
        this.dataReagendamento = dataReagendamento;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the listaDocumentos
     */
    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    /**
     * @param listaDocumentos the listaDocumentos to set
     */
    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    /**
     * @return the dataImportacaoTxt
     */
    public Calendar getDataImportacaoTxt() {
        return dataImportacaoTxt;
    }

    /**
     * @param dataImportacaoTxt the dataImportacaoTxt to set
     */
    public void setDataImportacaoTxt(Calendar dataImportacaoTxt) {
        this.dataImportacaoTxt = dataImportacaoTxt;
    }

    /**
     * @return the dataPesquisaPasta
     */
    public Calendar getDataPesquisaPasta() {
        return dataPesquisaPasta;
    }

    /**
     * @param dataPesquisaPasta the dataPesquisaPasta to set
     */
    public void setDataPesquisaPasta(Calendar dataPesquisaPasta) {
        this.dataPesquisaPasta = dataPesquisaPasta;
    }

    public String getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    /**
     * @param funcionarioResponsavel the funcionarioResponsavel to set
     */
    public void setFuncionarioResponsavel(String funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    /**
     * @return the dataDeExecucao
     */
    public DataExecucao getDataDeExecucao() {
        return dataDeExecucao;
    }

    /**
     * @param dataDeExecucao the dataDeExecucao to set
     */
    public void setDataDeExecucao(DataExecucao dataDeExecucao) {
        this.dataDeExecucao = dataDeExecucao;
    }

    /**
     * @return the qtdDoc
     */
    public Integer getQtdDoc() {
        return qtdDoc;
    }

    /**
     * @param qtdDoc the qtdDoc to set
     */
    public void setQtdDoc(Integer qtdDoc) {
        this.qtdDoc = qtdDoc;
    }

    /**
     * @return the enviadoPortal
     */
    public String getEnviadoPortal() {
        return enviadoPortal;
    }

    /**
     * @param enviadoPortal the enviadoPortal to set
     */
    public void setEnviadoPortal(String enviadoPortal) {
        this.enviadoPortal = enviadoPortal;
    }
   
    
    
}
