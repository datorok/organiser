package hu.icell.organiser.controller;

import hu.icell.organiser.entity.Service;
import hu.icell.organiser.repository.ReservedRepository;
import hu.icell.organiser.repository.ServerRepository;
import hu.icell.organiser.repository.ServiceRepository;
import hu.icell.organiser.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * A @Controller annotációval ellátott osztályok közreműködnek a @RequestMapping(„/”), @GetMapping("/"), @PostMapping("/") annotációval ellátott metódusaik segítségével az adott .html oldalak
 * megjelenítésében. Ez az osztály tehát a Service-ekkel kapcsolatos HTTP requesteket (get-eket és post-okat) koordinálja, vagyis meghatározza, hogy a user böngészőjétől érkező get esetében
 * milyen .html oldalt kell elküldeni, illetve a post esetében mit várunk el a programtól, és utána hová akarjuk átdobni a user-t.
 */

@Controller
public class ServiceController {

    /**
     * A Dependency injection elvének megfelelően azokat az osztályokat, amikből készült objektumokat a működése során használni fogja a Controller felvesszük field-ként.
     */
    private ServerRepository serverRepository;
    private ServiceRepository serviceRepository;
    private ReservedRepository reservedRepository;
    private ServiceService serviceService;

    /**
     * A feield-ként felvett objektumok átadása itt történik meg a Controller osztály konstruktorában. Az @Autowired annotáció teszi lehetővé, hogy a Container (ez tárolja és menedzseli a bean-ek
     * életciklusait) szerepét betöltő Application Context kezelhesse az átadott objektumokat.
     */

    @Autowired
    public ServiceController(ServerRepository serverRepository, ServiceRepository serviceRepository, ServiceService serviceService, ReservedRepository reservedRepository) {
        this.serverRepository = serverRepository;
        this.serviceRepository = serviceRepository;
        this.serviceService = serviceService;
        this.reservedRepository = reservedRepository;
    }

    /**
     * A @GetMapping annotáció a http get request-ek mapping-elésére szolgál, és egyenértékű a következővel: @RequestMapping(method = RequestMethod.GET).
     *
     * A @GetMapping annotációval azt jelezzük vele a Spring számára, hogy a szerveren minden, ami a zárójelben szereplő kifejezés (jelen esetben a /editservice) után el van kapva címzettként,
     * arra visszaadja az annotáció alatti metódus visszatérési értékét (vagyis az resources/templates/editservice.html oldalt). A get tehát a user böngészőjéből érkező get-eket kezeli.
     *
     * A Model paraméter az .addAttribute metódus segítségével adatot tud szállítani a backend (és a DB) valamint a frontend között.
     * Az .addAttribute metódusnak két paramétere van, az első az átadandó adatnak a neve (ahogy a .html oldalon hivatkozhatunk rá), a második pedig maga az átadandó adat (ami lehet egy változó
     * de akár valamilyen adatszerkezet is, ami adatbázis-lekérdezés során kapott adatokat tartalmaz).
     *
     * A Model segítségével jelen esetben átadjuk a következőket:
     * - servicedata - a db-ben található összes szervice adatai
     * - serverdata -  a db-ben található összes szerver adatai
     * - currentpage - az oldalszám
     * - pageSize - az összes oldal száma (ami minden esetben nulla, mert csak egy oldalt használunk a könnyebb kereshetőség érdekében)
     *
     * A page paraméter használata lehetővé tenné az adott .html oldal által megjelenített rekordok lapozható oldalakra történő bontását. Mivel ez nagyban megnehezítené a rekordok közötti keresést,
     * így ezt nem használjuk, és emiatt az értéke minden esetben 0, így az összes rekord egyazon oldalon jelenik meg.
     */

    @GetMapping("/editservice")
    public String editService(Model model, @RequestParam(defaultValue = "0") int page) {
        var size = serviceRepository.findAll().size();
        model.addAttribute("servicedata", serviceRepository.findAll(PageRequest.of(page, size == 0 ? 1 : size)));
        model.addAttribute("serverdata", serverRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", 0);
        return "editservice";
    }

    /**
     * A @PostMapping annotáció a http post request-ek mapping-elésére szolgál, és egyenértékű a következővel: @RequestMapping(method = RequestMethod.POST).
     * Ezzel az annotációval jelezzük a Spring számára, hogy a user-től az adott html oldalról érkező (post-olt) információ fogadásakor milyen metódusokat akarunk meghívni.
     * Jelen esetben a azt akarjuk, hogy a módosított Service mentése történjen meg, majd a user böngészőjébe töltődjön be az editservice.html. Ezeket a Thymeleaf hajtja végre.
     *
     */

    @PostMapping("/editservice")
    public String editService(@ModelAttribute("service") Service service) {
        serviceRepository.save(service);
        return "redirect:/editservice";
    }

    @GetMapping("/addservice")
    public String addService(Model model) {
        model.addAttribute("serverListInModel", serverRepository.findAll());
        var reservedPorts = reservedRepository.findAll();
        var occupiedPorts = serviceRepository.findAll();
        model.addAttribute("suggestedport", serviceService.suggestAFreePort(reservedPorts, occupiedPorts));
        return "addservice";
    }

    @PostMapping("/addservice")
    public RedirectView addService(@ModelAttribute("service") Service service) {
        serviceRepository.save(service);
        return new RedirectView("editservice");
    }

    /**
     * Ez a metódus lehetővé teszi, hogy ha a user a /service/ tag után az url-ben megadja egy konkrét service nevét, akkor láthassa az ahhoz tartozó rekordot
     */
    @GetMapping("/service/{servicename}")
    public String searchForService(@PathVariable(name = "servicename") String servicename, Model serviceModel, @RequestParam(defaultValue = "0") int page) throws Exception {
        var size = serviceRepository.findAll().size();
        if (servicename == null) {
            throw new Exception("This server does not exist");
        }
        serviceModel.addAttribute("servicedata", serviceRepository.findAllByServiceNameEquals(servicename, PageRequest.of(page, size == 0 ? 1 : size)));
        serviceModel.addAttribute("currentPage", page);
        serviceModel.addAttribute("pageSize", 0);
        return "editservice";
    }


    @PostMapping("/saveService")
    public String saveService(Service service) {
        serviceRepository.save(service);
        return "redirect:/editservice";
    }

    @PostMapping("/deleteservice")
    public String deleteService(@RequestParam int serviceId) {
        serviceRepository.deleteById(serviceId);
        return "redirect:/editservice";
    }

    @GetMapping("/findoneservice")
    @ResponseBody
    public Service findOneService(int id) {
        Optional optional = serviceRepository.findById(id);
        Service service = (Service) optional.get();
        return service;
    }
}


