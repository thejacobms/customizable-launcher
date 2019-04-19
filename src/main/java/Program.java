/**
 * The Parent class for both types
 * of programs
 */
class Program implements Comparable<Program> {

    private String name;
    private String description;
    String type;

    Program() { }

    Program(String type, String name, String description) {

        this.type = type;
        this.name = name;
        this.description = description;
    }

    String getName() {

        return this.name;
    }

    String getDescription() {

        return this.description;
    }

    void setName(String name) {

        this.name = name;
    }

    public int compareTo(Program program) {

        String comparator = this.getName().toLowerCase();
        String target = program.getName().toLowerCase();

        if (!comparator.equals(target)) {

            if (comparator.compareTo(target) > 0) {

                return 1;
            } else {

                return -1;
            }

        } else {

            return 0;
        }
    }
}
