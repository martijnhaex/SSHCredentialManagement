package be.haexnet.sshcredentialmanagement.fixture;

import be.haexnet.sshcredentialmanagement.model.Credential;

import java.util.ArrayList;
import java.util.List;

public final class CredentialFixture {
    private CredentialFixture() {
    }

    public static List<Credential> LIST() {
        final List<Credential> credentials = new ArrayList<>();
        credentials.add(CREATE("2"));
        credentials.add(CREATE("3"));
        credentials.add(CREATE("1"));
        return credentials;
    }

    public static Credential CREATE(final String id) {
        return Credential.of(
                id,
                "SERVER" + id,
                "PASSWORD" + id,
                "USERNAME" + id,
                "URL" + id
        );
    }
}
