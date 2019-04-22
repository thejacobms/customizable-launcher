import javax.swing.*;
import java.awt.*;

class AddNewProgramPanel extends JPanel {

    private static JPanel checkBoxPanel =   new JPanel(new GridBagLayout());
    private static JPanel mainPanel =       new JPanel(new GridBagLayout());

    private static JCheckBoxMenuItem websiteCheckBox =  new JCheckBoxMenuItem("Website");
    private static JCheckBoxMenuItem gameCheckBox =     new JCheckBoxMenuItem("Game");

    AddNewProgramPanel() {

        setLayout(new GridBagLayout());
        setPreferredSize(ComponentsProperties.LAUNCHER_PANEL_SIZE);
        setBackground(ComponentsProperties.BACKGROUND_COLOR);
        addProgramQuery();
    }

    private static void addComponentsToMainPanel(Program program) {

        final JTextArea nameTextArea;
        final JTextArea descriptionTextArea;
        final JTextArea urlTextArea;
        final JTextArea filePathTextArea;

        JLabel label;
        JScrollPane scrollPane;

        final JButton submitButton;
        final JButton findFileButton;

        final FileDialog fileDialog = new FileDialog(new JFrame());

        // Name label
        label = new JLabel("Name");
        label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
        mainPanel.add(label, ComponentsProperties.NAME_LABEL_GBC);

        // Description label
        label = new JLabel("Description");
        label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
        mainPanel.add(label, ComponentsProperties.DESCRIPTION_LABEL_GBC);

        // Name text area
        nameTextArea = new JTextArea();
        nameTextArea.setEditable(true);
        nameTextArea.setLineWrap(true);

        // Name scroll pane
        scrollPane = new JScrollPane(nameTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
        scrollPane.getHorizontalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
        mainPanel.add(scrollPane, ComponentsProperties.NAME_TEXT_AREA_GBC);

        // Description text area
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(true);
        descriptionTextArea.setLineWrap(true);

        // Description scroll pane
        scrollPane = new JScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DESCRIPTION_TEXT_AREA_SIZE);
        scrollPane.getHorizontalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
        mainPanel.add(scrollPane, ComponentsProperties.DESCRIPTION_TEXT_AREA_GBC);
        if (program instanceof Website) {

            // URL label
            label = new JLabel("URL");
            label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
            mainPanel.add(label, ComponentsProperties.CUSTOM_LABEL_GBC);

            // URL text area
            urlTextArea = new JTextArea();
            urlTextArea.setEditable(true);

            // URL scroll pane
            scrollPane = new JScrollPane(urlTextArea,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            scrollPane.getHorizontalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
            mainPanel.add(scrollPane, ComponentsProperties.CUSTOM_TEXT_AREA_GBC);

            // Submit button
            submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> {

                String name = nameTextArea.getText();
                String description = descriptionTextArea.getText();
                String url = urlTextArea.getText();

                if (name.equals("")
                        || description.equals("")
                        || url.equals("")) {

                    JOptionPane.showMessageDialog(mainPanel, "Please fill out the fields");
                } else {

                    new Website(name, description, url);

                    nameTextArea.setText("");
                    descriptionTextArea.setText("");
                    urlTextArea.setText("");

                    Main.launcherFrame.addIconsToIconPanel();
                }
            });
            submitButton.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            submitButton.setBackground(ComponentsProperties.BUTTON_COLOR);
            mainPanel.add(submitButton, ComponentsProperties.WEBSITE_SUBMIT_BUTTON_GBC);
            mainPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        } else if (program instanceof Game) {

            // File path label
            label = new JLabel("File Path");
            label.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_LABEL_SIZE);
            mainPanel.add(label, ComponentsProperties.CUSTOM_LABEL_GBC);

            // File path text area
            filePathTextArea = new JTextArea();
            filePathTextArea.setEditable(false);

            // File path scroll pane
            JScrollPane filePathScrollPane = new JScrollPane(filePathTextArea,
                    JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            filePathScrollPane.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_PATH_TEXT_AREA_SIZE);
            filePathScrollPane.setMinimumSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_PATH_TEXT_AREA_SIZE);
            filePathScrollPane.getHorizontalScrollBar().setPreferredSize(ComponentsProperties.SCROLL_BAR_SIZE);
            mainPanel.add(filePathScrollPane, ComponentsProperties.CUSTOM_TEXT_AREA_GBC);

            // File path submit button
            submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> {

                if (nameTextArea.getText().equals("")
                        || descriptionTextArea.getText().equals("")
                        || filePathTextArea.getText().equals("")) {

                    JOptionPane.showMessageDialog(mainPanel, "Please fill out the fields.");
                } else {

                    new Game(nameTextArea.getText(),
                            descriptionTextArea.getText(),
                            filePathTextArea.getText());

                    nameTextArea.setText("");
                    descriptionTextArea.setText("");
                    filePathTextArea.setText("");

                    Main.launcherFrame.addIconsToIconPanel();
                }
            });
            submitButton.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            submitButton.setBackground(ComponentsProperties.BUTTON_COLOR);
            mainPanel.add(submitButton, ComponentsProperties.GAME_SUBMIT_BUTTON_GBC);

            // Find file button
            findFileButton = new JButton("Find File");
            findFileButton.addActionListener(e -> {

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
            findFileButton.setPreferredSize(ComponentsProperties.ADD_NEW_PROGRAM_PANEL_DEFAULT_COMPONENT_SIZE);
            findFileButton.setBackground(ComponentsProperties.BUTTON_COLOR);

            mainPanel.add(findFileButton, ComponentsProperties.FIND_FILE_BUTTON_GBC);
            mainPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        }
    }

    private void addProgramQuery() {

        // Website check box
        websiteCheckBox.addActionListener(e -> {

            if (gameCheckBox.isSelected()) {

                gameCheckBox.setSelected(false);
                websiteCheckBox.setSelected(true);
                refreshMainPanel(new Website());
            } else {

                websiteCheckBox.setSelected(true);
                refreshMainPanel(new Website());
            }
        });
        websiteCheckBox.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        checkBoxPanel.add(websiteCheckBox, ComponentsProperties.WEBSITE_CHECK_BOX_GBC);

        // Game check box
        gameCheckBox.addActionListener(e -> {

            if (websiteCheckBox.isSelected()) {

                websiteCheckBox.setSelected(false);
                gameCheckBox.setSelected(true);
                refreshMainPanel(new Game());
            } else {

                gameCheckBox.setSelected(true);
                refreshMainPanel(new Game());
            }
        });

        gameCheckBox.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        checkBoxPanel.add(gameCheckBox, ComponentsProperties.GAME_CHECK_BOX_GBC);

        checkBoxPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        this.add(checkBoxPanel);
    }

    private void refreshMainPanel(Program program) {

        if (program instanceof Website) {

            Website website = (Website) program;
            mainPanel.removeAll();
            addComponentsToMainPanel(website);
        } else if (program instanceof Game) {

            Game game = (Game) program;
            mainPanel.removeAll();
            addComponentsToMainPanel(game);
        }

        this.add(checkBoxPanel);
        this.add(mainPanel);

        this.revalidate();
        this.repaint();
    }
}
