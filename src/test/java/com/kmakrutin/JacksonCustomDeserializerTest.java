package com.kmakrutin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kmakrutin.deserializer.Item;
import com.kmakrutin.deserializer.ItemDeserializer;
import com.kmakrutin.deserializer.ItemWithDeserializer;
import com.kmakrutin.deserializer.User;

public class JacksonCustomDeserializerTest
{
  @Test
  public void defaultDeserializing() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    Item item = mapper.readValue( TestUtils.getResourceAsString( "/deserializer/default.json" ), Item.class );
    assertEquals( 1, item.getId() );
    assertEquals( "theItem", item.getName() );
    User owner = item.getOwner();
    assertEquals( 2, owner.getId() );
    assertEquals( "theUser", owner.getName() );
  }

  @Test( expected = UnrecognizedPropertyException.class )
  public void defaultDeserializingFails() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    Item item = mapper.readValue( TestUtils.getResourceAsString( "/deserializer/custom_item.json" ), Item.class );
    assertNotNull( item );
  }

  @Test
  public void customDeserializer() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addDeserializer( Item.class, new ItemDeserializer() );
    mapper.registerModule( simpleModule );

    Item item = mapper.readValue( TestUtils.getResourceAsString( "/deserializer/custom_item.json" ), Item.class );
    assertNotNull( item );
    assertEquals( 1, item.getId() );
    assertEquals( "theItem", item.getName() );
    User owner = item.getOwner();
    assertEquals( 2, owner.getId() );
    assertNull( owner.getName() );
  }

  @Test
  public void customDeserializerInClass() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();

    ItemWithDeserializer item = mapper.readValue( TestUtils.getResourceAsString( "/deserializer/custom_item.json" ), ItemWithDeserializer.class );
    assertNotNull( item );
    assertEquals( 1, item.getId() );
    assertEquals( "theItem", item.getName() );
    User owner = item.getOwner();
    assertEquals( 2, owner.getId() );
    assertNull( owner.getName() );
  }
}
