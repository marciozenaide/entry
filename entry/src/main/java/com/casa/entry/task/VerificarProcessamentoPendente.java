package com.casa.entry.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.casa.entry.model.Item;
import com.casa.entry.model.Processamento;
import com.casa.entry.model.Venda;
import com.casa.entry.repository.Itens;
import com.casa.entry.repository.Processamentos;
import com.casa.entry.service.CadastroProcessamentoService;
import com.casa.entry.util.VerificarDiretorioExiste;

@Component
public class VerificarProcessamentoPendente {

	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;

	private static final String DIR_NAME = "PROCESSADOS";
	private String caminho;

	@Autowired
	private CadastroProcessamentoService cadastroProcessmentoService;

	private Processamento processamento;

	@Autowired
	private Processamentos processamentos;

	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private Itens itens;

	@Scheduled(fixedRate = MINUTO)
	public void capturarPendentes() throws IOException {
		List<Processamento> list = new ArrayList<Processamento>();
		List<Processamento> list10 = new ArrayList<Processamento>();
		list = processamentos.findAll();

		processamento = null;
		for (Processamento pElement : list) {
			if (pElement.getStatus().equals("PENDENTE")) {
				processamento = pElement;
				break;
			}
		}
		if (processamento != null) {
			counter.incrementAndGet();
			int i = (int) counter.intValue() / 10;
			String nomeArquivo = "arquivo_" + i + ".txt";
			processamento.setArquivo(nomeArquivo);
			processamento.setStatus("Ok");
			cadastroProcessmentoService.salvar(processamento);

			List<String> conteudo = new ArrayList<String>();
			Item item = new Item();
			Venda venda = new Venda();
			List<Item> listaItem = new ArrayList<Item>();

			venda.setId(processamento.getId());
			listaItem = itens.findByVenda(venda);
			// if (listaItem.size()>0){
			conteudo = gerarConteudo(list.get(i), listaItem, counter.intValue());
			// }else{
			// conteudo = gerarConteudo(list.get(i), listaItem,
			// counter.intValue());
			// }
			gerarArquivo(nomeArquivo, conteudo);

		}
	}

	private List<String> gerarConteudo(Processamento processamento, List<Item> itens, int ind) {
		String conteudoProcessamento = "";
		String conteudoItem = "";
		List<String> lista = new ArrayList<String>();
		/*
		 * ID VENDA(numero) : POS 0 – 10 DATA (data ddmmaaaa): POS 11 – 18 LOJA
		 * (numero): POS 19-22 PDV (numero): POS 23-25 PRODUTO (TEXTO): POS
		 * 26-36 PRECO UNITARIO (numero 2 casas decimais): POS 37-41 DESCONTO
		 * (numero 2 casas decimais): POS 42-46 VALOR TOTAL (numero 2 casas
		 * decimais, PRECO UNITARIO – DESCONTO): POS 47-51 processar 1 venda por
		 * linha no máximo 10 vendas por arquivo
		 */

		String campoID = "0000000000".concat(processamento.getId().toString());
		int num = campoID.length() - 10;
		campoID = campoID.substring(num);
		conteudoProcessamento += campoID;

		String campoData;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy.MM.dd");
		campoData = formatter.format(processamento.getData());
		campoData = campoData.replace(".", "");
		conteudoProcessamento += campoData;

		String campoLoja = "000".concat(processamento.getLoja().toString());
		num = campoLoja.length() - 3;
		campoLoja = campoLoja.substring(num);
		conteudoProcessamento += campoLoja;

		String campoPDV = "000".concat(processamento.getPdv().toString());
		num = campoPDV.length() - 3;
		campoPDV = campoPDV.substring(num);
		conteudoProcessamento += campoPDV;

		BigDecimal total = new BigDecimal(0);
		BigDecimal valor = new BigDecimal(0);
		int i = 0;
		if (itens.size() > 0) {
			for (Item item : itens) {
				String campoProduto = "0000000000".concat(item.getProduto().toString());
				num = campoProduto.length() - 10;
				campoProduto = campoProduto.substring(num);
				conteudoItem += campoProduto;

				BigDecimal precoUnitario = formataDecimal(item.getPrecoUnitario());
				String campoPrecoUnitario = "00000".concat(precoUnitario.toString());
				num = campoPrecoUnitario.length() - 5;
				campoPrecoUnitario = campoPrecoUnitario.substring(num);
				campoPrecoUnitario = campoPrecoUnitario.replace(".", "");
				conteudoItem += campoPrecoUnitario;

				BigDecimal desconto = formataDecimal(item.getDesconto());
				String campoDesconto = "00000".concat(desconto.toString());
				num = campoDesconto.length() - 5;
				campoDesconto = campoDesconto.replace(".", "");
				campoDesconto = campoDesconto.substring(num);
				conteudoItem += campoDesconto;

				valor = item.getPrecoUnitario().subtract(item.getDesconto());
				total = total.add(valor);
				
				String campoTotal = "00000".concat(formataDecimal(valor).toString());
				num = campoTotal.length() - 5;
				campoTotal = campoTotal.substring(num);
				campoTotal = campoTotal.replace(".", "");

				lista.add(i, conteudoProcessamento + conteudoItem + campoTotal +  "\r\n");
				i++;
			}

			// VALOR TOTAL (numero 2 casas decimais, PRECO UNITARIO – DESCONTO):
			/*
			String campoTotal = "00000".concat(formataDecimal(total).toString());
			num = campoTotal.length() - 5;
			campoTotal = campoTotal.substring(num);
			campoTotal = campoTotal.replace(".", "");
			
			lista.add(i,  campoTotal +" \r\n");
			*/
		} else {
			lista.add(i, conteudoProcessamento +"\r\n");
		}

		return lista;
	}

	public BigDecimal formataDecimal(BigDecimal vlrFator) {
		BigDecimal numFormatado = vlrFator.setScale(2, BigDecimal.ROUND_UP);
		return numFormatado;
	}

	private void gerarArquivo(String nomeArquivo, List<String> conteudo) throws IOException {
		/*
		 * d. Uma 3a thread deverá a cada minuto, capturar os registros da
		 * tabela tb_processamento, gerar um arquivo TXT em uma pasta
		 * PROCESSADOS e marcar o status desses registros como “OK” na tabela
		 * tb_processamento; e. Cada arquivo pode conter apenas 10 vendas, ou
		 * seja, serão gerados tantos arquivos quanto vendas até todas as vendas
		 * serem processados. O nome do arquivo é livre desde que, não
		 * sobreescreva. Gravar na tabela tb_processamento o nome do arquivoonde
		 * a venda foi gerada. f. O layout do arquivo deverá ser conforme o
		 * ANEXO II – Layout do Arquivo
		 * 
		 * Todos os números, datas e valores com zeros a esquerda sem virgulas,
		 * pontos e outros caracteres.
		 * 
		 */

		caminho = "C:\\java";
		// file = new File(caminho + "\\" + DIR_NAME);

		VerificarDiretorioExiste verificarDiretorioExiste = new VerificarDiretorioExiste();
		if (verificarDiretorioExiste.dirExiste()) {
			try (FileOutputStream file = new FileOutputStream(caminho + "\\" + DIR_NAME + "\\" + nomeArquivo, true);) {
				// Gravar no arquivo designado
				//System.out.println(" " + nomeArquivo);
				for (String str : conteudo) {
					byte buf[] = str.getBytes();
					file.write(buf);
				}
			} catch (IOException e) {
				System.out.println("Um erro de I/O de ocorreu.");
			}

		}
	}
}
