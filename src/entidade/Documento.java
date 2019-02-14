/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    Integer id;
    @Column(name = "nm_documento")
    String nomeDocumento;
    
    @ManyToOne
    Resposta resposta;
    
    
}
