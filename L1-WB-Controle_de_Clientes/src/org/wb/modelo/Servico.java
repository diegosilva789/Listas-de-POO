package org.wb.modelo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Servico implements Serializable {
	public String tiposervico;
	public double valorservico;
	public Date diadoservico;
	
	@Override
	public String toString() {
//		String delimitador = "########################";
//		String info = "Tipo de Serviço: " + tiposervico + "\nValor: " + valorservico + "\nData: " + diadoservico;
//		return "\n" + delimitador + "\n" + info + "\n" + delimitador + "\n";
		return String.format("%s\t%f\t%tD", tiposervico, valorservico, diadoservico);
	}
}
