/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotina;

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
public class UploadAofPortal extends Thread{
 Coletas coletas = new Coletas();   
 
 
 @Override
 public void run(){
   
   
     
     
   criarLista();  
//   if(listaOficio.size()<=0){
//       JOptionPane.showMessageDialog(null, "Não há arquivos no banco de dados para carregar no portal jurídico");
//       return;
//   }
   
   if(driver==null){
       coletas.autenticarUsuario();
   }
   
     try {
         lerLista();
     } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
     }
   
   JOptionPane.showMessageDialog(null, "Fim de rotina!!");
     
 }
 
  private void criarLista() {
        
//       listaOficio = new ArrayList<>();
//       
//       listaOficio = uploadDAO.buscar();





  }
    
 
    
    
    
    
    public void lerLista() throws Exception{
        
      
       
       
//        for (Oficio oficio : listaOficio) {
//            
//            carregarPortalEnvioContinuado(oficio);
//            
//        }
//        
        
        
    }

//    private void carregarPortalEnvioContinuado(Oficio oficio) throws Exception {
        
       JavascriptExecutor js = (JavascriptExecutor)driver;
      
//       coletas.setURL("https://juridico.intranet.bb.com.br/wfj/oficio/triagem/cumprimentoContinuado/listar");
//       coletas.aguardaElementoTelaByID("triagemForm:tipoDocumentoDecorate:tipoDocumentoListBox");
//       
//       coletas.procuraElementoPorId(driver, "triagemForm:anoRastreamento", oficio.getAof().subSequence(0, 4).toString());
//       coletas.procuraElementoPorId(driver, "triagemForm:sequencialRastreamento", oficio.getAof().subSequence(4, oficio.getAof().length()).toString());
//       
//       coletas.clickElementId(driver, "triagemForm:btFiltrarTriagem");






       
//       
//       String msg = null;
//       boolean b = coletas.isElementPresentXpath(driver, ".//*[@id='thisForm:upload:fileItems']/table/tbody/tr/td[1]/div[3]");
//    
//        if(b){
//          msg = coletas.lerValorElementoByXpth(".//*[@id='thisForm:upload:fileItems']/table/tbody/tr/td[1]/div[3]");
//        } else{
//            return;
//        }
//       
//       
//        while(!msg.equals("Enviado com Sucesso!")){
//            coletas.pausar(1000);
//            msg = coletas.lerValorElementoByXpth(".//*[@id='thisForm:upload:fileItems']/table/tbody/tr/td[1]/div[3]");
//        }
//        
//        if(msg.equals("Enviado com Sucesso!")){
//            oficio.setObs(msg);
//            oficio.setStatus("Enviado");
//            oficio.setDataEnvio(Utils.getDataAtualFormatoMysql());
//           
//            
//        }
//        
//        uploadDAO.salvar(oficio);
//        
//       
//    }

   
}
