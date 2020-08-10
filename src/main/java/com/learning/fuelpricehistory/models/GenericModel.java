package com.learning.fuelpricehistory.models;

import java.io.Serializable;

public interface GenericModel<T extends Serializable> {

    /**
    * Gets the ID of the model.
    * 
    * @return ID of the model.
    */
   public abstract T getId(); 
   
   /**
    * Sets the ID of the model.
    * 
    * @param id
    * 		ID of the model.
    */
   public abstract void setId(T id);
}