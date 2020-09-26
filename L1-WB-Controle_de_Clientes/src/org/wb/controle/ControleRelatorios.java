package org.wb.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.wb.modelo.Cliente;
import org.wb.modelo.Filial;
import org.wb.modelo.Servico;

public class ControleRelatorios {

	public void idadeMedia(Filial f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		int[] idades = new int[f.clientes.size()];
		for (int i = 0; i < f.clientes.size(); i++) {
			String nasc = f.clientes.get(i).nascimento;
			String[] partes = nasc.split("/");
			int anonasc = Integer.parseInt(partes[2]);
			idades[i] = anoatual - anonasc;
		}
		int total = 0;
		for (int i = 0; i < idades.length; i++) {
			total += idades[i];
		}
		int idademedia = total / idades.length;
		System.out.println("A idade média dos clientes é: " + idademedia);
	}

	public void idadeMediaFem(Filial f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> idades = new ArrayList<>();
		for (int i = 0; i < f.clientes.size(); i++) {
			if (f.clientes.get(i).genero.equals("F")) {
				String nasc = f.clientes.get(i).nascimento;
				String[] partes = nasc.split("/");
				int anonasc = Integer.parseInt(partes[2]);
				idades.add(anoatual - anonasc);
			}
		}
		int total = 0;
		for (int i = 0; i < idades.size(); i++) {
			total += idades.get(i);
		}
		int idademedia = total / idades.size();
		System.out.println("A idade média das clientes femininas é: " + idademedia);
	}

	public void idadeMediaMas(Filial f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> idades = new ArrayList<>();
		for (int i = 0; i < f.clientes.size(); i++) {
			if (f.clientes.get(i).genero.equals("M")) {
				String nasc = f.clientes.get(i).nascimento;
				String[] partes = nasc.split("/");
				int anonasc = Integer.parseInt(partes[2]);
				idades.add(anoatual - anonasc);
			}
		}
		int total = 0;
		for (int i = 0; i < idades.size(); i++) {
			total += idades.get(i);
		}
		int idademedia = total / idades.size();
		System.out.println("A idade média dos clientes masculinos é: " + idademedia);
	}

	public void rankingServicos(Filial f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (Cliente cliente : f.clientes) {
			for (Servico consumo : cliente.consumo) {
				if (consumo.tiposervico.equals("Manicure")) {
					manicure += 1;
				} else if (consumo.tiposervico.equals("Pedicure")) {
					pedicure += 1;
				} else if (consumo.tiposervico.equals("Design de Sobrancelhas")) {
					sobrancelhas += 1;
				} else if (consumo.tiposervico.equals("Corte de Cabelo")) {
					corte += 1;
				} else {
					pintura += 1;
				}
			}

		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking de serviços #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));

	}

	public void rankingServicosFem(Filial f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (Cliente cliente : f.clientes) {
			if (cliente.genero.equals("F")) {
				for (Servico consumo : cliente.consumo) {
					if (consumo.tiposervico.equals("Manicure")) {
						manicure += 1;
					} else if (consumo.tiposervico.equals("Pedicure")) {
						pedicure += 1;
					} else if (consumo.tiposervico.equals("Design de Sobrancelhas")) {
						sobrancelhas += 1;
					} else if (consumo.tiposervico.equals("Corte de Cabelo")) {
						corte += 1;
					} else {
						pintura += 1;
					}
				}
			}
		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking dos serviços mais utilizados pelo público feminino #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));
	}

	public void rankingServicosMas(Filial f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (Cliente cliente : f.clientes) {
			if (cliente.genero.equals("M")) {
				for (Servico consumo : cliente.consumo) {
					if (consumo.tiposervico.equals("Manicure")) {
						manicure += 1;
					} else if (consumo.tiposervico.equals("Pedicure")) {
						pedicure += 1;
					} else if (consumo.tiposervico.equals("Design de Sobrancelhas")) {
						sobrancelhas += 1;
					} else if (consumo.tiposervico.equals("Corte de Cabelo")) {
						corte += 1;
					} else {
						pintura += 1;
					}
				}
			}
		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking dos serviços mais utilizados pelo público masculino #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));

	}

}
