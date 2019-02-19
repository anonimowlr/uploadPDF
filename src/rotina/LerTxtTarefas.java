/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotina;

import dao.RespostaDAO;
import entidade.DataExecucao;
import entidade.Resposta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import static javassist.CtMethod.ConstParameter.string;
import javax.swing.JOptionPane;
import util.Utils;

/**
 *
 * @author f5078775
 */
public class LerTxtTarefas {

    public void importarTxt(String caminhoArquivo, DataExecucao dataExecucao) throws ParseException {

        Boolean proximaPagina = true;
        RespostaDAO respostaDAO = new RespostaDAO();
        String linha = new String();

        String nomeArquivo = caminhoArquivo;
        File arq = new File(nomeArquivo);

        if (arq.exists()) {

            try {

                FileReader leitorArquivo = new FileReader(nomeArquivo);
                BufferedReader buferArquivo = new BufferedReader(leitorArquivo);

                int n = 0;
                while (proximaPagina) {

                    linha = buferArquivo.readLine();
                    if (linha == null) {
                        proximaPagina = false;

                        if (respostaDAO.salvar(dataExecucao)) {
                            JOptionPane.showMessageDialog(null, " SUCESSO -Lista de respostas salva  , agora importe a pasta com os documentos");
                            return;

                        } else {
                            JOptionPane.showMessageDialog(null, "ERRO - Falha ao salvar o Arquivo TXT com as  respostas");
                            return;
                        }

                    }

                    String[] campos = linha.split(";");
                    if (campos.length <= 1) {
                        continue;
                    }

                    Resposta resposta = new Resposta();
                    resposta.setAof(Utils.tratarVariavel(campos[0]));
                    if(campos[1].contains("9999")){
                         resposta.setDataReagendamento(null);
                    } else{
                         resposta.setDataReagendamento(Utils.converterParaCalendar(campos[1]));
                    }
                   
                    resposta.setMensagemResposta(campos[2]);
                    resposta.setDataImportacaoTxt(Utils.getDataAtualCalendar());
                    resposta.setDataDeExecucao(dataExecucao);

                    dataExecucao.adicionarResposta(resposta);

                    if (linha.subSequence(0, 5).equals("99999")) {
                        JOptionPane.showMessageDialog(null, "Importação finalizada!!");
                        return;

                    }

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo: " + caminhoArquivo + "  - " + e);

            }

        }

    }

}
