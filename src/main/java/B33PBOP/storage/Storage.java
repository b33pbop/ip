package B33PBOP.storage;

import B33PBOP.task.Task;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * B33PBOP.storage.Storage handles storage for B33PBOP.ui.B33PBOP.
 * On first usage, it creates a new DIRECTORY with a file B33PBOP.ui.B33PBOP.txt which stores tasks in String format.
 * Subsequent usage overwrites the existing file to update storage.
 */
public class Storage {
    private final File STORAGE_FILE;

    /**
     * Initializes DIRECTORY and STORAGE_FILE variables.
     * Creates a new directory and file if they do not exist.
     * @throws IOException If storage creation fails
     */
    public Storage() throws IOException {
        File DIRECTORY = new File("data");
        DIRECTORY.mkdirs();

        STORAGE_FILE = new File(DIRECTORY, "B33PBOP.ui.B33PBOP.txt");
        if (!STORAGE_FILE.exists()) {
            boolean isCreated = STORAGE_FILE.createNewFile();
            if (!isCreated) {
                throw new IOException("Failed to create storage file");
            }
        }
    }

    public File getStorageFile() {
        return this.STORAGE_FILE;
    }

    /**
     * Updates B33PBOP.ui.B33PBOP.txt file with a new task list.
     * @param tasks List of tasks added by the user.
     * @throws IOException If update fails.
     */
    public void updateStorage(List<Task> tasks) throws IOException {
        try (PrintWriter pw = new PrintWriter(this.STORAGE_FILE)) {
            for (Task t : tasks) {
                pw.println(t.toSaveFormat());
            }
        }
    }
}
