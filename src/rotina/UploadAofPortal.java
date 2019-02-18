/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotina;

import dao.RespostaDAO;
import entidade.DataExecucao;
import entidade.Documento;
import entidade.Resposta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openqa.selenium.JavascriptExecutor;
import static rotina.Coletas.driver;
import util.Utils;

/**
 *
 * @author f5078775
 */
public class UploadAofPortal{
 Coletas coletas = new Coletas();   
 RespostaDAO  respostaDAO = new RespostaDAO();
 List<DataExecucao> listaDataExecucao = new ArrayList<>();
 List<Resposta> listaResposta = new ArrayList<>();
 List<Documento> listaDocumento = new ArrayList<>();
 

 public void iniciar(){
   
   
     
     
   criarLista();  
   if(listaDataExecucao.size()<=0){
       JOptionPane.showMessageDialog(null, "Não há arquivos no banco de dados para carregar no portal jurídico");
       return;
   }
   
//   if(driver==null){
//       coletas.autenticarUsuario();
//   }
   
     try {
         lerLista();
     } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
     }
   
     for (DataExecucao dataExecucao : listaDataExecucao) {
         
         respostaDAO.salvar(dataExecucao);
     }
     
     
     
   JOptionPane.showMessageDialog(null, "Fim de rotina!!");
     
 }
 
  private void criarLista() {
        
listaDataExecucao =  respostaDAO.buscar();





  }
    
 
    
    
    
    
    public void lerLista() throws Exception{
        
      
        for (DataExecucao dataExecucao : listaDataExecucao) {
            listaResposta = dataExecucao.getRespostas();
                for (Resposta resposta : listaResposta) {
                    
                    if(listaResposta.get(listaResposta.size() -1 ).equals(resposta)){
                        resposta.getDataDeExecucao().setRealizada("ENVIADO");
                    }
                    
                    
                    
                    if(resposta.getEnviadoPortal()==null && resposta.getListaDocumentos().size()>0 ){
                          carregarPortalEnvioContinuado(resposta);
                    }
                    
                  
            }
            
        }
       

        
        
    }

    private void carregarPortalEnvioContinuado(Resposta resposta) throws Exception {
        listaDocumento = resposta.getListaDocumentos();
       JavascriptExecutor js = (JavascriptExecutor)driver;
      

       
        for (Documento documento : listaDocumento) {
            if( documento.getNomeDocumento().contains(".DOC")){
               continue;
            }
            
            if(documento.getSitDocumento()==null){
                 processaPortal(documento);
            }
           
            
          if(listaDocumento.get(listaDocumento.size() -1 ).equals(documento)){// verifica se o objeto é o ultimo da lista
            documento.getResposta().setObs(resposta.getQtdDoc() + " Documentos enviados");
            documento.getResposta().setEnviadoPortal("SIM");
          }
        }





       
     
       
    }

    private void processaPortal(Documento documento) {
//       coletas.setURL("https://juridico.intranet.bb.com.br/wfj/oficio/triagem/cumprimentoContinuado/listar");
//       coletas.aguardaElementoTelaByID("triagemForm:tipoDocumentoDecorate:tipoDocumentoListBox");

documento.setSitDocumento("processado");

    }

   
}
