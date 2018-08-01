package com.kmakrutin;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize( using = DistanceSerializer.class )
@AllArgsConstructor
@Getter
public enum DistanceWithSerializer
{
  KILOMETER( "km", 1000 ),
  MILE( "miles", 1609.34 ),
  METER( "meters", 1 ),
  INCH( "inches", 0.0254 ),
  CENTIMETER( "cm", 0.01 ),
  MILLIMETER( "mm", 0.001 );

  private final String unit;
  private final double meters;

  /**
   * To Deserialize from the custom serializer shape
   */
  @JsonCreator
  public static DistanceWithSerializer fromObject( final Map<String, String> obj )
  {
    if ( obj.containsKey( "name" ) )
    {
      return DistanceWithSerializer.valueOf( obj.get( "name" ) );
    }

    return null;
  }

}
