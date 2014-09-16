package be.haexnet.sshcredentialmanagement.repository;

import be.haexnet.sshcredentialmanagement.model.Credential;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.List;

@Repository
public class CredentialRepository {

    private static final String DATABASE_FILE_NAME = "credentials";
    private static final String DATABASE_FILE_EXTENSION = "ser";

    public void batchSave(final List<Credential> credentials) {
        try {
            save(credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(final List<Credential> credentials) throws IOException {
        final File databaseFile = Files.createTempFile(DATABASE_FILE_NAME, DATABASE_FILE_EXTENSION).toFile();
        try (final ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(databaseFile))) {
            oo.writeObject(credentials);
        }
    }
}
