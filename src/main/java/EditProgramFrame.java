import javax.swing.*;
import java.awt.*;

/**
 * A secondary frame that opens up
 * when the user wants to edit a program
 */
class EditProgramFrame extends JFrame {

    private Program program;
    private static JPanel editProgramPanel = new JPanel(new GridBagLayout());

    EditProgramFrame(Program program) {

        this.program = program;

        setTitle("Editing " + program.getName());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPanel(editProgramPanel);
        editProgramPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        add(editProgramPanel);
    }

    private void addComponentsToPanel(JPanel panel) {

        JLabel label;

        JTextArea nameTextArea;
        JTextArea descriptionTextArea;

        final JTextArea urlTextArea = new JTextArea();
        final JTextArea filePathTextArea = new JTextArea();

        JScrollPane scrollPane;
        JButton button;

        // Name label
        label = new JLabel("Name");
        label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
        panel.add(label, ComponentsProperties.NAME_LABEL_GBC);

        // Description label
        label = new JLabel("Description");
        label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
        panel.add(label, ComponentsProperties.DESCRIPTION_LABEL_GBC);

        // URL label
        if (program instanceof Website) {

            label = new JLabel("URL");
            label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
            panel.add(label, ComponentsProperties.CUSTOM_LABEL_GBC);
        }

        // File path label
        if (program instanceof Game) {

            label = new JLabel("File Path");
            label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
            panel.add(label, ComponentsProperties.CUSTOM_LABEL_GBC);
        }

        // Name text area/scroll pane
        nameTextArea = new JTextArea(program.getName());
        nameTextArea.setLineWrap(true);
        scrollPane = new JScrollPane(nameTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
        panel.add(scrollPane, ComponentsProperties.NAME_TEXT_AREA_GBC);

        // Description text area/scroll pane
        descriptionTextArea = new JTextArea(program.getDescription());
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DESCRIPTION_TEXT_AREA_SIZE);
        panel.add(scrollPane, ComponentsProperties.DESCRIPTION_TEXT_AREA_GBC);

        // URL text area/scroll pane
        if (program instanceof Website) {

            urlTextArea.setText(((Website) program).getURL());
            scrollPane = new JScrollPane(urlTextArea,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
            scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            panel.add(scrollPane, ComponentsProperties.CUSTOM_TEXT_AREA_GBC);
        }

        // File path text area/scroll pane
        if (program instanceof Game) {

            filePathTextArea.setText(((Game) program).getFilePath());
            scrollPane = new JScrollPane(filePathTextArea,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
            scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            panel.add(scrollPane, ComponentsProperties.CUSTOM_TEXT_AREA_GBC);
        }

        // Website submit button
        if (program instanceof Website) {

            button = new JButton("Submit");
            button.addActionListener(e -> {

                String name = nameTextArea.getText();
                String description = descriptionTextArea.getText();
                String url = urlTextArea.getText();

                if (name.equals("")
                || description.equals("")
                || url.equals("")) {

                    JOptionPane.showMessageDialog(panel, "Please fill out the fields.");
                } else {

                    Website oldWebsite = (Website) program;
                    Website newWebsite = new Website(name, description, url, false);
                    DataManagement.replaceProgramInProgramArrayList(oldWebsite, newWebsite);
                    Main.launcherFrame.addIconsToIconPanel();
                }
            });
            button.setBackground(ComponentsProperties.BUTTON_COLOR);
            button.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            panel.add(button, ComponentsProperties.WEBSITE_SUBMIT_BUTTON_GBC);
        }

        // Game submit button and file button
        if (program instanceof Game) {

            button = new JButton("Find File");
            final FileDialog fileDialog = new FileDialog(new JFrame());

            button.addActionListener(e -> {

                fileDialog.setVisible(true);

                String file = fileDialog.getDirectory() + fileDialog.getFile();
                String name = fileDialog.getFile();

                if (name != null && name.endsWith(".exe")) {

                    int end = name.indexOf(".exe");
                    name = name.substring(0, end);

                    nameTextArea.setText(name);
                    filePathTextArea.setText(file);
                }
            });
            button.setBackground(ComponentsProperties.BUTTON_COLOR);
            button.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            panel.add(button, ComponentsProperties.FIND_FILE_BUTTON_GBC);

            button = new JButton("Submit");
            button.addActionListener(e -> {

                String name = nameTextArea.getText();
                String description = descriptionTextArea.getText();
                String filePath = filePathTextArea.getText();

                if (name.equals("")
                        || description.equals("")
                        || filePath.equals("")) {

                    JOptionPane.showMessageDialog(panel, "Please fill out the fields.");
                } else {

                    Game oldGame = (Game) program;
                    Game newGame = new Game(name, description, filePath, false);
                    DataManagement.replaceProgramInProgramArrayList(oldGame, newGame);
                    Main.launcherFrame.addIconsToIconPanel();
                    this.dispose();
                }
            });
            button.setBackground(ComponentsProperties.BUTTON_COLOR);
            button.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            panel.add(button, ComponentsProperties.GAME_SUBMIT_BUTTON_GBC);
        }
    }
}
