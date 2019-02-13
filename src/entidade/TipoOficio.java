/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author f5078775
 */
@Entity
@Table(name = "tb_tip_envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOficio.findAll", query = "SELECT t FROM TipoOficio t")
    , @NamedQuery(name = "TipoOficio.findByCdTipEnvio", query = "SELECT t FROM TipoOficio t WHERE t.cdTipEnvio = :cdTipEnvio")
    , @NamedQuery(name = "TipoOficio.findByNmTipEnvio", query = "SELECT t FROM TipoOficio t WHERE t.nmTipEnvio = :nmTipEnvio")})
public class TipoOficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_tip_envio")
    private Integer cdTipEnvio;
    @Size(max = 100)
    @Column(name = "nm_tip_envio")
    private String nmTipEnvio;
    @OneToMany(mappedBy = "cdTipoEnvio")
    private List<Oficio> oficioList;

    public TipoOficio() {
    }

    public TipoOficio(Integer cdTipEnvio) {
        this.cdTipEnvio = cdTipEnvio;
    }

    public Integer getCdTipEnvio() {
        return cdTipEnvio;
    }

    public void setCdTipEnvio(Integer cdTipEnvio) {
        this.cdTipEnvio = cdTipEnvio;
    }

    public String getNmTipEnvio() {
        return nmTipEnvio;
    }

    public void setNmTipEnvio(String nmTipEnvio) {
        this.nmTipEnvio = nmTipEnvio;
    }

    @XmlTransient
    public List<Oficio> getOficioList() {
        return oficioList;
    }

    public void setOficioList(List<Oficio> oficioList) {
        this.oficioList = oficioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipEnvio != null ? cdTipEnvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOficio)) {
            return false;
        }
        TipoOficio other = (TipoOficio) object;
        if ((this.cdTipEnvio == null && other.cdTipEnvio != null) || (this.cdTipEnvio != null && !this.cdTipEnvio.equals(other.cdTipEnvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.TipoOficio[ cdTipEnvio=" + cdTipEnvio + " ]";
    }
    
}
