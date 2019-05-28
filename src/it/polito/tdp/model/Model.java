package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.dao.EsameDAO;





public class Model {
	List<Esame> tutti= new EsameDAO().getTuttiEsami();
	List<Esame> best;
	double media_best;
	/**
	 * crea una combinazione di corsi
	 * @param numeroCrediti
	 * @return {@link ArrayList} < {@link Esame}> 
	 */
	
	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		media_best=0;
		best=new ArrayList();
	cerca(new HashSet<Esame>(),0,numeroCrediti);
	return best;
	}
		
	
	
	public void cerca(Set<Esame> parziale,int L, int m) { //L = livello, m= crediti
		
		int crediti= sommaCrediti(parziale);
		
		if (crediti>m){
			return;
			}
		if (crediti==m){
			double media= calcolaMedia(parziale);
			if (media>media_best){
				media_best=media;
				best= new ArrayList<Esame>(parziale);
				return;
				}
			}
		else//se i crediti sono minori di m
		{
			
			if (L==tutti.size()) //termina se non ci sono più esami da aggiungere
			{return;}
		else {
			
			cerca(parziale,L+1,m); //provo a non aggiungerlo
			Esame singolo=tutti.get(L);
			parziale.add(singolo); //provo ad aggiungerlo
			cerca(parziale,L+1,m);
			parziale.remove(singolo);//backtracking
				
			}
		}
			
		

	
		
	}

	private int sommaCrediti(Set<Esame> parziale) {
		int somma=0;
	for (Esame e:parziale) {
		somma+= e.getCrediti();
	}
	
		
			
		
		return somma;
	}

	private double calcolaMedia(Set<Esame> parziale) {
		
		int creditiPerVoto=0;
		int crediti=0;
		for (Esame e:parziale) {
			creditiPerVoto= e.getCrediti()*e.getVoto();
			crediti+= e.getCrediti();
		}
		return creditiPerVoto/parziale.size();
	}
}

		
		
	

	
	

