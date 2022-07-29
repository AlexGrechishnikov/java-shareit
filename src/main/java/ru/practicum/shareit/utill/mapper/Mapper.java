package ru.practicum.shareit.utill.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtoList(List<E> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
