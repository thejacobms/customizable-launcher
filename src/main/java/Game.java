class Game extends Program {

    private String filePath;

    Game () {

        this.type = "game";
    }

    Game (String name, String description, String filePath) {

        super("game", name, description);
        this.filePath = filePath;

        DataManagement.addProgramToProgramArrayList(this);
    }

    Game (String name, String description, String filePath, boolean isAdded) {

        super("game", name, description);
        this.filePath = filePath;

        if (isAdded) {

            DataManagement.addProgramToProgramArrayList(this);
        }
    }

    String getFilePath() {

        return this.filePath;
    }
}
