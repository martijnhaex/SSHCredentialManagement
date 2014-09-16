package be.haexnet.sshcredentialmanagement.repository;

import be.haexnet.sshcredentialmanagement.model.Credential;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

@Repository
public class CredentialRepository {

    private static final String DATABASE_FILE_NAME = "credentials.ser";

    public void batchSave(final List<Credential> credentials) {
        try {
            saveAlLCredentials(credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Credential> findAll() {
        try {
            return getAllCredentials();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void saveAlLCredentials(final List<Credential> credentials) throws IOException {
        final File databaseFile = new File(DATABASE_FILE_NAME);
        try (final ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(databaseFile))) {
            oo.writeObject(credentials);
        }
    }

    private List<Credential> getAllCredentials() throws IOException, ClassNotFoundException {
        try (final ObjectInput oi = new ObjectInputStream(new FileInputStream(DATABASE_FILE_NAME))) {
            return (List<Credential>) oi.readObject();
        }
    }
}
