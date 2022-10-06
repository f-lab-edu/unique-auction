package com.uniqueAuction.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.uniqueAuction.exception.advice.CommonException;

import java.util.Arrays;
import java.util.Optional;

import static com.uniqueAuction.exception.ErrorCode.NOT_FOUND_CATEGORY;

public enum Category {

    신발,
    의류,
    패션잡화,
    전자기기,

   ;

    @JsonCreator
    public static Category setValue(String key){
        Optional<Category> category = Arrays.stream(Category.values())
                .filter(ct -> ct.toString().equals(key))
                .findAny();
        return category.orElseThrow(() ->  new CommonException(NOT_FOUND_CATEGORY));
    }
}
