package hu.icell.organiser.controller;

import hu.icell.organiser.entity.Server;
import hu.icell.organiser.repository.ServerRepository;
import hu.icell.organiser.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ServerController {
    private ServerRepository serverRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public ServerController(ServerRepository serverRepository, ServiceRepository serviceRepository) {
        this.serverRepository = serverRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/editserver")
    public String editServer(Model model, @RequestParam(defaultValue = "0") int page) {
        var size = serverRepository.findAll().size();
        model.addAttribute("serverdata", serverRepository.findAll(PageRequest.of(page, size == 0 ? 1 : size)));
        model.addAttribute("servicedata", serviceRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", 0);

        return "editserver";
    }

    @PostMapping("/editserver")
    public String editServer(@ModelAttribute("server") Server server) {
        serverRepository.save(server);
        return "redirect:/editserver";
    }

    @PostMapping("/saveServer")
    public String saveServer(Server server) {
        serverRepository.save(server);
        return "redirect:/editserver";
    }

    @PostMapping("/deleteserver")
    public String deleteServer(@RequestParam int serverId) {
        serverRepository.deleteById(serverId);
        return "redirect:/editserver";
    }

    @GetMapping("/findoneserver")
    @ResponseBody
    public Server findOneServer(int id) {
        Optional optional = serverRepository.findById(id);
        Server server = (Server) optional.get();
        return server;
    }

    @GetMapping("/addserver")
    public String addServer(Model model) {
        model.addAttribute("serviceListInModel", serviceRepository.findAll());
        return "addserver";
    }

    @PostMapping("/addserver")
    public RedirectView addServer(@ModelAttribute("server") Server server) {
        serverRepository.save(server);
        return new RedirectView("editserver");
    }

    /**
     * Ez a metódus lehetővé teszi, hogy ha a user a /server/ tag után az url-ben megadja egy konkét server nevét, akkor láthassa az adott serverhez tartozó rekordot
     */
    @GetMapping("/server/{servername}")
    public String searchForServer(@PathVariable(name = "servername") String serverename, Model servereModel, @RequestParam(defaultValue = "0") int page) throws Exception {
        var size = serverRepository.findAll().size();

        if (serverename == null) {
            throw new Exception("This server does not exist");
        }

        servereModel.addAttribute("serverdata", serverRepository.findAllByServerNameEquals(serverename, PageRequest.of(0, 1)));
        servereModel.addAttribute("currentPage", page);
        servereModel.addAttribute("pageSize", 0);
        return "editserver";
    }
}


