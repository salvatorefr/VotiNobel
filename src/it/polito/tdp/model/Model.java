package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import it.polito.tdp.dao.EsameDAO;





public class Model {
	
	ArrayList<Esame> tutti= new ArrayList<Esame>();
	
	private int numeroCreditiImpostato=0;
	
	//mappa delle possibili combinazioni
	
	HashMap<ArrayList<Esame>, Double> combinazioni;
	
	
	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
	
	
	combinazioni=new HashMap<ArrayList<Esame>,Double>();
	this.numeroCreditiImpostato=numeroCrediti;
	EsameDAO eDao= new EsameDAO();	
	tutti.addAll(eDao.getTuttiEsami());
	
	 //inizio
	combina(new ArrayList<Esame>(),tutti,0,0)	;
	
	//return del migliore voto x crediti
	double massimo=0;
	ArrayList<Esame> risultato=new ArrayList<Esame>();
	for (ArrayList<Esame> key :combinazioni.keySet()){
	
		if (combinazioni.get(key)>massimo) 
		{
			massimo=combinazioni.get(key);
			risultato=key;
		}
	}
	
	return risultato;
	
	
	
	}
	
	

	private void combina(ArrayList<Esame> parziali, ArrayList<Esame> combinabili,int creditiParziali, int creditiPerVotoParziali) {
			
		//finisce quando i crediti==numero crediti o Combinabili.size=0
		//quando finisce clona la lista e la aggiunge alle combinazioni possibili
		
   if (creditiParziali==numeroCreditiImpostato||combinabili.size()==0) {
	   ArrayList<Esame> listaDaAggiungere= this.clonaLista(parziali);
	   combinazioni.put(listaDaAggiungere, (double)creditiPerVotoParziali/(double)creditiParziali); 

	   return;
   }
   
   
   
   //se non contiene l'elemento, lo aggiunge ai parziali,rimuove l'elemento dai combinabili
    for (int i=0;i<combinabili.size();i++) { 
    	
    	
    	Esame e=combinabili.get(i);
 
	      	if (e.getCrediti()+creditiParziali<=numeroCreditiImpostato) {
    		parziali.add(e);
    		ArrayList<Esame> combinabiliClone=this.clonaLista(combinabili);
    		combinabiliClone.remove(e);
    		//itero
	   
	    combina(parziali,combinabiliClone,creditiParziali+combinabili.get(i).getCrediti(),creditiPerVotoParziali+combinabili.get(i).getCrediti()*combinabili.get(i).getVoto());
	   //backtracking
	   parziali.remove(e);
	  
	   }
	      	else {
	      		ArrayList<Esame> combinabiliClone=this.clonaLista(combinabili);
	    		combinabiliClone.remove(e);
	    		combina(parziali,combinabiliClone,creditiParziali,creditiPerVotoParziali);
	    		
	    		 return;
	    		
	      	}
	   
  
   
	  
	  

   }
   
			
		}
	
	
	
   public ArrayList<Esame> clonaLista(ArrayList<Esame> listaOriginale){
	   ArrayList<Esame> listaClonata= new ArrayList<Esame>();
	  listaClonata.addAll(listaOriginale);    
	   return listaClonata;
	
   }
		
		
	}

	
	
	

	

	

