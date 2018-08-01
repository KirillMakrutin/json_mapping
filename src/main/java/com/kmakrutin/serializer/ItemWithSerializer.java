package com.kmakrutin.serializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( exclude = "owner" )
@JsonSerialize( using = ItemSerializer2.class )
public class ItemWithSerializer
{
  private int id;
  private String name;
  private User owner;
}
