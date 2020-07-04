package com.bulefy.api.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Response<T> {
	private Long idUsuario;
	private T data;
	private List<String> errors = new ArrayList<>();
}
