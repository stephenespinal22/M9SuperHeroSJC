/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

/**
 *
 * @author corey
 */
public class SuperPerson {
    
    private int superId;
    
    @NotEmpty(message = "Please enter a name.")
    private String name;
    private boolean isVillain;
    
    @NotEmpty(message = "Please enter a description.")
    private String description;
    
    private List<SuperPowers> powers;
    private List<Organization> organizations;
    
    private List<Sighting> sightings;
    
    private String imagePath;

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

    public List<SuperPowers> getPowers() {
        return powers;
    }

    public void setPowers(List<SuperPowers> powers) {
        this.powers = powers;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.superId;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + (this.isVillain ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.powers);
        hash = 67 * hash + Objects.hashCode(this.organizations);
        hash = 67 * hash + Objects.hashCode(this.sightings);
        hash = 67 * hash + Objects.hashCode(this.imagePath);
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
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        return true;
    }

    

}
