import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

    class DisplayProgramPanel extends JPanel {

    DisplayProgramPanel(Program program) {

        setPreferredSize(ComponentsProperties.LAUNCHER_PANEL_SIZE);
        setMinimumSize(ComponentsProperties.LAUNCHER_PANEL_SIZE);
        setMaximumSize(ComponentsProperties.LAUNCHER_PANEL_SIZE);
        setBackground(ComponentsProperties.BACKGROUND_COLOR);

        addComponentsToPanel(program);
    }

    private void addComponentsToPanel(Program program) {

        JPanel programPanel = new JPanel();
        programPanel.setLayout(new BoxLayout(programPanel, BoxLayout.Y_AXIS));

        // name label
        JLabel nameLabel = new JLabel(program.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        programPanel.add(nameLabel);
        programPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // description text area
        JTextArea descriptionTextArea = new JTextArea(program.getDescription());
        descriptionTextArea.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setEditable(false);

        // description text pane
        JScrollPane descriptionTextAreaScrollPane = new JScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionTextAreaScrollPane.setPreferredSize(ComponentsProperties.PROGRAM_DISPLAY_PANEL_DESCRIPTION_TEXT_AREA_SIZE);
        descriptionTextAreaScrollPane.setMinimumSize(ComponentsProperties.PROGRAM_DISPLAY_PANEL_DESCRIPTION_TEXT_AREA_SIZE);
        descriptionTextAreaScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionTextAreaScrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        programPanel.add(descriptionTextAreaScrollPane);
        programPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton launchButton = new JButton("Launch");

        if (program instanceof Website) {

            Website website = (Website) program;
            launchButton.addActionListener(e -> {

                String url = website.getURL();

                boolean startsWith = url.startsWith("http://") || url.startsWith("https://");
                if (!startsWith) {

                    System.out.println(url + " does not start with http:// or https://.");
                    url = "http://" + url;
                    System.out.println(url);
                }

                URI uri = URI.create(url);

                try {

                    Desktop.getDesktop().browse(uri);
                } catch (IOException e1) {

                    JOptionPane.showMessageDialog(this, "Failed to launch " + website.getURL()
                            + ". Make sure the website has a proper domain such as .com, .org...");
                }
            });

        } else if (program instanceof Game) {

            Game game = (Game) program;
            launchButton.addActionListener(e -> {

                Runtime runtime = Runtime.getRuntime();

                try {

                    runtime.exec(game.getFilePath());
                } catch (IOException e2) {

                    JOptionPane.showMessageDialog(this, "Failed to open " + game.getName()
                            + ". Maybe the file path is incorrect?");
                }
            });
        }

        launchButton.setBackground(ComponentsProperties.BUTTON_COLOR);
        launchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        launchButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        programPanel.add(launchButton);
        programPanel.setBackground(ComponentsProperties.BACKGROUND_COLOR);
        this.add(programPanel);
    }
}
