package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SettingsListener saves the settings
 *
 * @author Manuel Adams
 * @since 2018-06-20
 */
public class SettingsListener implements ActionListener {
    private SettingsDialog settingsDialog;

    /**
     * Constructs the SettingsListener
     *
     * @param settingsDialog the owner of the Listener
     */
    SettingsListener(SettingsDialog settingsDialog) {
        this.settingsDialog = settingsDialog;
    }

    /**
     * saves or discards the settings
     *
     * @param e action event for the save/discard Buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Uebernehmen")) {
            Settings.setSetting("opponentStartWaitTime", Integer.toString(settingsDialog.getSliderOpponentStartWaitTime().getValue()));
            Settings.setSetting("shotWaitTime", Integer.toString(settingsDialog.getSliderShotWaitTime().getValue()));
            Settings.setSetting("opponentWaitTime", Integer.toString(settingsDialog.getSliderOpponentWaitTime().getValue()));
            Settings.setSetting("randomOpponentWaitTime", Boolean.toString(settingsDialog.getCheckBoxRandomOpponentWaitTime().isSelected()));
            Settings.setSetting("avoidCollisionWithObstacles", Boolean.toString(settingsDialog.getCheckBoxAvoidCollisionWithObstacles().isSelected()));
            Settings.setSetting("avoidCollisionWithOpponent", Boolean.toString(settingsDialog.getCheckBoxAvoidCollisionWithOpponent().isSelected()));
            Settings.setSetting("gridSizeY", settingsDialog.getTextFieldGridSizeY().getText());
            Settings.setSetting("gridSizeX", settingsDialog.getTextFieldGridSizeX().getText());
            Settings.setSetting("obstacleCount", settingsDialog.getTextFieldObstacleCount().getText());
            Settings.setSetting("opponentCountBegin", settingsDialog.getTextFieldOpponentCountBegin().getText());
            settingsDialog.dispose();
        } else if (e.getActionCommand().equals("Abbruch")) {
            settingsDialog.dispose();
        }
    }
}
