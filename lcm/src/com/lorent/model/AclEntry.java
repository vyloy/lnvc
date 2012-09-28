package com.lorent.model;
public class AclEntry {
    private long id;
    
    private AclObjectIdentity aclObject;
    
    private Integer aceOrder;
    
    private AclSid aclSid;
    
    private Integer mask;
    
    private boolean granting;

    private boolean auditSuccess;
    
    private boolean auditFailure;    
    
    /**
     * No-arg constructor for JavaBean tools.
     */
    public AclEntry() {
        
    }

    /**
     * Full constructor
     */
    public AclEntry(AclObjectIdentity aclObject,Integer aceOrder,AclSid aclSid,
            Integer mask,boolean granting,boolean auditSuccess,boolean auditFailure) {
        this.aclObject = aclObject;
        this.aceOrder = aceOrder;
        this.aclSid = aclSid;
        this.mask = mask;
        this.granting = granting;    
        this.auditSuccess = auditSuccess;    
        this.auditFailure = auditFailure;            
    }  
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Accessor Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~//     
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AclObjectIdentity getAclObject() {
        return aclObject;
    }

    public void setAclObject(AclObjectIdentity aclObject) {
        this.aclObject = aclObject;
    }      

    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }    
    
    public AclSid getAclSid() {
        return aclSid;
    }

    public void setAclSid(AclSid aclSid) {
        this.aclSid = aclSid;
    }  
    
    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }
    
    public boolean isGranting() {
        return granting;
    }
    
    public void setGranting(boolean granting) {
        this.granting = granting;
    }  
    
    public boolean isAuditSuccess() {
        return auditSuccess;
    }
    
    public void setAuditSuccess(boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }   
    
    public boolean isAuditFailure() {
        return auditFailure;
    }
    
    public void setAuditFailure(boolean auditFailure) {
        this.auditFailure = auditFailure;
    }     
}