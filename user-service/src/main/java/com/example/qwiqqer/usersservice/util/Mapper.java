package com.example.qwiqqer.usersservice.util;

abstract public class Mapper<T, E> {
	abstract public E mapToDto(T t);
	abstract public T mapToEntity(E e);
}
