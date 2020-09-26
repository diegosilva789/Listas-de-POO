package org.wb.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.wb.modelo.Cliente;
import org.wb.modelo.Filial;
import org.wb.modelo.Servico;

public class ControleMenu {

	@SuppressWarnings("unchecked")
	public List<Filial> recuperarDados(List<Filial> filiais) throws Exception {
		Date data = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dataformatada = dateFormat.format(data);

		String caminho = "D:\\OneDrive - Etec Centro Paula Souza\\Área de Trabalho\\3sem\\github"
				+ "\\Listas_de_POO\\L1-WB-Controle_de_Clientes\\src\\org\\wb\\dados\\cadastros.ser";
		String caminho2 = "D:\\OneDrive - Etec Centro Paula Souza\\Área de Trabalho\\3sem\\github"
				+ "\\Listas_de_POO\\L1-WB-Controle_de_Clientes\\src\\org\\wb\\dados\\cadastros" + dataformatada
				+ ".ser";

		File original = new File(caminho);
		File backup = new File(caminho2);

		try {
			Files.copy(original.toPath(), backup.toPath());
		} catch (Exception e) {
			System.out.println("Algo deu errado: " + e);
		}

		FileInputStream canal = new FileInputStream(caminho);
		ObjectInputStream leitor = new ObjectInputStream(canal);
		filiais = (List<Filial>) leitor.readObject();
		leitor.close();
		System.out.println("Cadastros lidos com sucesso!");
		return filiais;
	}

	public void salvarDados(List<Filial> filiais) throws Exception {
		String caminho = "D:\\OneDrive - Etec Centro Paula Souza\\Área de Trabalho\\3sem\\github"
				+ "\\Listas_de_POO\\L1-WB-Controle_de_Clientes\\src\\org\\wb\\dados\\cadastros.ser";
		FileOutputStream canal = new FileOutputStream(caminho);
		ObjectOutputStream escritor = new ObjectOutputStream(canal);
		escritor.writeObject(filiais);
		escritor.close();
		System.out.println("Cadastros salvos com sucesso!");
	}

	public Filial selecionarFilial(List<Filial> filiais, Filial f, Controle controle) throws Exception {
		System.out.println("Por favor, digite o número da filial:");
		String numerofil = controle.texto();

		if (filiais.isEmpty()) {
			System.out.println("Não existe filiais cadastradas");
			f = salvarFilial(filiais, f, numerofil, controle);
		} else {
			f = procurarFilial(filiais, f, numerofil, controle);
		}
		return f;
	}

	public Filial procurarFilial(List<Filial> filiais, Filial f, String numerofil, Controle controle) {
		int encontrou = 0;
		for (Filial fil : filiais) {
			if (fil.numero.equals(numerofil)) {
				System.out.println("Filial encontrada\n");
				f = fil;
				encontrou = 1;
				break;
			}
		}
		if (encontrou != 1) {
			System.out.println("Filial não encontrada");
			f = salvarFilial(filiais, f, numerofil, controle);
			System.out.println("");
		}
		return f;
	}

	public Filial salvarFilial(List<Filial> filiais, Filial f, String numerofil, Controle controle) {
		int resposta = 0;
		System.out.println("Deseja salvar a nova filial?");
		System.out.println("(Escolha Sim(1) ou Não(2)");
		resposta = controle.opcao();
		switch (resposta) {
		case 1:
			Filial novafilial = new Filial();
			novafilial.numero = numerofil;
			System.out.println("Digite o nome da filial:");
			novafilial.nomefilial = controle.texto();
			filiais.add(novafilial);
			f = novafilial;
			break;
		default:
			System.out.println("A filial não foi salva\n");
		}
		return f;
	}

	public void cadastrarCliente(Controle controle, Filial f) {
		Cliente c = new Cliente();
		System.out.println("Por favor insira o nome do cliente:");
		c.nome = controle.texto();
		System.out.println("Por favor insira o telefone:");
		c.telefone = controle.texto();
		System.out.println("Por favor insira a data de nascimento:");
		c.nascimento = controle.texto();
		System.out.println("Por favor insira o gênero (M ou F):");
		c.genero = controle.texto();
		f.clientes.add(c);
	}

	public void editarCliente(Filial f, Controle controle) {
		System.out.println("Digite o nome do cliente para alterar seus dados:");
		String nomecliente = controle.texto();
		for (Cliente pessoa : f.clientes) {
			if (pessoa.nome.equals(nomecliente)) {
				System.out.println("Por favor insira o nome do cliente:");
				pessoa.nome = controle.texto();
				System.out.println("Por favor insira o telefone:");
				pessoa.telefone = controle.texto();
				System.out.println("Por favor insira a data de nascimento:");
				pessoa.nascimento = controle.texto();
				System.out.println("Por favor insira o gênero (M ou F):");
				pessoa.genero = controle.texto();
				break;
			}

		}
	}

	public void excluirCliente(Filial f, Controle controle) {
		System.out.println("Digite o nome do cliente para excluir seus dados:");
		String nomecliente = controle.texto();
		for (Cliente pessoa : f.clientes) {
			if (pessoa.nome.equals(nomecliente)) {
				f.clientes.remove(pessoa);
				System.out.println("O cadastro do cliente " + nomecliente + " foi removido!");
				break;
			}
		}
	}

	public void listarClientesAlfabeto(Filial f) {
		if (f.clientes.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<Cliente> listacli = new LinkedList<>();
		listacli = f.clientes;
		Collections.sort(listacli, Cliente.Comparators.NOME);
		listacli.forEach(pessoa -> System.out.println(pessoa));
		System.out.println("");
	}

	public void listarClientesFemAlfabeto(Filial f) {
		if (f.clientes.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<Cliente> listacli = new LinkedList<>();
		listacli = f.clientes;
		Collections.sort(listacli, Cliente.Comparators.GENEROENOME);
		for (Cliente pessoa : listacli) {
			if (pessoa.genero.equals("F")) {
				System.out.println(pessoa);
			}
		}
		System.out.println("");
	}

	public void listarClientesMasAlfabeto(Filial f) {
		if (f.clientes.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<Cliente> listacli = new LinkedList<>();
		listacli = f.clientes;
		Collections.sort(listacli, Cliente.Comparators.GENEROENOME);
		for (Cliente pessoa : listacli) {
			if (pessoa.genero.equals("M")) {
				System.out.println(pessoa);
			}
		}
		System.out.println("");
	}

	public void registrarConsumo(Filial f, int escolha, Controle controle) {
		System.out.println("Digite o nome do cliente para registrar o seu consumo:");
		String nomecliente = controle.texto();
		Date data = new Date();
		double total = 0;
		for (Cliente pessoa : f.clientes) {
			if (pessoa.nome.equals(nomecliente)) {
				while (escolha != 6) {
					MenuServico.mostrarMenuServico();
					escolha = controle.opcao();
					if (escolha == 1) {
						Servico listaconsumo = new Servico();
						listaconsumo.tiposervico = "Manicure";
						listaconsumo.valorservico = 50.0;
						listaconsumo.diadoservico = data;
						pessoa.consumo.add(listaconsumo);
					}
					if (escolha == 2) {
						Servico listaconsumo = new Servico();
						listaconsumo.tiposervico = "Pedicure";
						listaconsumo.valorservico = 60.0;
						listaconsumo.diadoservico = data;
						pessoa.consumo.add(listaconsumo);
					}
					if (escolha == 3) {
						Servico listaconsumo = new Servico();
						listaconsumo.tiposervico = "Design de Sobrancelhas";
						listaconsumo.valorservico = 40.0;
						listaconsumo.diadoservico = data;
						pessoa.consumo.add(listaconsumo);
					}
					if (escolha == 4) {
						Servico listaconsumo = new Servico();
						listaconsumo.tiposervico = "Corte de Cabelo";
						listaconsumo.valorservico = 80.0;
						listaconsumo.diadoservico = data;
						pessoa.consumo.add(listaconsumo);
					}
					if (escolha == 5) {
						Servico listaconsumo = new Servico();
						listaconsumo.tiposervico = "Pintura de Cabelo";
						listaconsumo.valorservico = 150.0;
						listaconsumo.diadoservico = data;
						pessoa.consumo.add(listaconsumo);
					}
				}
				for (Servico consumido : pessoa.consumo) {
					if (consumido.diadoservico.equals(data)) {
						total = total + consumido.valorservico;
						System.out.println(consumido);
					}
				}
				break;
			}
		}
		System.out.printf("Total a pagar: R$ %f\n", total);
	}

	public void listarHisConCliente(Filial f, Controle controle) {
		System.out.println("Digite o nome do cliente para exibir o seu histórico:");
		String nomecliente = controle.texto();
		double total = 0;
		for (Cliente pessoa : f.clientes) {
			if (pessoa.nome.equals(nomecliente)) {
				if (pessoa.consumo.isEmpty()) {
					System.out.println("Esse cliente ainda não utilizou nenhum serviço!");
				} else {
					for (Servico consumido : pessoa.consumo) {
						total = total + consumido.valorservico;
					}
					pessoa.consumo.forEach(consumido -> System.out.println(consumido));
				}
			}
		}
		System.out.printf("Total do histórico: R$ %f\n", total);
	}

	public void listarRelatorios(Filial f, int escolha, Controle controle, ControleRelatorios controlerelatorios) {
		while (escolha != 7) {
			MenuRelatorios.mostrarMenuServico();
			escolha = controle.opcao();
			if (escolha == 1) {
				controlerelatorios.idadeMedia(f);
			}
			if (escolha == 2) {
				controlerelatorios.idadeMediaFem(f);
			}
			if (escolha == 3) {
				controlerelatorios.idadeMediaMas(f);
			}
			if (escolha == 4) {
				controlerelatorios.rankingServicos(f);
			}
			if (escolha == 5) {
				controlerelatorios.rankingServicosFem(f);
			}
			if (escolha == 6) {
				controlerelatorios.rankingServicosMas(f);
			}
		}
	}
}
