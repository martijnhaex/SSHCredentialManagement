package be.haexnet.sshcredentialmanagement.util;

import be.haexnet.sshcredentialmanagement.model.Credential;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CredentialParser {
    public List<Credential> fromString(final String value) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }

        return createStringList(value)
                .stream()
                .map(createCredential())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.<Credential>toList());
    }

    private List<String> createStringList(final String value) {
        return Arrays.asList(
                value.split("\\r?\\n")
        );
    }

    private Function<String, Optional<Credential>> createCredential() {
        return credential -> {
            final String[] credentialParts = credential.split(",");

            if (credentialParts.length >= 4) {
                return Optional.of(
                        Credential.of(generateId(), credentialParts[0].trim(), credentialParts[1].trim(), credentialParts[2].trim(), credentialParts[3].trim())
                );
            }
            return Optional.empty();
        };
    }

    private Long generateId() {
        return System.currentTimeMillis();
    }
}
