package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel panel;
    private JTextArea surnameTextArea;
    private JTextArea nameTextArea;
    private JTextArea patronymicTextArea;
    private JPanel panellow;
    private JButton collapsButton;
    private JLabel Surname;
    private JLabel Name;
    private JLabel Patr;

    public MainForm() {
        collapsButton.addActionListener(new Action() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextArea.getText();
                String surname = surnameTextArea.getText();
                String patronymic = patronymicTextArea.getText();
                String fio = "";

                if (collapsButton.getText().equals("Expand")) {

                    String[] strings = surnameTextArea.getText().split(" ");
                    String snm = strings[0];
                    String nm = strings[1];
                    String p = "";
                    if (strings.length == 3) {
                        p = strings[2];
                        patronymicTextArea.setText(p);
                    }else {
                        patronymicTextArea.setText("");
                    }
                    boolean bool = true;
                    collapsButton.setText("Collapse");
                    surnameTextArea.setText(snm);
                    nameTextArea.setVisible(bool);
                    nameTextArea.setText(nm);
                    patronymicTextArea.setVisible(bool);
                    Surname.setText("Surname");
                    Name.setVisible(bool);
                    Patr.setVisible(bool);


                } else {
                    if (name.length() >= 3 & surname.length() >= 3) {
                        fio = surname + " " + name + " " + patronymic;
                        collapsButton.setText("Expand");
                        boolean bool = false;
                        surnameTextArea.setText(fio);
                        nameTextArea.setVisible(bool);
                        patronymicTextArea.setVisible(bool);
                        Surname.setText("ФИО");
                        Name.setVisible(bool);
                        Patr.setVisible(bool);
                    } else {
                        JOptionPane.showMessageDialog(
                                panel,
                                "Слишком мало букв)",
                                "Error",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }

                }

            }

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public boolean accept(Object sender) {
                return Action.super.accept(sender);
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }


}
