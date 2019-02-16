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

    public void iniciar(String diretorio) throws IOException, InterruptedException, Exception {
        createList(diretorio);
        gravarDados();
    }

    public void createList(String diretorio) {// método cria a lista com  as tarefas a serem executadas

        try {
            listaDataExecucao = respostaDAO.buscar();

            for (DataExecucao dataExecucao : listaDataExecucao) {
                listaResposta = dataExecucao.getRespostas();
                lerLista(diretorio);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO-" + e);
        }
    }

    public void lerLista(String diretorio) throws IOException, InterruptedException, Exception {

        for (Resposta resposta : listaResposta) {
            if (resposta.getListaDocumentos().isEmpty()) {
                lerDiretorio(diretorio, resposta);
            }

        }

    }

    private void gravarDados() throws Exception {

        for (DataExecucao dataExecucao : listaDataExecucao) {
            if (respostaDAO.salvar(dataExecucao)) {
                JOptionPane.showMessageDialog(null, "Gravado no banco de dados os documentos referentes  à data " + Utils.formataDateSQL(dataExecucao.getDataExec().getTime()));
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Não foi possível gravar os documentos  referentes  à data " + Utils.formataDateSQL(dataExecucao.getDataExec().getTime()));
            }

        }
    }

    public void lerDiretorio(String diretorio, Resposta resposta) throws IOException, InterruptedException, Exception {

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

                        String caminhoCompleto = caminhoPastaSubpasta + "\\" + nomeConteudo;

                        if (afilesubpasta[l].isDirectory()) {

                            File subsubPasta = new File(caminhoCompleto);
                            
                            File afilesubsubpasta[] = subsubPasta.listFiles();
                         

                            if (Utils.tratarVariavel(Utils.formataDataDMY(Utils.formataDateSQL(resposta.getDataDeExecucao().getDataExec().getTime()))).equals(Utils.tratarVariavel(nomeConteudo))) {
                                int m = 0;

                                for (int y = afilesubsubpasta.length; m < y; m++) {
                                    Documento documento = new Documento();
                                    conteudo = afilesubsubpasta[m];
                                    nomeConteudo = conteudo.getName();
                                    documento.setNomeDocumento(nomeConteudo);
                                    documento.setCaminhoDocumento(conteudo.toString());
                                    resposta.setQtdDoc(y);
                                    resposta.adicionarDocumento(documento);

                                }
                            }

                        }

                    }

                }

            }

        }

    }

}
