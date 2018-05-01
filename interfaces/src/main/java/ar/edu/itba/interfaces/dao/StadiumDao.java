package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Stadium;

public interface StadiumDao {
    
    Stadium create(String name, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice);

    Stadium findById(long id);
}
