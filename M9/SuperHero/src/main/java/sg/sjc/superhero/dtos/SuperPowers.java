/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.dtos;
import java.util.Objects;


/**
 *
 * @author jhoan
 */
public class SuperPowers {
    
       private int spwId;
    private String name;
    

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.spwId;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        return true;
    }


   

  


 
    
}
