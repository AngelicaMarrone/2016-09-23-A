package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	private DidatticaDAO dao;
	private Map<Integer,Studente> idMap;


		//scelta tipo valori lista

		private List<Nodo> vertex;
		

		//scelta tipo vertici e tipo archi

		private Graph<Nodo, DefaultEdge> graph;
		
		

		public Model() {
			
			//inserire tipo dao

			dao  = new DidatticaDAO();

			corsi= new ArrayList<>(dao.getTuttiICorsi());
			idMap= new HashMap<Integer,Studente>();
			studenti= new ArrayList<>(dao.getTuttiStudenti(idMap));
			

		
		}

		

		public String creaGrafo() {

			

			//scelta tipo vertici e archi

			graph = new SimpleGraph<Nodo,DefaultEdge>(DefaultEdge.class);

			

			//scelta tipo valori lista

			vertex = new ArrayList<Nodo>();
			vertex.addAll(corsi);
			vertex.addAll(studenti);

			Graphs.addAllVertices(graph,vertex);

			

			for (Corso c: corsi)
			{
				dao.getStudentiIscrittiAlCorso(c, idMap);
			}
			
			for(Corso c: corsi)
			{
				for (Studente s: c.getStudenti())
				{
					graph.addEdge(s, c);
				}
			}

			

			System.out.println("#vertici: "+graph.vertexSet().size());

			System.out.println("#archi: "+graph.edgeSet().size());

			String ris= trovaFrequenza();
			return ris;

		}



		private String trovaFrequenza() {
			
			Map<Integer,Integer> frequenza= new LinkedHashMap<Integer,Integer>();
			int i;
			for (i=0; i<35; i++)
			{
				frequenza.put(i, 0);
			}
			
			for (Studente n: studenti)
			{
					List<Nodo> corsi= Graphs.neighborListOf(graph, n);
					int numCorsi= corsi.size();
					
					int count=frequenza.get(numCorsi);
					frequenza.put(numCorsi,count+1);
			}
			
			String ris= "Numero corsi frequentati da n studenti:\n";
			for (Integer c: frequenza.keySet())
			{
				ris+= "Ci sono "+ c+ " corsi " + " frequentati da " + frequenza.get(c)+ " studenti \n";
			}
			return ris;
			
		}
}
