/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author f5078775
 */

@Entity
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nm_documento")
    private String nomeDocumento;
    @Column(name = "caminho_doc",length = 300)
    private String caminhoDocumento;
    @Column(name = "sit_doc")
    private String sitDocumento;
    
    @ManyToOne
    @JoinColumn(name = "id_resposta", referencedColumnName = "id")
    private Resposta resposta;

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
     * @return the nomeDocumento
     */
    public String getNomeDocumento() {
        return nomeDocumento;
    }

    /**
     * @param nomeDocumento the nomeDocumento to set
     */
    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    /**
     * @return the resposta
     */
    public Resposta getResposta() {
        return resposta;
    }

    /**
     * @param resposta the resposta to set
     */
    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    /**
     * @return the caminhoDocumento
     */
    public String getCaminhoDocumento() {
        return caminhoDocumento;
    }

    /**
     * @param caminhoDocumento the caminhoDocumento to set
     */
    public void setCaminhoDocumento(String caminhoDocumento) {
        this.caminhoDocumento = caminhoDocumento;
    }

    /**
     * @return the sitDocumento
     */
    public String getSitDocumento() {
        return sitDocumento;
    }

    /**
     * @param sitDocumento the sitDocumento to set
     */
    public void setSitDocumento(String sitDocumento) {
        this.sitDocumento = sitDocumento;
    }
    
    
}
