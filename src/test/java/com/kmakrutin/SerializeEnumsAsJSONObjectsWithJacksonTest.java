package com.kmakrutin;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmakrutin.enums.Distance;
import com.kmakrutin.enums.DistanceFormatted;
import com.kmakrutin.enums.DistanceValue;
import com.kmakrutin.enums.DistanceWithSerializer;
import com.kmakrutin.enums.WithEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializeEnumsAsJSONObjectsWithJacksonTest
{
  @Test
  public void defaultMapperSerialization() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString( Distance.MILE );
    log.info( "Default enum serializing: {}", json );
    assertEquals( Distance.MILE, mapper.readValue( TestUtils.getResourceAsString( "/enums/defualt.json" ), Distance.class ) );
  }

  @Test
  public void formattedMapperSerialization() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString( DistanceFormatted.MILE );
    log.info( "Enum serializing formatted to object shape: {}", json );
    assertEquals( DistanceFormatted.MILE, mapper.readValue( TestUtils.getResourceAsString( "/enums/object_formatted.json" ), DistanceFormatted.class ) );
  }

  @Test
  public void mapperSerializationByValue() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString( DistanceValue.MILE );
    log.info( "Enum serializing to concrete value: {}", json );
    assertEquals( DistanceValue.MILE, mapper.readValue( TestUtils.getResourceAsString( "/enums/by_value.json" ), DistanceValue.class ) );
  }

  @Test
  public void mapperSerializationBySerializer() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString( DistanceWithSerializer.MILE );
    log.info( "Enum serializing using serializer: {}", json );
    assertEquals( DistanceWithSerializer.MILE, mapper.readValue( TestUtils.getResourceAsString( "/enums/by_serializer.json" ), DistanceWithSerializer.class ) );
  }

  @Test
  public void serializeWithEnum() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    WithEnum withEnum = new WithEnum();
    withEnum.setSomeString( "value" );
    withEnum.setDistance( DistanceFormatted.CENTIMETER );
    String asString = mapper.writeValueAsString( withEnum );
    log.info( "Serializing object with enum field: {}", asString );
    assertEquals( withEnum, mapper.readValue( asString, WithEnum.class ) );
  }
}
