package hu.icell.organiser.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Ez az osztály felelős a db service nevű adattáblájának a programon belüli reprezentációjáért, így kialakítása is tükrözi azt. Ennek megfelelően az itteni field-ek reprezentálják az ottani
 * oszlopokat. Az osztály és az adattábla hatékony együttműködéséhez felhasznált annotációk jelentései a következőek:
 *
 * + Entity : ez az osztály egy pojo, és megfeleltethető egy db-n belüli adatbázistáblának
 * + Table : átadjuk a konkrét db táblanevet
 * + Id : jelezzük, hogy az adott field megfeltethető a db-n belüli adattábla "id" oszlopának
 * + GeneratedValue : új rekordok beszúrásakor nem mi adjuk meg az id értékét, hanem a db. Az IDENTITY azt határozza meg, hogy a generált érték csak az adott táblán belül lesz egyedi, nem pedig az egész
 *                    db-n belül (ehhez az AUTO beállítás használható)
 * + Column : ezzel azonosítjuk azt az adattáblabeli oszlopot, amit az adott field-del akarunk reprezentálni
 * + ManyToMany : jelezzük a spring számára, hogy ilyen típusú kapcsolat áll fenn ez (a service) és egy másik (server_service) tábla között.
 * + JoinTable : megadjuk annak a táblának (server_service) a nevét, amivel fennáll a fenti kapcsolat
 * + JoinColumn : kijelöljük azt a két oszlopot, amelyek alapján létre lehet hozni a fenti kapcsolatot.
 *
 */

@Entity
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "url")
    private String url;

    @Column(name = "port")
    private String port;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "server_service",
            joinColumns = {@JoinColumn(name = "service_id")},
            inverseJoinColumns = {@JoinColumn(name = "server_id")}

    )
    private List<Server> serverList;

    public Service() {
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Server> getServerList() {
        return serverList;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }

    @Override
    public String toString() {
        return serviceName;
    }
}
