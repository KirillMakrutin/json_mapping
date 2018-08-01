package com.kmakrutin.basic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties( ignoreUnknown = true )
@Data
public class MyDtoIgnoreUnknown
{
  private String stringValue;
  private int intValue;
  private boolean booleanValue;
}
