package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialCommand;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.util.CredentialParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/batch-import")
public class ImportController {

    private final CredentialParser credentialParser;

    @Autowired
    public ImportController(final CredentialParser credentialParser) {
        this.credentialParser = credentialParser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showImport(@ModelAttribute("command") CredentialCommand credentialCommand) {
        return "import";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doImport(@ModelAttribute("command") CredentialCommand credentialCommand) {
        saveCredentials(credentialCommand);
        return "redirect:/";
    }

    private void saveCredentials(final CredentialCommand credentialCommand) {
        parseCredentials(credentialCommand.getCredentials());
    }

    private List<Credential> parseCredentials(final String credentialsAString) {
        return credentialParser.fromString(credentialsAString);
    }
}
