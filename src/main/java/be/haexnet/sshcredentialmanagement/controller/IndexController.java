package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialUpdateCommand;
import be.haexnet.sshcredentialmanagement.controller.mapper.CredentialUpdateMapper;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final ICredentialService credentialService;
    private final CredentialUpdateMapper credentialUpdateMapper;

    @Autowired
    public IndexController(final ICredentialService credentialService,
                           final CredentialUpdateMapper credentialUpdateMapper) {
        this.credentialService = credentialService;
        this.credentialUpdateMapper = credentialUpdateMapper;
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.put("credentials", findAllCredentialsSortedOnServer());
        return "index";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String credentialId) {
        credentialService.delete(credentialId);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String showEdit(@PathVariable("id") String credentialId,
                           ModelMap modelMap) {
        modelMap.put("credential", credentialUpdateMapper.map(credentialService.findOne(credentialId)));
        return "update";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute CredentialUpdateCommand credential) {
        credentialService.update(credentialUpdateMapper.map(credential));
        return "redirect:/";
    }

    private List<Credential> findAllCredentialsSortedOnServer() {
        return credentialService
                .findAll()
                .stream()
                .sorted((c1, c2) -> c1.getServer().toUpperCase().compareTo(c2.getServer().toUpperCase()))
                .collect(Collectors.toList());
    }
}
