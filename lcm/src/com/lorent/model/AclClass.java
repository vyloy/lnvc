package com.lorent.model;
public class AclClass{
    private long id;

    private String cls;

    /**
     * No-arg constructor for JavaBean tools.
     */
    public AclClass() {
        
    }
    /**
     * Full constructor
     */
    public AclClass(String cls) {
        this.cls = cls;
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Accessor Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~//     
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}
