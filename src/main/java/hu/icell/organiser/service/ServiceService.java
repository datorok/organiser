package hu.icell.organiser.service;

import hu.icell.organiser.entity.Reserved;
import hu.icell.organiser.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Ebben az interface-ben gyűjtjük össze azokat a metődusokat, amiket majd a program működése során service-ként használni akarunk. Ezek implementálása a ServiceServiceImplementation class-ban történik
 * majd meg.
 */

public interface ServiceService {
    Service createService (Service service);
    Service getService (Integer id);
    Service editService (Service service);
    void deleteService (Service service);
    void deleteService (Integer id);
    Service findByServiceName(String ServiceName);
    Page<Service> findAllByServiceNameEquals(String serviceName, Pageable pageable);
    List<Service> getAllService();
    Service create (Service server);
    Service edit (Service server);
    String suggestAFreePort(List<Reserved> reservedPorts, List<Service>  occupiedPorts);
}
