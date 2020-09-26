package org.wb.controle;

import org.wb.modelo.Filial;

public class Menu {
	public static void mostrarMenu(Filial f) {
		System.out.println("\n============================================================");
		System.out.println("Você escolheu a filial: " + f.nomefilial);
		System.out.println("============================================================");
		System.out.println("Selecione a opção desejada:");
		System.out.println("1.  Selecione a filial");
		System.out.println("2.  Cadastrar cliente");
		System.out.println("3.  Editar cliente");
		System.out.println("4.  Excluir cliente");
		System.out.println("5.  Registrar consumo do cliente");
		System.out.println("6.  Listar histórico de consumo do cliente");
		System.out.println("7.  Listar clientes em ordem alfabética");
		System.out.println("8.  Listar clientes femininos");
		System.out.println("9.  Listar clientes masculinos");
		System.out.println("10.  Relatórios de Inteligência - AINDA FALTA ESSE");
		System.out.println("11. Sair");
		System.out.println("============================================================\n");
	}

}
