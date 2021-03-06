package com.kmakrutin.deserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( exclude = "owner" )
@JsonDeserialize( using = ItemDeserializer2.class )
public class ItemWithDeserializer
{
  private int id;
  private String name;
  private User owner;
}
