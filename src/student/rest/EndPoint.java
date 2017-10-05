package student.rest;

import student.data.Service;

public class EndPoint {

    Service service;
    Class<?> resource;

    String query;
    String id;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getResource() {
        return resource;
    }

    public void setResource(Class resource) {
        this.resource = resource;
    }

}
