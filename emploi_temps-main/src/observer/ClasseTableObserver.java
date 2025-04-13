package observer;

import gestion_emploi.Classe;
import javax.swing.JTable;

public class ClasseTableObserver implements Observer {
    private final Classe classe;
    private final JTable table;

    public ClasseTableObserver(Classe classe, JTable table) {
        this.classe = classe;
        this.table = table;
        classe.registerObserver(this);
    }

    @Override
    public void update() {
        classe.update_table(table);
    }
}
