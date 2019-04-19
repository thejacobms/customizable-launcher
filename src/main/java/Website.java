/**
 * Child class of Program
 *
 * The Website class creates objects
 * that are for launching websites
 */
class Website extends Program {

    private String url;

    Website() {

        this.type = "website";
    }

    Website(String name, String description, String url) {

        super("website", name, description);

        this.url = url;

        DataManagement.addProgramToProgramArrayList(this);
    }

    Website(String name, String description, String url, boolean isAdded) {

        super("website", name, description);

        this.url = url;

        if (isAdded) {

            DataManagement.addProgramToProgramArrayList(this);
        }
    }
    String getURL() {

        return this.url;
    }
}
