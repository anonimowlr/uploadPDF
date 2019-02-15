/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.DataExecucao;
import entidade.Resposta;
import java.util.List;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;

/**
 *
 * @author f5078775
 */
public class RespostaDAO {
    EntityManager em = EntityManagerUtil.getEntityManager();
    
    
    
    
    public boolean salvar(DataExecucao dataExecucao) {

        
        
        try{
        em.getTransaction().begin();
        if(dataExecucao.getId()==null){
            em.persist(dataExecucao);
        }else{
            em.merge(dataExecucao);
        }
        em.getTransaction().commit();
        return true;
        }catch(Exception e){
            if(em.getTransaction().isActive()== false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            return false;
        }

    }

    public List<DataExecucao> buscar() {
     return   em.createQuery("From DataExecucao c where c.realizada = null").getResultList();



    }
    
}
