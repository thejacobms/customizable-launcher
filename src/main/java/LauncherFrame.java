import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * The frame of the launcher that holds
 * all the components
 *
 * The class here is all the default
 * components that are initialized on
 * startup
 */
class LauncherFrame extends JFrame {

    private static JPanel defaultLauncherPanel = new JPanel();
    private static AddNewProgramPanel addNewProgramPanel = new AddNewProgramPanel();

    private static JPanel mainIconPanel = new JPanel();
    static JPanel iconsPanel = new JPanel();

    LauncherFrame() {

        setTitle(ComponentsProperties.PROJECT_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(ComponentsProperties.FRAME_SIZE);
        setMinimumSize(ComponentsProperties.FRAME_SIZE);

        addComponentsToPane(getContentPane());
        createMenu();
    }

    private void addComponentsToPane(Container pane) {

        // === Icons Scroll Pane === //
        mainIconPanel.setLayout(new BoxLayout(mainIconPanel, BoxLayout.Y_AXIS));
        mainIconPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);

        // Icons panel
        iconsPanel.setLayout(new BoxLayout(iconsPanel, BoxLayout.Y_AXIS));
        iconsPanel.setPreferredSize(ComponentsProperties.ICON_PANEL_SIZE);
        iconsPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);

        // Add the icons to the icon panel
        this.addIconsToIconPanel();

        // Icons panel scroll pane
        JScrollPane iconScrollPane = new JScrollPane(iconsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        iconScrollPane.setLayout(new ScrollPaneLayout());
        iconScrollPane.setPreferredSize(ComponentsProperties.ICON_SCROLL_PANE_SIZE);
        iconScrollPane.setMinimumSize(ComponentsProperties.ICON_SCROLL_PANE_SIZE);
        mainIconPanel.add(iconScrollPane);

        // Add new program button
        JButton addNewProgramButton = new JButton("+");
        addNewProgramButton.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_BUTTON_SIZE);
        addNewProgramButton.setMaximumSize(ComponentsProperties.ADD_NEW_PROGRAM_BUTTON_SIZE);
        addNewProgramButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewProgramButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        addNewProgramButton.setBackground(ComponentsProperties.BUTTON_COLOR);
        addNewProgramButton.addActionListener(e -> {

            if (addNewProgramPanel.isDisplayable()) {

                pane.remove(addNewProgramPanel);
                pane.add(defaultLauncherPanel);
            } else {

                pane.removeAll();
                pane.add(mainIconPanel, BorderLayout.LINE_START);
                pane.add(addNewProgramPanel);
            }

            pane.revalidate();
            pane.repaint();
        });
        mainIconPanel.add(addNewProgramButton);
        pane.add(mainIconPanel, BorderLayout.LINE_START);
        // === Icons Scroll Pane === //

        // === Default Launcher Area === //
        defaultLauncherPanel.setLayout(new BoxLayout(defaultLauncherPanel, BoxLayout.Y_AXIS));

        // Launcher description text area
        JTextArea launcherDescriptionTextArea = new JTextArea();
        launcherDescriptionTextArea.setEditable(false);
        launcherDescriptionTextArea.setLineWrap(true);
        launcherDescriptionTextArea.setWrapStyleWord(true);
        launcherDescriptionTextArea.setPreferredSize(ComponentsProperties.LAUNCHER_PANEL_SIZE);
        launcherDescriptionTextArea.setText(ComponentsProperties.LAUNCHER_DESCRIPTION_TEXT_AREA_STRING);
        launcherDescriptionTextArea.setBackground(ComponentsProperties.BACKGROUND_COLOR);

        defaultLauncherPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        defaultLauncherPanel.add(launcherDescriptionTextArea);
        pane.add(defaultLauncherPanel, BorderLayout.CENTER);
        // === Default Launcher Area === //
    }

    void addIconsToIconPanel() {

        Container pane = this.getContentPane();
        iconsPanel.removeAll();

        ArrayList<Program> programArrayList = DataManagement.getProgramArrayList();

        // Go through each program in the programarraylist and
        // assign an icon for each program
        for (Program program : programArrayList) {

            JButton iconButton = new JButton(program.getName());

            iconButton.setPreferredSize(ComponentsProperties.ICON_BUTTON_SIZE);
            iconButton.setMinimumSize(ComponentsProperties.ICON_BUTTON_SIZE);
            iconButton.setMaximumSize(ComponentsProperties.ICON_BUTTON_SIZE);
            iconButton.setMargin(new Insets(0, 0, 0, 0));

            iconButton.setBackground(ComponentsProperties.BUTTON_COLOR);

            iconButton.addActionListener(e -> {

                pane.removeAll();
                pane.add(mainIconPanel);
                pane.add(new DisplayProgramPanel(program));

                pane.revalidate();
                pane.repaint();
            });

            iconButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    // if it's a right click
                    if (e.getButton() == MouseEvent.BUTTON3) {

                        JPopupMenu popupMenu = new JPopupMenu();

                        JMenuItem deleteItem = new JMenuItem("Delete");
                        deleteItem.addActionListener(e1 -> {
                            DataManagement.removeProgramFromProgramArrayList(program);
                            iconsPanel.remove(iconButton);

                            pane.removeAll();
                            pane.add(mainIconPanel, BorderLayout.LINE_START);
                            pane.add(defaultLauncherPanel);

                            pane.revalidate();
                            pane.repaint();
                        });

                        JMenuItem editItem = new JMenuItem("Edit");
                        editItem.addActionListener(e2 -> {

                            EditProgramFrame editProgramFrame = new EditProgramFrame(program);
                            editProgramFrame.pack();
                            editProgramFrame.setVisible(true);
                        });
                        popupMenu.add(deleteItem);
                        popupMenu.add(editItem);

                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                        // if its a middle click
                    } else if (e.getButton() == MouseEvent.BUTTON2) {

                        // just delete it
                        DataManagement.removeProgramFromProgramArrayList(program);
                        iconsPanel.remove(iconButton);

                        pane.removeAll();
                        pane.add(mainIconPanel, BorderLayout.LINE_START);
                        pane.add(defaultLauncherPanel);

                        pane.revalidate();
                        pane.repaint();
                    }
                }
            });

            iconsPanel.add(iconButton);
            iconsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            iconsPanel.revalidate();
            iconsPanel.repaint();
        }
    }

    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem viewFolder = new JMenuItem("View Folder");
        viewFolder.addActionListener(e -> {

            try {

                Desktop.getDesktop().open(new File(System.getProperty("user.dir")));
            } catch (IOException e1) {

                JOptionPane.showMessageDialog(this, "Failed to open file explorer.");
            }
        });
        fileMenu.add(viewFolder);

        JMenuItem viewRepository = new JMenuItem("View Repository");
        viewRepository.addActionListener(e -> {

            try {

                Desktop.getDesktop().browse(URI.create(ComponentsProperties.REPOSITORY_LINK));
            } catch (IOException e1) {

                JOptionPane.showMessageDialog(this, "Failed to open repository.");
            }
        });
        fileMenu.add(viewRepository);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

}
