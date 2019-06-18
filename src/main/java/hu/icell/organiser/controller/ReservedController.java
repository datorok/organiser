package hu.icell.organiser.controller;

import hu.icell.organiser.entity.Reserved;
import hu.icell.organiser.repository.ReservedRepository;
import hu.icell.organiser.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ReservedController {
    private ReservedRepository reservedRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public ReservedController(ReservedRepository reservedRepository, ServiceRepository serviceRepository) {
        this.reservedRepository = reservedRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/editreserved")
    public String editReserved(Model model, @RequestParam(defaultValue = "0") int page) {
        var size = reservedRepository.findAll().size();
        model.addAttribute("reserveddata", reservedRepository.findAll(PageRequest.of(page, size == 0 ? 1 : size)));
        model.addAttribute("servicedata", serviceRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", 0);

        return "editreserved";
    }

    @PostMapping("/editreserved")
    public String editReserved(@ModelAttribute("reserved") Reserved reserved) {
        reservedRepository.save(reserved);
        return "redirect:/editreserved";
    }

    @PostMapping("/saveReserved")
    public String saveReserved(Reserved reserved) {
        reservedRepository.save(reserved);
        return "redirect:/editreserved";
    }

    @PostMapping("/deletereserved")
    public String deleteReserved(@RequestParam int reservedId) {
        reservedRepository.deleteById(reservedId);
        return "redirect:/editreserved";
    }

    @GetMapping("/findonereserved")
    @ResponseBody
    public Reserved findOneReserved(int id) {
        Optional optional = reservedRepository.findById(id);
        Reserved reserved = (Reserved) optional.get();
        return reserved;
    }

    @GetMapping("/addreserved")
    public String addReserved(Model model) {
        model.addAttribute("reservedListInModel", reservedRepository.findAll());
        return "addreserved";
    }

    @PostMapping("/addreserved")
    public RedirectView addReserved(@ModelAttribute("reserved") Reserved reserved) {
        reservedRepository.save(reserved);
        return new RedirectView("editreserved");
    }

    /**
     * Ez a metódus lehetővé teszi, hogy ha a user a /reserved/ tag után az url-ben megadja egy konkét reserved nevét, akkor láthassa az adott reservedhez tartozó rekordot
     */
    @GetMapping("/reserved/{portnumber}")
    public String searchForReserved(@PathVariable(name = "portnumber") String portnumber, Model reservedModel) throws Exception { //a @PathVariable annotációval lehet elérési
        // utakból dinamikus változót kiszedni

        if (portnumber == null) {
            throw new Exception("This port number does not exist");
        }
        reservedModel.addAttribute("reserveddata", reservedRepository.findAllByPortNumberEquals(portnumber, PageRequest.of(0, 1)));

        return "editreserved";
    }

}
