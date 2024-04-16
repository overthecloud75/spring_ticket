package com.example.manage.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Paging {
    Integer number;
    Integer size;
    Integer totalPages;
    Integer count;
    Boolean hasPrevious = false;
    Boolean hasNext = true;
    Boolean empty;

    public boolean isEmpty() {
        if (count == 0) { empty = true;} 
        else { empty = false; }
        return empty;
    }
}
