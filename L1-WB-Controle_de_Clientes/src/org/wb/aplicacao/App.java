package org.wb.aplicacao;

import java.util.ArrayList;
import java.util.List;

import org.wb.controle.Controle;
import org.wb.controle.ControleMenu;
import org.wb.controle.Menu;
import org.wb.modelo.Filial;

public class App {

	public static void main(String[] args) {
		Controle controle = new Controle();
		ControleMenu controlemenu = new ControleMenu();
		List<Filial> filiais = new ArrayList<>();
		try {
			filiais = controlemenu.recuperarDados(filiais);
		} catch (Exception e) {
			System.out.println("Algo deu errado na leitura!");
		}
		Filial f = new Filial();

		int escolha = 0;
		while (escolha != 11) {
			try {
				controlemenu.salvarDados(filiais);
			} catch (Exception e) {
				System.out.println("Algo deu errado no salvamento!");
			}
			System.out.println("Digite 0 para continuar");
			escolha = controle.opcao();
			Menu.mostrarMenu(f);
			escolha = controle.opcao();

			if (escolha == 1) {
				try {
					f = controlemenu.selecionarFilial(filiais, f, controle);
				} catch (Exception e) {
					System.out.println("Algo deu errado na seleção da filial");
					e.printStackTrace();
				}
			}

			if (escolha == 2) {
				controlemenu.cadastrarCliente(controle, f);
			}

			if (escolha == 3) {
				controlemenu.editarCliente(f, controle);
			}

			if (escolha == 4) {
				controlemenu.excluirCliente(f, controle);
			}

			if (escolha == 5) {
				controlemenu.registrarConsumo(f, escolha, controle);
			}
			
			if (escolha == 6) {
				controlemenu.listarHisConCliente(f, controle);
			}

			if (escolha == 7) {
				controlemenu.listarClientesAlfabeto(f);
			}

			if (escolha == 8) {
				controlemenu.listarClientesFemAlfabeto(f);
			}

			if (escolha == 9) {
				controlemenu.listarClientesMasAlfabeto(f);
			}

		}
		System.out.println("Aplicação Finalizada!");

	}
}
