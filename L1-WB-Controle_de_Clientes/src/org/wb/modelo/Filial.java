package org.wb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Filial implements Serializable {
	public String nomefilial;
	public List<Cliente> clientes = new ArrayList<Cliente>();
	

	
	

}
