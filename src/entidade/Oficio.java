/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author f5078775
 */
@Entity
@Table(name = "tb_oficio_upload")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficio.findAll", query = "SELECT o FROM Oficio o")
    , @NamedQuery(name = "Oficio.findById", query = "SELECT o FROM Oficio o WHERE o.id = :id")
    , @NamedQuery(name = "Oficio.findByNomeArquivo", query = "SELECT o FROM Oficio o WHERE o.nomeArquivo = :nomeArquivo")
    , @NamedQuery(name = "Oficio.findByStatus", query = "SELECT o FROM Oficio o WHERE o.status = :status")
    , @NamedQuery(name = "Oficio.findByDataEnvio", query = "SELECT o FROM Oficio o WHERE o.dataEnvio = :dataEnvio")
    , @NamedQuery(name = "Oficio.findByObs", query = "SELECT o FROM Oficio o WHERE o.obs = :obs")
    , @NamedQuery(name = "Oficio.findByAof", query = "SELECT o FROM Oficio o WHERE o.aof = :aof")})
public class Oficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "nome_arquivo")
    private String nomeArquivo;
    @Size(max = 300)
    @Column(name = "status")
    private String status;
    @Column(name = "data_envio")
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    @Size(max = 300)
    @Column(name = "obs")
    private String obs;
    @Size(max = 45)
    @Column(name = "aof")
    private String aof;
    @JoinColumn(name = "cd_tipo_envio", referencedColumnName = "cd_tip_envio")
    @ManyToOne
    private TipoOficio cdTipoEnvio;

    public Oficio() {
    }

    public Oficio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getAof() {
        return aof;
    }

    public void setAof(String aof) {
        this.aof = aof;
    }

    public TipoOficio getCdTipoEnvio() {
        return cdTipoEnvio;
    }

    public void setCdTipoEnvio(TipoOficio cdTipoEnvio) {
        this.cdTipoEnvio = cdTipoEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficio)) {
            return false;
        }
        Oficio other = (Oficio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Oficio[ id=" + id + " ]";
    }
    
}
