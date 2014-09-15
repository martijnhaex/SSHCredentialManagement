package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImportController {
    @RequestMapping(value = "/batch-import", method = RequestMethod.GET)
    public String showImport(@ModelAttribute("command") CredentialCommand credentialCommand) {
        return "import" ;
    }
}
