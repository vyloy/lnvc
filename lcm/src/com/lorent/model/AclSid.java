package com.lorent.model;
public class AclSid {
    private long id;
    private boolean principal;
    private String sid;

    /**
     * No-arg constructor for JavaBean tools.
     */
    public AclSid() {
        
    }

    /**
     * Full constructor
     */
    public AclSid(boolean principal, String sid) {
        this.principal = principal;
        this.sid = sid;     
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Accessor Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~//     
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPrincipal() {
        return principal;
    }
    
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
