package game;

import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Toolbar includes the AmmoPanel, the ScoreBoard, the SettingsDialog, the Progressbar and a new GameButton
 *
 * @author Manuel Adams
 * @since 2017-01-16
 */
public class Toolbar extends JToolBar {
    private GameWindow gameWindow;
    private JTextField scoreTextField;
    private JButton newGameButton;
    private JProgressBar gameProgressBar;
    private AmmoPanel[] ammoPanels;
    private JPanel ammoPanel;

    /**
     * Constructs the Toolbar
     *
     * @param gameWindow with the necessary methods to construct the Toolbar properly
     */
    Toolbar(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 5));
        setPreferredSize(new Dimension(500, 50));
        setFloatable(false);
        newGameButton = new JButton("Neues Spiel");
        newGameButton.addActionListener(e -> gameWindow.startGame());
        add(newGameButton);
        scoreTextField = new JTextField("0");
        scoreTextField.setBorder(BorderFactory.createTitledBorder("Punktestand"));
        scoreTextField.setEditable(false);
        add(scoreTextField);
        ammoPanel = new JPanel();
        initMunitionDisplay();
        JPanel gameProgressTextField = new JPanel(new BorderLayout());
        gameProgressTextField.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
        gameProgressBar = new JProgressBar(0);
        gameProgressBar.setStringPainted(true);
        gameProgressTextField.add(gameProgressBar);
        add(gameProgressTextField);
        JButton settingsButton = new JButton("Einstellungen");
        settingsButton.setEnabled(true);
        settingsButton.addActionListener(e -> new SettingsDialog(gameWindow, "Settings"));
        add(settingsButton);
        setVisible(true);
    }

    /**
     * invalidates deprecated toolbar
     */
    public void invalidateToolbar() {
        newGameButton.setEnabled(false);
        gameProgressBar.setValue(0);
        InvalidateAmmoPanel();
        int startingEnemies = gameWindow.getDionaRapModel().getOpponentCount();
        gameWindow.getDionaRapModel().addModelChangedEventListener(new DionaRapListener() {
            /**
             * fills the progressBar and Scoreboard
             *
             * @param e action event for changed DionaRapModel
             */
            @Override
            public void modelChanged(DionaRapChangedEvent e) {
                scoreTextField.setText(Integer.toString(gameWindow.getDionaRapModel().getScore()));
                gameProgressBar.setValue(100 - (100 * gameWindow.getDionaRapModel().getOpponentCount()) / startingEnemies);
                InvalidateAmmoPanel();
            }

            /**
             * checks if the game is still in progress
             *
             * @param gameStatusEvent action event for gamestatus changes
             */
            @Override
            public void statusChanged(GameStatusEvent gameStatusEvent) {
                newGameButton.setEnabled(gameWindow.getDionaRapModel().isGameOver() ||
                        gameWindow.getDionaRapModel().isGameWon());
                scoreTextField.setText(Integer.toString(gameWindow.getDionaRapModel().getScore()));
            }
        });
    }

    private void initMunitionDisplay() {
        ammoPanel.setLayout(new GridLayout(1, 3, 10, 0));
        ammoPanel.setBorder(BorderFactory.createTitledBorder("Munition"));
        ammoPanels = new AmmoPanel[3];

        for (int i = 0; i < 3; i++) {
            ammoPanels[i] = new AmmoPanel(gameWindow, i);
            ammoPanels[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
            ammoPanel.add(ammoPanels[i]);
            add(ammoPanel);
        }
    }

    private void InvalidateAmmoPanel() {
        for (AmmoPanel ammoPanel : ammoPanels) {
            ammoPanel.repaint();
        }
    }

    public JPanel getAmmoPanel() {
        return ammoPanel;
    }
}
