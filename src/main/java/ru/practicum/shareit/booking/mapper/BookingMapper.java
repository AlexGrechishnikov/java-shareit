package ru.practicum.shareit.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.utill.mapper.Mapper;

@Component
public class BookingMapper implements Mapper<Booking, BookingDto> {

    @Override
    public BookingDto toDto(Booking entity) {
        return new BookingDto(
                entity.getId(),
                entity.getStart(),
                entity.getEnd(),
                entity.getItem().getId(),
                entity.getBooker().getId(),
                entity.getStatus()
        );
    }

    @Override
    public Booking toEntity(BookingDto dto) {
        return null;
    }
}