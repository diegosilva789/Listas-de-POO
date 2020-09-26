package org.wb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class Cliente implements Serializable {
	public String nome;
	public String telefone;
	public String nascimento;
	public String genero;
	public List<Servico> consumo = new ArrayList<Servico>();

	
	@Override
	public String toString() {
		//String delimitador = "########################";
		//String info = "Nome: " + nome + "\nTelefone: " + telefone + "\nData de Nascimento: " + nascimento + "\nGênero: " + genero;
		//return "\n" + delimitador + "\n" + info + "\n" + delimitador + "\n";
		return String.format("%s\t%s\t%s\t%s", nome, telefone, nascimento, genero);
	}
	
	
	public static class Comparators {
	    public static final Comparator<Cliente> NOME = (Cliente c1, Cliente c2) -> c1.nome.compareTo(c2.nome);
	    public static final Comparator<Cliente> GENERO = (Cliente c1, Cliente c2) -> c1.genero.compareTo(c2.genero);
	    public static final Comparator<Cliente> GENEROENOME = (Cliente c1, Cliente c2) -> GENERO.thenComparing(NOME).compare(c1, c2);

	}
	
}

