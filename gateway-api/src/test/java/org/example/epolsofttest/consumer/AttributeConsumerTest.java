package org.example.epolsofttest.consumer;

import org.example.epolsofttest.ServiceStatus;
import org.example.epolsofttest.dto.AttributeDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeConsumerTest {

    @Autowired
    private AttributeConsumer attributeConsumer;
    private MockWebServiceServer mockWebServiceServer;

    @Before
    public void setUp()  {
        mockWebServiceServer = MockWebServiceServer.createServer(attributeConsumer);
    }

    @Test
    public void getAttributeByNameResponse() {
        Source expectedRequestPayload = new StringSource(
                "<getAttributeByNameRequest xmlns=\"http://example.org/xsd/attribute\" >" +
                            "<name>samsung</name>"+
                        "</getAttributeByNameRequest>"

        );
        Source responsePayload = new StringSource(
                "<getAttributeByNameResponse xmlns=\"http://example.org/xsd/attribute\">" +
                            "<attributeType>" +
                                "<name>samsung</name>" +
                                "<value>699</value>" +
                                "<description>white</description>" +
                            "</attributeType>" +
                        "</getAttributeByNameResponse>"
        );

        mockWebServiceServer
                .expect(payload(expectedRequestPayload))
                .andRespond(withPayload(responsePayload));

        AttributeDto attributeDto = attributeConsumer.getAttributeByNameResponse("samsung");
        Assert.assertEquals("samsung", attributeDto.getName());
        Assert.assertEquals("699", attributeDto.getValue());
        Assert.assertEquals("white", attributeDto.getDescription());
    }

    @Test
    public void getAllAttributes() {
        Source expectedRequestPayload = new StringSource(
                "<getAllAttributesRequest xmlns=\"http://example.org/xsd/attribute\" />"
        );
        Source responsePayload = new StringSource(
                "<getAllAttributesResponse xmlns=\"http://example.org/xsd/attribute\">" +
                        "<findAll>" +
                        "<name>Iphone_XI</name>" +
                        "<value>899</value>" +
                        "<description>black</description>" +
                        "</findAll>" +
                        "</getAllAttributesResponse>"
        );

        mockWebServiceServer
                .expect(payload(expectedRequestPayload))
                .andRespond(withPayload(responsePayload));

        Assert.assertEquals(1, attributeConsumer.getAllAttributes().size());
    }

    @Test
    public void deleteAttribute() {
        Source expectedRequestPayload = new StringSource(
                "<getDeleteAttributeRequest xmlns=\"http://example.org/xsd/attribute\">" +
                        "<name>samsung</name>"+
                        "</getDeleteAttributeRequest>"
        );
        Source responsePayload = new StringSource(
                "<getDeleteAttributeResponse xmlns=\"http://example.org/xsd/attribute\">" +
                        "<serviceStatus>" +
                        "<statusCode>SUCCESS</statusCode>" +
                        "<message>Content Deleted Successfully</message>" +
                        "</serviceStatus>" +
                        "</getDeleteAttributeResponse>"
        );

        mockWebServiceServer
                .expect(payload(expectedRequestPayload))
                .andRespond(withPayload(responsePayload));

        ServiceStatus serviceStatus = attributeConsumer.deleteAttribute("samsung");

        Assert.assertEquals("SUCCESS", serviceStatus.getStatusCode());
        Assert.assertEquals("Content Deleted Successfully", serviceStatus.getMessage());
    }

    @Test
    public void updateAttribute() {
        Source expectedRequestPayload = new StringSource(
                "<getUpdateAttributeRequest xmlns=\"http://example.org/xsd/attribute\">" +
                            "<attributeType>" +
                                "<name>samsung</name>" +
                                "<value>123</value>" +
                                "<description>123</description>" +
                            "</attributeType>" +
                        "</getUpdateAttributeRequest>"
        );
        Source responsePayload = new StringSource(
                "<getUpdateAttributeResponse xmlns=\"http://example.org/xsd/attribute\">" +
                            "<serviceStatus>" +
                                "<statusCode>SUCCESS</statusCode>" +
                                "<message>Content updated Successfully</message>" +
                            "</serviceStatus>" +
                        "</getUpdateAttributeResponse>"
        );

        mockWebServiceServer
                .expect(payload(expectedRequestPayload))
                .andRespond(withPayload(responsePayload));

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setName("samsung");
        attributeDto.setValue("123");
        attributeDto.setDescription("123");
        ServiceStatus serviceStatus = attributeConsumer.updateAttribute(attributeDto);

        Assert.assertEquals("SUCCESS", serviceStatus.getStatusCode());
        Assert.assertEquals("Content updated Successfully", serviceStatus.getMessage());
    }

    @Test
    public void saveAttribute() {
        Source expectedRequestPayload = new StringSource(
                "<getAddAttributeRequest xmlns=\"http://example.org/xsd/attribute\">" +
                            "<attributeType>" +
                                "<name>samsung</name>" +
                                "<value>123</value>" +
                                "<description>123</description>" +
                            "</attributeType>" +
                        "</getAddAttributeRequest>"
        );
        Source responsePayload = new StringSource(
                "<getAddAttributeResponse xmlns=\"http://example.org/xsd/attribute\">" +
                            "<serviceStatus>" +
                                "<statusCode>SUCCESS</statusCode>" +
                                "<message>Content Added Successfully</message>" +
                            "</serviceStatus>" +
                        "</getAddAttributeResponse>"
        );

        mockWebServiceServer
                .expect(payload(expectedRequestPayload))
                .andRespond(withPayload(responsePayload));

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setName("samsung");
        attributeDto.setValue("123");
        attributeDto.setDescription("123");
        ServiceStatus serviceStatus = attributeConsumer.saveAttribute(attributeDto);

        Assert.assertEquals("SUCCESS", serviceStatus.getStatusCode());
        Assert.assertEquals("Content Added Successfully", serviceStatus.getMessage());
    }
}