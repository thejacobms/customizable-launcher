import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class deals with managing all
 * of the json and program data
 */
class DataManagement {

    /**
     * What this does is we assigned a type
     * field to the Program class that this
     * adapter can read from and distinguish
     * whether an object in the ProgramArrayList
     * is a website or not, because when they
     * are deserialized from json they can only
     * be read as Program objects natively
     */
    private static RuntimeTypeAdapterFactory<Program> adapter =
            RuntimeTypeAdapterFactory.of(Program.class, "type")
                    .registerSubtype(Website.class, "website")
                    .registerSubtype(Game.class, "game");

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapterFactory(adapter)
            .create();

    private static String directoryPath = System.getProperty("user.dir") + "\\Resources\\";
    private static String filePath = directoryPath + "Programs.json";

    private static ArrayList<Program> programArrayList;

    static {

        try {
            File dir = new File(directoryPath);
            File file = new File(filePath);

            // if a new directory is made
            if (dir.mkdirs()) {

                // if a new file is made
                if (file.createNewFile()) {

                    // initialize the programarraylist and save it
                    programArrayList = new ArrayList<>();
                    saveList();
                }
            } else {

                // if a new file is made
                // maybe it was deleted from the directory
                if (file.createNewFile()) {

                    programArrayList = new ArrayList<>();
                    saveList();
                }

                // if the file exists already then we want to
                // initialize the programarraylist to whatever
                // is saved in the json file
                programArrayList = gson.fromJson(new FileReader(filePath),
                        new TypeToken<ArrayList<Program>>(){}.getType());
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * This method goes through a check of whether or not the name
     * of the program being added already exists in another program.
     * If it does, then it will append a 0 at the end of the name until
     * it has a unique name, then adds the program to the programarraylist
     * and saves it.
     *
     * @param program The program being added into the programarraylist
     */
    static void addProgramToProgramArrayList(Program program) {

        while (hasName(program)) {

            String name = program.getName();
            name += 0;

            program.setName(name);
        }

        programArrayList.add(program);
        sortProgramArrayList();
    }

    static void replaceProgramInProgramArrayList(Program oldProgram, Program newProgram) {

        int index = programArrayList.indexOf(oldProgram);

        if (index > -1) {

            programArrayList.remove(index);
            programArrayList.add(newProgram);
            sortProgramArrayList();
        } else {

            System.out.println("Program not replaced.");
        }
    }

    static void removeProgramFromProgramArrayList(Program program) {

        programArrayList.remove(program);
        saveList();
    }

    /**
     * Uses binary search through the programarraylist
     * to see whether or not the program's name exists
     * in another program.
     *
     * @param program The program being checked.
     * @return true if the name exists
     */
    private static boolean hasName(Program program) {

        int high = programArrayList.size() - 1;
        int low = 0;

        int mid;

        boolean result = false;

        while (high >= low && !result) {

            mid = (high + low) / 2;
            Program target = programArrayList.get(mid);

            if (program.compareTo(target) > 0) {

                low = mid + 1;
            } else if (program.compareTo(target) < 0) {

                high = mid - 1;
            } else {

                result = true;
            }
        }

        return result;
    }

    /**
     * Uses insertion sorting to sort the programarraylist
     */
    private static void sortProgramArrayList() {

        for (int i = 1; i < programArrayList.size(); i++) {

            Program key = programArrayList.get(i);
            int j = i - 1;

            while (j >= 0 && programArrayList.get(j).compareTo(key) > 0) {

                programArrayList.set(j + 1, programArrayList.get(j));
                j--;
            }

            programArrayList.set(j + 1, key);
        }

        saveList();
    }

    private static void saveList() {

        String json = gson.toJson(programArrayList);

        try {

            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static ArrayList<Program> getProgramArrayList() {

        return programArrayList;
    }
}
