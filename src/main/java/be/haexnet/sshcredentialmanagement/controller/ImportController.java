package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialImportCommand;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
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

    private final ICredentialService credentialService;
    private final CredentialParser credentialParser;

    @Autowired
    public ImportController(final ICredentialService credentialService,
                            final CredentialParser credentialParser) {
        this.credentialService = credentialService;
        this.credentialParser = credentialParser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showImport(@ModelAttribute("command") CredentialImportCommand credentialImportCommand) {
        return "import";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doImport(@ModelAttribute("command") CredentialImportCommand credentialImportCommand) {
        saveCredentials(credentialImportCommand);
        return "redirect:/";
    }

    private void saveCredentials(final CredentialImportCommand credentialImportCommand) {
        credentialService.batchSave(
                parseCredentials(credentialImportCommand.getCredentials())
        );
    }

    private List<Credential> parseCredentials(final String credentialsAString) {
        return credentialParser.fromString(credentialsAString);
    }
}
