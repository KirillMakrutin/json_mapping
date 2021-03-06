package com.kmakrutin.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ItemSerializer extends StdSerializer<Item>
{
  public ItemSerializer()
  {
    super( Item.class );
  }

  @Override
  public void serialize( Item item, JsonGenerator jgen, SerializerProvider serializerProvider ) throws IOException
  {
    jgen.writeStartObject();
    jgen.writeNumberField( "id", item.getId() );
    jgen.writeStringField( "itemName", item.getName() );
    jgen.writeNumberField( "owner", item.getOwner().getId() );
    jgen.writeEndObject();
  }
}
