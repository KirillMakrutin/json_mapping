package com.kmakrutin;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat( shape = JsonFormat.Shape.OBJECT )
@AllArgsConstructor
@Getter
public enum DistanceFormatted implements Serializable
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
   * To Deserialize from the Object shape
   */
  @JsonCreator
  public static DistanceFormatted fromObject( final Map<String, Object> obj )
  {
    if ( obj.containsKey( "unit" ) )
    {
      return Arrays.stream( values() )
          .filter( distance -> distance.unit.equals( obj.get( "unit" ) ) )
          .findFirst()
          .orElse( null );
    }

    return null;
  }
}
