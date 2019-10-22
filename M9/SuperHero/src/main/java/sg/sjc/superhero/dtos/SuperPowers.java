/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author jhoan
 */
public class SuperPowers {

    private int spwId;
    
    @NotBlank(message = "Must enter a power name.")
    @Size(max = 50, message = "Power name must be less than 50 characters.")
    private String name;
    
    private List<SuperPerson> superPersons;

    public int getSpwId() {
        return spwId;
    }

    public void setSpwId(int spwId) {
        this.spwId = spwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SuperPerson> getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(List<SuperPerson> superPersons) {
        this.superPersons = superPersons;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.spwId;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.superPersons);
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
        final SuperPowers other = (SuperPowers) obj;
        if (this.spwId != other.spwId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.superPersons, other.superPersons)) {
            return false;
        }
        return true;
    }





}
