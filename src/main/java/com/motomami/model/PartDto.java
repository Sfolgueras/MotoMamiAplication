package com.motomami.model;
import java.util.Date;

public class PartDto {
    private Date datePartExternal;
    private String descriptionPartExternal;
    private String codeDamageExternal;
    private String codeDamage;
    private String identityCode; //DNI del user
    private String idExternal;

    public String getIdExternal() {
        return idExternal;
    }

    public void setIdExternal(String idExternal) {
        this.idExternal = idExternal;
    }

    public Date getDatePartExternal() {
        return datePartExternal;
    }

    public void setDatePartExternal(Date datePartExternal) {
        this.datePartExternal = datePartExternal;
    }

    public String getDescriptionPartExternal() {
        return descriptionPartExternal;
    }

    public void setDescriptionPartExternal(String descriptionPartExternal) {
        this.descriptionPartExternal = descriptionPartExternal;
    }

    public String getCodeDamageExternal() {
        return codeDamageExternal;
    }

    public void setCodeDamageExternal(String codeDamageExternal) {
        this.codeDamageExternal = codeDamageExternal;
    }

    public String getCodeDamage() {
        return codeDamage;
    }

    public void setCodeDamage(String codeDamage) {
        this.codeDamage = codeDamage;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

}

