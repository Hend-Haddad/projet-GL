package gestion_emploi;

public class Strategies {
	// TousLesJoursStrategy.java
	public static class TousLesJoursStrategy implements RechercheStrategy {
	    @Override
	    public String rechercher(String classe, String matiere, String jour) {
	        String baseQuery = "SELECT seance.*, enseignant.nom, enseignant.contact " +
	                          "FROM seance " +
	                          "INNER JOIN enseignant ON enseignant.matricule = seance.matricule " +
	                          "WHERE classe = ?";
	        if (matiere != null && !matiere.equals("null")) baseQuery += " AND matiere = ?";
	        return baseQuery;
	    }
	}

	// JourSpecifiqueStrategy.java
	public static class JourSpecifiqueStrategy implements RechercheStrategy {
	    @Override
	    public String rechercher(String classe, String matiere, String jour) {
	        String baseQuery = "SELECT seance.*, enseignant.nom, enseignant.contact " +
	                          "FROM seance " +
	                          "INNER JOIN enseignant ON enseignant.matricule = seance.matricule " +
	                          "WHERE classe = ? AND jour = ?";
	        if (matiere != null && !matiere.equals("null")) baseQuery += " AND matiere = ?";
	        return baseQuery;
	    }
	}

}
