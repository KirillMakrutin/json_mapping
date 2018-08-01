package com.kmakrutin.deserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( exclude = "owner" )
public class Item
{
  private int id;
  private String name;
  private User owner;
}
