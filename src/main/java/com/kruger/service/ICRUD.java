package com.kruger.service;

import java.util.List;

public interface ICRUD<T> {

	T register(T t);

	T update(T t);

	void remove(int id);

	T getOne(int id);

	List<T> getAll();
}
