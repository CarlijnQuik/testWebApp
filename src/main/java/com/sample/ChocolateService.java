package com.sample;

import com.sample.model.ChocolateType;

import java.util.ArrayList;
import java.util.List;

/**
 * ChocolateService class
 */
public class ChocolateService {

    public List getAvailableBrands(ChocolateType type){

        List brands = new ArrayList();

        if(type.equals(ChocolateType.DARK)){
            brands.add("Lu");
            brands.add(("Lindt"));

        }else if(type.equals(ChocolateType.WHITE)){
            brands.add("Cote d'Or");
            brands.add("Tony");

        }else if(type.equals(ChocolateType.MILK)){
            brands.add("Toblerone");
            brands.add("Tony");

        }else {
            brands.add("No Brand Available");
        }
        return brands;
    }
}