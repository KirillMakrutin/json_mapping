package com.kmakrutin.enums;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class WithEnum
{
  private String someString;
  private DistanceFormatted distance;
}
