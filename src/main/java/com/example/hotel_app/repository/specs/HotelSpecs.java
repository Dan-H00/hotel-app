package com.example.hotel_app.repository.specs;

import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class HotelSpecs {
    public static Specification<Hotel> hotelRoomAvailableAndTimeframeAndCity(String city) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.join("rooms").get(Room.Fields.isAvailable), true));
            predicates.add(criteriaBuilder.equal(root.get("city"), city));

            return criteriaBuilder.and(predicates);
        });
    }
}
