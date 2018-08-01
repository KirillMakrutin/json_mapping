package com.kmakrutin.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


public class DistanceSerializer extends StdSerializer<DistanceWithSerializer>
{
  protected DistanceSerializer()
  {
    super( DistanceWithSerializer.class );
  }

  @Override
  public void serialize( DistanceWithSerializer distance, JsonGenerator generator, SerializerProvider serializerProvider ) throws IOException
  {
    generator.writeStartObject();
    generator.writeFieldName( "name" );
    generator.writeString( distance.name() );
    generator.writeFieldName( "unit" );
    generator.writeString( distance.getUnit() );
    generator.writeFieldName( "meters" );
    generator.writeNumber( distance.getMeters() );
    generator.writeEndObject();
  }
}
