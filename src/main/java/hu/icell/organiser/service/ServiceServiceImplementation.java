package hu.icell.organiser.service;

import hu.icell.organiser.entity.Reserved;
import hu.icell.organiser.entity.Service;
import hu.icell.organiser.repository.ReservedRepository;
import hu.icell.organiser.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Ebben a class-ban implementáljuk azokat a metódusokat, amiket meg a ServiceService interface-ben összegyűjtöttünk.
 * Mivel a metódusok egy kivételével DB-műveletekre vonatkoznak, így implementálásukhoz egyszerűen csak meg kell hívni a repository megfelelő metódusát
 *
 */


@org.springframework.stereotype.Service
public class ServiceServiceImplementation implements ServiceService {

    /**
     * A Dependency Injection itt field szinten történik meg, nem pedig konstruktorban. Az Autowired annotáció teszi lehetővé azt, hogy a Container szerepét betöltő Application Context az adott
     * POJO-t a @Scope(“”) annotációban megadott paraméternek megfelelően kezelje. Ha nem használjuk az @Autowired annotációt, akkor hiába állítunk be az adott POJO példányosítását lehetővé tévő
     * Class @Scope annotációjában bármilyen paramétert is, a Spring mindig ugyanúgy fog eljárni: a Controller class minden egyes példányosításakor létre fog hozni egy darab POJO-t az érintett
     * classból, hiába adtunk neki Session vagy Request scope-ot.
     */

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReservedRepository reservedRepository;

    @Override
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service getService(Integer id) {
        return serviceRepository.getOne(id);
    }

    @Override
    public Service editService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Service service) {
        serviceRepository.delete(service);
    }

    @Override
    public void deleteService(Integer id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Service findByServiceName(String serviceName) {
        return serviceRepository.findByServiceName(serviceName);
    }

    @Override
    public Page<Service> findAllByServiceNameEquals(String serviceName, Pageable pageable) {
        return serviceRepository.findAllByServiceNameEquals(serviceName, pageable);
    }

    @Override
    public List<Service> getAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public Service create(Service server) {
        return serviceRepository.saveAndFlush(server);
    }

    @Override
    public Service edit(Service server) {
        return serviceRepository.saveAndFlush(server);
    }

    /**
     * Ez a metódus ajánl a Service számára egy olyan portot, amit még nem használ egyetlen más Service sem, illetve nem szerepel a DB-ben a Reserved portok között sem.
     * Az ajánlás véletlenszám-generálással történik, és minden esetben 1024 és 65535 közé esik az értéke. A metódus visszatérési értéke String, hiszen nem akarjuk számként használni.
     * Az itt generált portszámot a ServiceController class addService (getMapping) metódusa adja át suggestedport néven a Model segítségével.
     */

    @Override
    public String suggestAFreePort(List<Reserved> reservedPorts, List<Service> occupiedPorts) {

        List<String> notAvailablePortNumberList = new ArrayList<>();

        for (int i = 0; i < reservedPorts.size(); i++) {
            notAvailablePortNumberList.add(reservedPorts.get(i).getPortNumber());
        }
        for (int i = 0; i < occupiedPorts.size(); i++) {
            notAvailablePortNumberList.add(occupiedPorts.get(i).getPort());
        }
        String randomPort = "";

        Random ran = new Random();

        while (randomPort == "") {
            int generatedInt = ran.nextInt(64511) + 1025;
            String generatedString = String.valueOf(generatedInt);

            if (!notAvailablePortNumberList.contains(generatedString)) {
                randomPort = generatedString;
            }
        }
        return randomPort;
    }
}
