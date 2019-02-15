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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.Utils;

/**
 *
 * @author f5078775
 */
public class UploadAOF {

    RespostaDAO uploadDAO = new RespostaDAO();
    RespostaDAO respostaDAO = new RespostaDAO();
    List<DataExecucao> listaDataExecucao = new ArrayList<>();
    List<Resposta> listaResposta = new ArrayList<>();

    public void iniciar(String diretorio) throws IOException, InterruptedException {
        createList();
        lerLista(diretorio);
        gravarDados();
    }

    public void createList() {// método cria a lista com  as tarefas a serem executadas

        listaDataExecucao = respostaDAO.buscar();

        for (DataExecucao dataExecucao : listaDataExecucao) {
            listaResposta = dataExecucao.getRespostas();
        }
    }

    public void lerLista(String diretorio) throws IOException, InterruptedException {

        for (Resposta resposta : listaResposta) {
            lerDiretorio(diretorio, resposta);

        }
        

    }
    
    
     private void gravarDados() {

          for (DataExecucao dataExecucao : listaDataExecucao) {
            respostaDAO.salvar(dataExecucao);
           
     }
     }
    
    
    
    

    public void lerDiretorio(String diretorio, Resposta resposta) throws IOException, InterruptedException {

        File pasta = new File(diretorio);
        File afile[] = pasta.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {  // percorrer pasta inicial
            File pastas = afile[i];

            if (afile[i].isDirectory()) {
                String subDiretorio = pastas.getName();
                subDiretorio = pastas.getName();

                if (Utils.tratarVariavel(subDiretorio).equals(Utils.tratarVariavel(resposta.getAof()))) {
                  
                    String caminhoPastaSubpasta = diretorio + "\\" + subDiretorio;
                    caminhoPastaSubpasta = diretorio + "\\" + subDiretorio;

                    File subPasta = new File(caminhoPastaSubpasta);
                    File afilesubpasta[] = subPasta.listFiles();

                    int l = 0;
                    for (int k = afilesubpasta.length; l < k; l++) {
                        File conteudo = afilesubpasta[l];
                        String nomeConteudo = conteudo.getName();

                        String caminhoCompleto = caminhoPastaSubpasta + "\\" + nomeConteudo  ;
                        
                        
                        if (afilesubpasta[l].isDirectory()) {
                            

                                            File subsubPasta = new File(caminhoCompleto);
                                            File afilesubsubpasta[] = subsubPasta.listFiles();

                            
                            
                            
                                        String subsubDiretorio = pastas.getName();
                                        subsubDiretorio = pastas.getName();
                                        
                                        int m = 0;
                                        
                                        for(int y = afilesubsubpasta.length;m<y;m++){
                                            Documento documento = new Documento();
                                             conteudo = afilesubsubpasta[m];
                                             nomeConteudo = conteudo.getName();
                                             documento.setNomeDocumento(nomeConteudo);
                                             documento.setCaminhoDocumento(conteudo.toString());
                                             resposta.adicionarDocumento(documento);
                                             
                                            
                                        }
                                        
                        }
                        
                        
                        

                      

                    }

                }

            }

        }

    }

   

}
