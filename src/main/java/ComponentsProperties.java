import java.awt.*;

class ComponentsProperties {

    private static final double GLOBAL_DIMENSION_SCALE = 0.9;
    private static final double ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE = 1.0;

    private static final Insets ADD_NEW_PROGRAM_PANEL_INSETS = new Insets(1, 1, 1, 1);

    static final Dimension ICON_BUTTON_SIZE = new Dimension((int) (85 * GLOBAL_DIMENSION_SCALE), (int) (50 * GLOBAL_DIMENSION_SCALE));
    static final Dimension ICON_PANEL_SIZE = new Dimension((int) (100 * GLOBAL_DIMENSION_SCALE) , (int) ((DataManagement.getProgramArrayList().size() * 55) * GLOBAL_DIMENSION_SCALE));
    static final Dimension ICON_SCROLL_PANE_SIZE = new Dimension((int) (100 * GLOBAL_DIMENSION_SCALE), (int) (400 * GLOBAL_DIMENSION_SCALE));
    static final Dimension ADD_NEW_PROGRAM_BUTTON_SIZE = new Dimension((int) (100 * GLOBAL_DIMENSION_SCALE), (int) (50 * GLOBAL_DIMENSION_SCALE));
    static final Dimension LAUNCHER_PANEL_SIZE = new Dimension((int) (700 * GLOBAL_DIMENSION_SCALE), (int) (600 * GLOBAL_DIMENSION_SCALE));
    static final Dimension FRAME_SIZE = new Dimension((int) (800 * GLOBAL_DIMENSION_SCALE), (int) (600 * GLOBAL_DIMENSION_SCALE));
    static final Dimension ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE = new Dimension((int) (200 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE), (int) (25 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE));
    static final Dimension ADD_NEW_PROGRAM_PANEL_LABEL_SIZE = new Dimension((int) (75 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE), (int) (25 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE));
    static final Dimension ADD_NEW_PROGRAM_PANEL_DESCRIPTION_TEXT_AREA_SIZE = new Dimension((int) (200 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE), (int) (100 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE));
    static final Dimension ADD_NEW_PROGRAM_PANEL_PATH_TEXT_AREA_SIZE = new Dimension((int) (200 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE), (int) (35 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE));
    static final Dimension SCROLL_BAR_SIZE = new Dimension((int) (0 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE), (int) (15 * GLOBAL_DIMENSION_SCALE * ADD_NEW_PROGRAM_PANEL_DIMENSION_SCALE));
    static final Dimension PROGRAM_DISPLAY_PANEL_DESCRIPTION_TEXT_AREA_SIZE = new Dimension((int) (500 * GLOBAL_DIMENSION_SCALE), (int) (450 * GLOBAL_DIMENSION_SCALE));

    static final GridBagConstraints WEBSITE_CHECK_BOX_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(0, 0).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints GAME_CHECK_BOX_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(0, 1).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints NAME_LABEL_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(1, 0).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints DESCRIPTION_LABEL_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(1, 1).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints CUSTOM_LABEL_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(1, 2).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints NAME_TEXT_AREA_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 0).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints DESCRIPTION_TEXT_AREA_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 1).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints CUSTOM_TEXT_AREA_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 2).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints WEBSITE_SUBMIT_BUTTON_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 3).setDimensions(1, 2).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints GAME_SUBMIT_BUTTON_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 4).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();
    static final GridBagConstraints FIND_FILE_BUTTON_GBC = GridBagConstraintsBuilder.Builder.newInstance().setGrid(2, 3).setAnchor(GridBagConstraints.LINE_START).setInsets(ADD_NEW_PROGRAM_PANEL_INSETS).build();

    static final Color BUTTON_COLOR = Color.PINK;
    static final Color BACKGROUND_COLOR = Color.ORANGE;

    static final String LAUNCHER_DESCRIPTION_TEXT_AREA_STRING = "Welcome to my Customizable Launcher!\n\n" +
                    "Press the + in the bottom left to add new programs to the launcher, " +
                    "then click on the icon then launch to launch your program!\n\n" +
                    "If you wish to delete or edit a program, right click on the icon " +
                    "of the program or just press the middle mouse button on the icon to " +
                    "delete the icon.";
    static final String PROJECT_NAME = "Customizable Launcher";
    static final String REPOSITORY_LINK = "https://github.com/thejacobms/customizable-launcher/";
}
