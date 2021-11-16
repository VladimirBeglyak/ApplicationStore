package by.beglyakdehterenok.store.mapper;

public interface BaseMapper <FROM,TO>{
    TO mapFrom(FROM entity);
}
