package com.kmakrutin;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat( shape = JsonFormat.Shape.OBJECT )
@AllArgsConstructor
@Getter
public enum DistanceValue
{
  KILOMETER( "km", 1000 ),
  MILE( "miles", 1609.34 ),
  METER( "meters", 1 ),
  INCH( "inches", 0.0254 ),
  CENTIMETER( "cm", 0.01 ),
  MILLIMETER( "mm", 0.001 );

  private final String unit;
  @JsonValue
  private final double meters;

  /**
   * To Deserialize from the value
   */
  @JsonCreator
  public static DistanceValue fromObject( String value )
  {
    if ( value != null )
    {
      return Arrays.stream( values() )
          .filter( distance -> distance.meters == Double.valueOf( value ) )
          .findFirst()
          .orElse( null );
    }

    return null;
  }
}
