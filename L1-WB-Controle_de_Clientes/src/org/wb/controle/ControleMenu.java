package org.wb.controle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		String caminho = "D:\\OneDrive - Etec Centro Paula Souza\\Área de Trabalho\\3sem\\github\\L1-WB-Controle_de_Clientes\\src\\org\\wb\\dados\\cadastros.ser";;
		FileInputStream canal = new FileInputStream(caminho);
		ObjectInputStream leitor = new ObjectInputStream(canal);
		filiais = (List<Filial>) leitor.readObject();
		leitor.close();
		System.out.println("Cadastros lidos com sucesso!");
		return filiais;
	}
	
	public void salvarDados(List<Filial> filiais) throws Exception {
		String caminho = "D:\\OneDrive - Etec Centro Paula Souza\\Área de Trabalho\\3sem\\github\\L1-WB-Controle_de_Clientes\\src\\org\\wb\\dados\\cadastros.ser";;
		FileOutputStream canal = new FileOutputStream(caminho);
		ObjectOutputStream escritor = new ObjectOutputStream(canal);
		escritor.writeObject(filiais);
		escritor.close();
		System.out.println("Cadastros salvos com sucesso!");
	}
	
	public Filial procurarFilial (List<Filial> filiais, Filial f, String nomefil, Controle controle) {
		for (Filial fil : filiais) {
			if (fil.nomefilial.equals(nomefil)) {
				System.out.println("Filial encontrada");
				f = fil;
			} else {
				salvarFilial(filiais, f, nomefil, controle);
				break;
			}
		}
		return f;
	}
	
	public Filial salvarFilial (List<Filial> filiais, Filial f, String nomefil, Controle controle) {
		int resposta = 0;
		System.out.println("Deseja salvar a nova filial?");
		System.out.println("(Escolha Sim(1) ou Não(2)");
		resposta = controle.opcao();
		System.out.println("Você digitou: " + resposta);
        switch (resposta) {
            case 1:
				Filial novafilial = new Filial();
				novafilial.nomefilial = nomefil;
				filiais.add (novafilial);
				f = novafilial;
				break;
			default:
				System.out.println("A filial não foi salva");
        }
		return f;
	}
	
	public Filial selecionarFilial (List<Filial> filiais, Filial f, Controle controle) throws Exception {
		System.out.println("Por favor, digite o número da filial:");
		String nomefil = controle.texto();
		
		if (filiais.isEmpty()) {
			System.out.println("Não existe filiais cadastradas");
			f = salvarFilial(filiais, f, nomefil, controle);
		} else {
			f = procurarFilial(filiais, f, nomefil, controle);
		}
		return f;
	}
	
	public void cadastrarCliente (Controle controle, Filial f) {
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
	
	public void editarCliente (Filial f, Controle controle) {
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
	
	public void excluirCliente (Filial f, Controle controle) {
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
	
	public void listarClientesAlfabeto (Filial f) {
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
	
	public void listarClientesFemAlfabeto (Filial f) {
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
	
	public void listarClientesMasAlfabeto (Filial f) {
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
					pessoa.consumo.forEach(consumido -> System.out.println(consumido));
				}
			}
			for (Servico consumido : pessoa.consumo) {
				total = total + consumido.valorservico;
			}
		}
		System.out.printf("Total do histórico: R$ %f\n", total);
	}

}
