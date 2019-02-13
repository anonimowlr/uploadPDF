/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotina;

import dao.UploadDAO;
import entidade.Oficio;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import util.Utils;

/**
 *
 * @author f5078775
 */
public class UploadAOF {

    UploadDAO uploadDAO = new UploadDAO();
//    public static void main(String[] args) throws IOException, InterruptedException {
//        
//        String diretorio = "C:\\Users\\f5078775\\Desktop\\DocumentosAOF";
//        lerDiretorio(diretorio);
//    }

    public void lerDiretorio(String diretorio) throws IOException, InterruptedException {

        File pasta = new File(diretorio);
        File afile[] = pasta.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {  // percorrer pasta inicial
            File pastas = afile[i];

            if (afile[i].isDirectory()) {
                String subDiretorio = pastas.getName();
                subDiretorio = pastas.getName();
                String caminhoPastaSubpasta = diretorio + "\\" + subDiretorio;
                caminhoPastaSubpasta = diretorio + "\\" + subDiretorio;

                File subPasta = new File(caminhoPastaSubpasta);
                File afilesubpasta[] = subPasta.listFiles();

                int l = 0;
                for (int k = afilesubpasta.length; l < k ; l++) {
                    File subpastas = afilesubpasta[l];
                    String nomeSubpasta = pastas.getName();
                   
                    String nomeArquivo = subpastas.getName();
                    
                     String caminhoCompleto = caminhoPastaSubpasta + "\\" + nomeSubpasta + "\\" + nomeArquivo;
                    
                    
                    Oficio uploadOficio = new Oficio();

                    String aof = Utils.tratarVariavel(nomeSubpasta);

                    uploadOficio.setNomeArquivo(caminhoCompleto);
                    uploadOficio.setAof(aof);

                    uploadDAO.salvar(uploadOficio);

                }

            }

        }

    }

}
