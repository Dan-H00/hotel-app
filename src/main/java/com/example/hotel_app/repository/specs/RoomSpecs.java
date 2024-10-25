package com.example.hotel_app.repository.specs;

import com.example.hotel_app.entity.Room;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpecs {
    public static Specification<Room> filterByValue(int searchValue, String field) {
        return (root, _, criteriaBuilder) -> criteriaBuilder.equal(root.get(field).as(Integer.class), searchValue);
    }
}
