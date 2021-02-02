package game;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.Ammo;
import navigation.NavigationWindow;
import de.fhwgt.dionarap.model.data.MTConfiguration;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.util.Objects;

/**
 * GameWindow handles the game start and restart and validation of the Toolbar, AmmoBar
 *
 * @author Manuel Adams
 * @since 2017-10-25
 */
public class GameWindow extends JFrame {
    private DionaRapModel dionaRapModel;
    private DionaRapController dionaRapController;
    private GameBoard gameBoard;
    private NavigationWindow navigationWindow;
    private PopupMenu popupMenu;
    private Toolbar toolbar;
    private boolean run = false;
    private String theme = "Alien";

    /**
     * Constructs the GameWindow
     */
    public GameWindow() {
        setTitle("DionaRap");
        Settings.setDefaultSettings();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Menubar menubar = new Menubar(this);
        setJMenuBar((menubar));
        toolbar = new Toolbar(this);
        add(toolbar, BorderLayout.NORTH);
        gameBoard = new GameBoard(this, Settings.getSettingAsInteger("gridSizeX"), Settings.getSettingAsInteger("gridSizeY"));
        add(gameBoard, BorderLayout.CENTER);
        navigationWindow = new NavigationWindow(this);
        popupMenu = new PopupMenu(this);
        addComponentListener(new WindowMoveListener());
        addKeyListener(new KeyControlListener(this));

        startGame();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    /**
     * handles the whole gamestart redrawing the field revalidating the toolbar and adding the listeners
     */
    public void startGame() {
        remove(gameBoard);
        gameBoard = new GameBoard(this, Settings.getSettingAsInteger("gridSizeX"), Settings.getSettingAsInteger("gridSizeY"));
        add(gameBoard, BorderLayout.CENTER);
        dionaRapModel = new DionaRapModel(Settings.getSettingAsInteger("gridSizeY"), Settings.getSettingAsInteger("gridSizeX"), Settings.getSettingAsInteger("opponentCount"), Settings.getSettingAsInteger("obstacleCount"));
        dionaRapModel.addModelChangedEventListener(new GameStatusListener(this));
        dionaRapController = new DionaRapController(dionaRapModel);

        dionaRapModel.setShootAmount(3);
        int ammocount = 4;
        spawnAmmo(ammocount);

        MTConfiguration mtConfiguration = new MTConfiguration();
        mtConfiguration.setAlgorithmAStarActive(true);
        mtConfiguration.setMinimumTime(800);
        mtConfiguration.setShotGetsOwnThread(true);
        mtConfiguration.setAvoidCollisionWithObstacles(Settings.getSettingAsBoolean("avoidCollisionWithObstacles"));
        mtConfiguration.setAvoidCollisionWithOpponent(Settings.getSettingAsBoolean("avoidCollisionWithOpponent"));
        mtConfiguration.setOpponentStartWaitTime(Settings.getSettingAsInteger("opponentStartWaitTime"));
        mtConfiguration.setOpponentWaitTime(Settings.getSettingAsInteger("opponentWaitTime"));
        mtConfiguration.setShotWaitTime(Settings.getSettingAsInteger("shotWaitTime"));
        mtConfiguration.setRandomOpponentWaitTime(Settings.getSettingAsBoolean("randomOpponentWaitTime"));
        mtConfiguration.setDynamicOpponentWaitTime(false);
        dionaRapController.setMultiThreaded(mtConfiguration);

        toolbar.invalidateToolbar();
        gameBoard.drawField();
    }

    /**
     * changes the lookAndFeel
     *
     * @param lookAndFeel new lookAndFeel
     */
    public void setGameLookAndFeel(String lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
            SwingUtilities.updateComponentTreeUI(navigationWindow);
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
        pack();
        requestFocus();
    }

    private void spawnAmmo(int ammocount) {
        for (int i = 0; i < ammocount; i++) {
            dionaRapModel.addAmmo(new Ammo());
        }
    }

    /**
     * changes the position of the ToolBar
     *
     * @param position the new position for the ToolBar
     */
    public void setToolbar(String position) {
        remove(toolbar);
        if (Objects.equals(position, "south")) {
            add(toolbar, BorderLayout.SOUTH);
        }
        if (Objects.equals(position, "north")) {
            add(toolbar, BorderLayout.NORTH);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * handles the start of the blink thread and the shot
     */
    public void shoot() {
        BlinkThread blinkThread = new BlinkThread(this);
        if (dionaRapModel.getShootAmount() == 0 && !run) {
            run = true;
            blinkThread.start();
        }
        this.dionaRapController.shoot();
    }

    public void setThreatState(boolean state) {
        run = state;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public NavigationWindow getNavigationWindow() {
        return navigationWindow;
    }

    public PopupMenu getPopupMenu() {
        return popupMenu;
    }

    public DionaRapController getDionaRapController() {
        return dionaRapController;
    }

    public DionaRapModel getDionaRapModel() {
        return dionaRapModel;
    }

    public String getTheme() {
        return theme;
    }

    /**
     * changes the theme and redraws the field
     *
     * @param newTheme the new theme
     */
    public void setTheme(String newTheme) {
        theme = newTheme;
        getGameBoard().drawField();
    }
}
