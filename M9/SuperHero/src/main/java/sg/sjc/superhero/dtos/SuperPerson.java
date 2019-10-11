/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;

import java.util.Objects;

/**
 *
 * @author corey
 */
public class SuperPerson {
    
    private int superId;
    private String name;
    private boolean isVillain;
    private String description;
    private String powers;
    private String organization;

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsVillain() {
        return isVillain;
    }

    public void setIsVillain(boolean isVillain) {
        this.isVillain = isVillain;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.superId;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + (this.isVillain ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.powers);
        hash = 41 * hash + Objects.hashCode(this.organization);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperPerson other = (SuperPerson) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (this.isVillain != other.isVillain) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
