/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author f5078775
 */
@Entity
public class DataExecucao implements Serializable{
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "dt_exec")
    @Temporal(TemporalType.DATE)
    private Calendar dataExec;
    @Column(name = "realizado")
    private Boolean realizada;
    
    @OneToMany(mappedBy ="dataDeExecucao",cascade =CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();
    
    
    
    
    
    
    public void adicionarResposta(Resposta resposta){
       resposta.setDataDeExecucao(this);
       respostas.add(resposta);
        
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
     * @return the dataExecucao
     */

    /**
     * @return the realizada
     */
    public Boolean getRealizada() {
        return realizada;
    }

    /**
     * @param realizada the realizada to set
     */
    public void setRealizada(Boolean realizada) {
        this.realizada = realizada;
    }

    /**
     * @return the respostas
     */
    public List<Resposta> getRespostas() {
        return respostas;
    }

    /**
     * @param respostas the respostas to set
     */
    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    /**
     * @return the dataExec
     */
    public Calendar getDataExec() {
        return dataExec;
    }

    /**
     * @param dataExec the dataExec to set
     */
    public void setDataExec(Calendar dataExec) {
        this.dataExec = dataExec;
    }

    /**
     * @return the resposta
     */
   

    /**
     * @param resposta the resposta to set
     */
   
    
}
