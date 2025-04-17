package gestion_emploi;

public interface RechercheStrategy {
	
	    String buildQuery(String classe, String matiere, String jour);
	}
