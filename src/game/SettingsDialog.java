package game;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * The SettingsDialog stores the default settings and the player added settings
 *
 * @author Manuel Adams
 * @since 2018-06-20
 */
public class SettingsDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JSlider sliderOpponentStartWaitTime;
    private JSlider sliderShotWaitTime;
    private JSlider sliderOpponentWaitTime;
    private JCheckBox checkBoxRandomOpponentWaitTime;
    private JCheckBox checkBoxAvoidCollisionWithObstacles;
    private JCheckBox checkBoxAvoidCollisionWithOpponent;
    private JTextField textFieldGridSizeY;
    private JTextField textFieldGridSizeX;
    private JTextField textFieldObstacleCount;
    private JTextField textFieldOpponentCountBegin;

    /**
     * Constructs the SettingsDialog
     *
     * @param gameWindow super class of the SettingsDialog
     * @param title      sets the title of the Dialog
     */
    SettingsDialog(GameWindow gameWindow, String title) {
        super(gameWindow, title);
        this.setLayout(new GridLayout(0, 2));
        addLabel("Wartezeit der Gegner zu Beginn:");
        sliderOpponentStartWaitTime = addSlider(Settings.getSettingAsInteger("opponentStartWaitTime"));
        addLabel("Verzoegerung eines Schusses:");
        sliderShotWaitTime = addSlider(Settings.getSettingAsInteger("shotWaitTime"));
        addLabel("Wartezeit eines Gegners vor jedem Schritt:");
        sliderOpponentWaitTime = addSlider(Settings.getSettingAsInteger("opponentWaitTime"));
        addLabel("");
        checkBoxRandomOpponentWaitTime = addCheckBox("Zufaellige Wartezeiten der Gegner", Settings.getSettingAsBoolean("randomOpponentWaitTime"));
        addLabel("");
        checkBoxAvoidCollisionWithObstacles = addCheckBox("Gegner meiden Kollision mit Hindernis", Settings.getSettingAsBoolean("avoidCollisionWithObstacles"));
        addLabel("");
        checkBoxAvoidCollisionWithOpponent = addCheckBox("Gegner meiden Kollision mit anderen Gegnern", Settings.getSettingAsBoolean("avoidCollisionWithOpponent"));
        addLabel("Anzahl Zeilen des Spielfelds:");
        textFieldGridSizeY = addTextField(Settings.getSetting("gridSizeY"));
        addLabel("Anzahl Spalten des Spielfelds:");
        textFieldGridSizeX = addTextField(Settings.getSetting("gridSizeX"));
        addLabel("Anzahl Hindernisse:");
        textFieldObstacleCount = addTextField(Settings.getSetting("obstacleCount"));
        addLabel("Anzahl der Gegner:");
        textFieldOpponentCountBegin = addTextField(Settings.getSetting("opponentCount"));
        addButton("Uebernehmen");
        addButton("Abbruch");
        this.pack();
        this.setLocationRelativeTo(gameWindow);
        this.setVisible(true);
    }

    private void addLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(300, 50));
        this.add(label);
    }

    private JSlider addSlider(int defaultValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10000, defaultValue);
        slider.setMajorTickSpacing(2000);
        slider.setMinorTickSpacing(500);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(300, 50));
        this.add(slider);
        return slider;
    }

    private JCheckBox addCheckBox(String text, boolean selected) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(selected);
        checkBox.setPreferredSize(new Dimension(300, 50));
        this.add(checkBox);
        return checkBox;
    }

    private JTextField addTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setPreferredSize(new Dimension(300, 50));
        this.add(textField);
        return textField;
    }

    private void addButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50));
        button.addActionListener(new SettingsListener(this));
        button.setActionCommand(text);
        this.add(button);

    }

    public JSlider getSliderOpponentStartWaitTime() {
        return sliderOpponentStartWaitTime;
    }

    public JSlider getSliderShotWaitTime() {
        return sliderShotWaitTime;
    }

    public JSlider getSliderOpponentWaitTime() {
        return sliderOpponentWaitTime;
    }

    public JCheckBox getCheckBoxRandomOpponentWaitTime() {
        return checkBoxRandomOpponentWaitTime;
    }

    public JCheckBox getCheckBoxAvoidCollisionWithObstacles() {
        return checkBoxAvoidCollisionWithObstacles;
    }

    public JCheckBox getCheckBoxAvoidCollisionWithOpponent() {
        return checkBoxAvoidCollisionWithOpponent;
    }

    public JTextField getTextFieldGridSizeY() {
        return textFieldGridSizeY;
    }

    public JTextField getTextFieldGridSizeX() {
        return textFieldGridSizeX;
    }

    public JTextField getTextFieldObstacleCount() {
        return textFieldObstacleCount;
    }

    public JTextField getTextFieldOpponentCountBegin() {
        return textFieldOpponentCountBegin;
    }
}

