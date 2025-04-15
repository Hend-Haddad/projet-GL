package gestion_emploi;

import observer.Observer;
import observer.Subject;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Classe implements Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final DatabaseConnection dbConnection;

    public Classe(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    PreparedStatement st;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void ajouter_classe(String libelle, String description) {
        try {
        	st = dbConnection.getConnection().prepareStatement(
                "INSERT INTO classe(libelle_classe, description) VALUES(?, ?)"
            );
            st.setString(1, libelle);
            st.setString(2, description);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Données enregistré avec succès");


            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimer_classe(String libelle) {
        try {
        	st = dbConnection.getConnection().prepareStatement(
                "DELETE FROM classe WHERE libelle_classe=?"
            );
            st.setString(1, libelle);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Classe d'id "+libelle+" est supprimé avec succès");
            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier_classe(String nouveauLibelle, String description, String ancienLibelle) {
        try {
        	st =dbConnection.getConnection().prepareStatement(
                "UPDATE classe SET libelle_classe=?, description=? WHERE libelle_classe=?"
            );
            st.setString(1, nouveauLibelle);
            st.setString(2, description);
            st.setString(3, ancienLibelle);
            st.executeUpdate();
            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(JTable tabl_classe, JComboBox<String> list_classe) {
        update_table(tabl_classe);
        update_list(list_classe);
    }

    public void update_table(JTable tabl_classe) {
        try {
        	st = dbConnection.getConnection().prepareStatement("SELECT * FROM classe");
            ResultSet rs = st.executeQuery();
            tabl_classe.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_list(JComboBox<String> list_classe) {
        try {
        	st = dbConnection.getConnection().prepareStatement("SELECT libelle_classe FROM classe");
            ResultSet rs = st.executeQuery();
            list_classe.removeAllItems();
            list_classe.addItem("Votre Choix:");
            while (rs.next()) {
                list_classe.addItem(rs.getString("libelle_classe"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get_by_libelle(String libelle) {
        try {
        	st = dbConnection.getConnection().prepareStatement("SELECT * FROM classe WHERE libelle_classe=?");
            st.setString(1, libelle);
            ResultSet rs = st.executeQuery();
            if (rs.next()) return "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public void change_list_classe(javax.swing.JTextField libelle, javax.swing.JTextArea description, String choix) {
        try {
        	st = dbConnection.getConnection().prepareStatement("SELECT * FROM classe WHERE libelle_classe=?");
            st.setString(1, choix);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                libelle.setText(rs.getString("libelle_classe"));
                description.setText(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
