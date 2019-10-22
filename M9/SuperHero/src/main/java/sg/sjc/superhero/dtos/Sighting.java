/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author stephenespinal
 */
public class Sighting {
    
    private int sightingId;
    
    @NotEmpty(message = "Please enter a description.")
    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;
    private Location location;
    private String sightingDate;
    
    private List<SuperPerson> superPersons;

    public List<SuperPerson> getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(List<SuperPerson> superPersons) {
        this.superPersons = superPersons;
    }

    
    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.sightingId;
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + Objects.hashCode(this.location);
        hash = 13 * hash + Objects.hashCode(this.sightingDate);
        hash = 13 * hash + Objects.hashCode(this.superPersons);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superPersons, other.superPersons)) {
            return false;
        }
        return true;
    }

    
    
}
