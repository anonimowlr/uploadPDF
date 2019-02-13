/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Oficio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import jpa.EntityManagerUtil;

/**
 *
 * @author f5078775
 */
public class UploadDAO implements  Serializable{
EntityManager em;
    public UploadDAO() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    
    
    
    public void salvar(Oficio uploadOficio){
        
        
       
        
        try{
              em.getTransaction().begin();
              em.persist(uploadOficio);
              em.getTransaction().commit();
            
        }catch(Exception e ){
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Erro ao salvar  nome de arquivo no banco arquivo" + e);
        }
      
        
        
    }
    
    
    
    
    
    
    public List<Oficio> buscar(){
        
        
        List<Oficio> listaOficio= new ArrayList<>();
        
        listaOficio = em.createQuery("From Oficio oficio").getResultList();
        
        
        return listaOficio;
    }
    
    
    
    
    
    
}
