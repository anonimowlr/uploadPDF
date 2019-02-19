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
public class UploadAofPortal {

    Coletas coletas = new Coletas();
    RespostaDAO respostaDAO = new RespostaDAO();
    List<DataExecucao> listaDataExecucao = new ArrayList<>();
    List<Resposta> listaResposta = new ArrayList<>();
    List<Documento> listaDocumento = new ArrayList<>();

    public void iniciar() {

        criarLista();
        if (listaDataExecucao.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Não há arquivos no banco de dados para carregar no portal jurídico");
            return;
        }

        if (driver == null) {
            coletas.autenticarUsuario();
        }

        try {
            lerLista();
        } catch (Exception ex) {
            for (DataExecucao dataExecucao : listaDataExecucao) {

                respostaDAO.salvar(dataExecucao);
            }
            driver = null;
        }

        for (DataExecucao dataExecucao : listaDataExecucao) {

            respostaDAO.salvar(dataExecucao);
        }

        JOptionPane.showMessageDialog(null, "Fim de rotina!!");

    }

    private void criarLista() {

        listaDataExecucao = respostaDAO.buscar();

    }

    public void lerLista() throws Exception {

        for (DataExecucao dataExecucao : listaDataExecucao) {
            listaResposta = dataExecucao.getRespostas();
            for (Resposta resposta : listaResposta) {

                if (resposta.getEnviadoPortal() == null && resposta.getListaDocumentos().size() > 0) {
                    carregarPortalEnvioContinuado(resposta);
                }

                if (listaResposta.get(listaResposta.size() - 1).equals(resposta)) {
                    resposta.getDataDeExecucao().setRealizada("ENVIADO");
                }

            }

        }

    }

    private void carregarPortalEnvioContinuado(Resposta resposta) throws Exception {
        listaDocumento = resposta.getListaDocumentos();

        for (Documento documento : listaDocumento) {
            if (documento.getNomeDocumento().contains(".DOC") || documento.getSitDocumento() != null) {
                continue;
            }

            if (documento.getSitDocumento() == null) {
                processaPortal(documento);
            }

            if (listaDocumento.get(listaDocumento.size() - 1).equals(documento)) {// verifica se o objeto é o ultimo da lista
                documento.getResposta().setObs(resposta.getQtdDoc() + " Documentos enviados");
                documento.getResposta().setEnviadoPortal("SIM");
               

            }
        }

    }

    private void processaPortal(Documento documento) throws Exception {
        String aofTratada = Utils.tratarVariavel(documento.getResposta().getAof());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        coletas.setURL("https://juridico.intranet.bb.com.br/paj");
        coletas.setURL("https://juridico.intranet.bb.com.br/wfj/oficio/triagem/cumprimentoContinuado/listar");

        coletas.aguardaElementoTelaByID("triagemForm:Decorate:ListBox");
        coletas.selecionarElementoID("triagemForm:Decorate:ListBox", 1);
        coletas.procuraElementoPorId(driver, "triagemForm:anoRastreamento", aofTratada.subSequence(0, 4).toString());

        coletas.procuraElementoPorId(driver, "triagemForm:sequencialRastreamento", aofTratada.subSequence(4, aofTratada.length()).toString());

        coletas.clickElementId(driver, "triagemForm:btFiltrarTriagem");
        coletas.pausar(1000);

        if (coletas.isElementPresentID(driver, "triagemForm:j_id187")) {
            String msg = coletas.lerValorElementoID("triagemForm:j_id187");
            if (msg.equals("Nenhum registro encontrado.")) {
                coletas.selecionarElementoID("triagemForm:Decorate:ListBox", 0);
                coletas.clickElementId(driver, "triagemForm:btFiltrarTriagem");
                coletas.pausar(1000);
            }
        }

        if (coletas.isElementPresentID(driver, "triagemForm:j_id187")) {
            String msg = coletas.lerValorElementoID("triagemForm:j_id187");
            if (msg.equals("Nenhum registro encontrado.")) {
                documento.getResposta().setObs("Não listou a aof");
                return;
            }
        }

        coletas.clickElementId(driver, "triagemForm:dataTable:0:j_id251");
        coletas.aguardaElementoTelaByID("complementarDadosForm:arquivo");
        coletas.procuraElementoPorId(driver, "complementarDadosForm:arquivo", documento.getCaminhoDocumento());
        coletas.procuraElementoPorId(driver, "complementarDadosForm:conteudoDecorate:conteudoInput", documento.getResposta().getMensagemResposta());
        coletas.clickElementId(driver, "complementarDadosForm:dataInicioDecorate:dataInicioInputInputDate");

        if (documento.getResposta().getDataReagendamento() != null) {

            coletas.procuraElementoPorId(driver, "complementarDadosForm:dataInicioDecorate:dataInicioInputInputDate", Utils.formataDataDMY(Utils.formataDateSQL(documento.getResposta().getDataReagendamento().getTime())));
        }

        //coletas.clickElementId(driver, "complementarDadosForm:conteudoDecorate:conteudoInput");
        // js.executeScript("return document.getElementById('complementarDadosForm:dataInicioDecorate:dataInicioInputInputDate').click();", "29/05/2019");
        coletas.pausar(1000);
        String dataImpostada = coletas.lerValorInputID("complementarDadosForm:dataInicioDecorate:dataInicioInputInputDate");

        if (dataImpostada.equals("") && documento.getResposta().getDataReagendamento() != null) {
            return;
        }

        coletas.clickElementId(driver, "complementarDadosForm:btConcluir");

        driver.switchTo().alert().accept();

        documento.setSitDocumento("processado");

    }

}
