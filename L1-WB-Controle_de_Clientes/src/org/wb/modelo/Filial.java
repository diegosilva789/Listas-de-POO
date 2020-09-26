package org.wb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Filial implements Serializable {
	public String numero;
	public String nomefilial;
	public List<Cliente> clientes = new ArrayList<Cliente>();

	@Override
	public String toString() {
		// String delimitador = "########################";
		// String info = "Nome: " + nome + "\nTelefone: " + telefone + "\nData de
		// Nascimento: " + nascimento + "\nGênero: " + genero;
		// return "\n" + delimitador + "\n" + info + "\n" + delimitador + "\n";
		return String.format("%s - %s", numero, nomefilial);
	}
}
