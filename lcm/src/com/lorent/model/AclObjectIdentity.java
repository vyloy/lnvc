package com.lorent.model;

public class AclObjectIdentity {

    private long id;
    
    private AclClass aclClass;

    private long objectId;

    private AclObjectIdentity parentAclObject;
    
    private AclSid aclSid;
    
    private boolean inheriting;
    
    /**
     * No-arg constructor for JavaBean tools.
     */
    public AclObjectIdentity() {
        
    }

    /**
     * Simple constructor
     */
    public AclObjectIdentity(AclClass aclClass,long objectId, AclSid aclSid,boolean inheriting) {
        this.aclClass = aclClass;
        this.objectId = objectId;
        this.aclSid = aclSid;
        this.inheriting = inheriting;           
    }    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Accessor Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~//     
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AclClass getAclClass() {
        return aclClass;
    }

    public void setAclClass(AclClass aclClass) {
        this.aclClass = aclClass;
    }
    
    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }  
    
    public AclObjectIdentity getParentAclObject() {
        return parentAclObject;
    }

    public void setParentAclObject(AclObjectIdentity parentAclObject) {
        this.parentAclObject = parentAclObject;
    }      
    
    public AclSid getAclSid() {
        return aclSid;
    }

    public void setAclSid(AclSid aclSid) {
        this.aclSid = aclSid;
    }    
    
    public boolean isInheriting() {
        return inheriting;
    }
    
    public void setInheriting(boolean inheriting) {
        this.inheriting = inheriting;
    }    
}