package com.example.pokemondreamteam.exceptions;

import com.example.pokemondreamteam.exceptions.MessageError.ApiError;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UnprocessableEntityException extends RuntimeException {

  private static final long serialVersionUID = -5012441619995884439L;

  private final List<ApiError> errors;

  public UnprocessableEntityException(ApiError error) {
    this(Lists.newArrayList(error));
  }

  public UnprocessableEntityException(List<ApiError> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public UnprocessableEntityException(ApiError error, String detail) {
    super(String.format("%s - Detail: %s", error.toString(), detail));
    this.errors = Lists.newArrayList(error);
  }

  public UnprocessableEntityException(ApiError error, String detail, Throwable ex) {
    super(String.format("%s - Detail: %s", error.toString(), detail), ex);
    this.errors = Lists.newArrayList(error);
  }
}
