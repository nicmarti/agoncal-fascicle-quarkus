package org.agoncal.fascicle.quarkus.http.jsonp;

import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerReaderWriterTest {

    @Test
    void writeCustomer() throws IOException {
      CustomerReaderWriter writer = new CustomerReaderWriter();
      writer.writeCustomer();
    }

    @Test
    void writeCustomerWithConfig() throws IOException {
      CustomerReaderWriter writer = new CustomerReaderWriter();
      writer.writeCustomerWithConfig();
    }

    @Test
    void readPurchaseOrder() throws FileNotFoundException {
      CustomerReaderWriter reader = new CustomerReaderWriter();
      JsonObject jsonObject = reader.readCustomer();

      // tag::adocReadCustomer[]
      JsonString firstName = jsonObject.getJsonString("firstName");
      JsonObject address = jsonObject.getJsonObject("address");
      JsonArray phones = jsonObject.getJsonArray("phoneNumbers");

      // end::adocReadCustomer[]
      assertEquals("Antonio", firstName.getString());
      assertEquals("21 Ritherdon Rd", address.getJsonString("street").getString());
      assertEquals("Brighton", address.getJsonString("city").getString());
      assertEquals("UK", address.getJsonString("country").getString());
      assertEquals(2, phones.size());
      //phones.
    }

    @Test
    void navigatePurchaseOrder() throws FileNotFoundException {
      CustomerReaderWriter reader = new CustomerReaderWriter();
      JsonObject jsonObject = reader.readCustomer();

      // tag::adocReadCustomer[]
      // Getting the value of a JsonString
      String firstName = jsonObject.getJsonString("firstName").getString();
      // end::adocReadCustomer[]
      assertEquals("Antonio", firstName);
    }
}
