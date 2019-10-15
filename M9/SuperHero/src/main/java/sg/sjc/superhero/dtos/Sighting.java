/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author stephenespinal
 */
public class Sighting {
    
    private int sightingId;
    private String description;
    private String sightingDate;

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

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.sightingId;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.sightingDate);
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
        return true;
    }


}
