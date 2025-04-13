package observer;

import gestion_emploi.Classe;
import javax.swing.JComboBox;

public class ClasseComboBoxObserver implements Observer {
    private final Classe classe;
    private final JComboBox<String> comboBox;

    public ClasseComboBoxObserver(Classe classe, JComboBox<String> comboBox) {
        this.classe = classe;
        this.comboBox = comboBox;
        classe.registerObserver(this);
    }

    @Override
    public void update() {
        classe.update_list(comboBox);
    }
}
