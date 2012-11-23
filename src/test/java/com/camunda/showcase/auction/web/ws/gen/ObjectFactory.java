
package com.camunda.showcase.auction.web.ws.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.camunda.showcase.auction.web.ws package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateAuctionResponse_QNAME = new QName("http://ws.web.auction.showcase.camunda.com/", "createAuctionResponse");
    private final static QName _CreateAuction_QNAME = new QName("http://ws.web.auction.showcase.camunda.com/", "createAuction");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.camunda.showcase.auction.web.ws
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateAuctionResponse }
     *
     */
    public CreateAuctionResponse createCreateAuctionResponse() {
        return new CreateAuctionResponse();
    }

    /**
     * Create an instance of {@link CreateAuction }
     *
     */
    public CreateAuction createCreateAuction() {
        return new CreateAuction();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAuctionResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.web.auction.showcase.camunda.com/", name = "createAuctionResponse")
    public JAXBElement<CreateAuctionResponse> createCreateAuctionResponse(CreateAuctionResponse value) {
        return new JAXBElement<CreateAuctionResponse>(_CreateAuctionResponse_QNAME, CreateAuctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAuction }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.web.auction.showcase.camunda.com/", name = "createAuction")
    public JAXBElement<CreateAuction> createCreateAuction(CreateAuction value) {
        return new JAXBElement<CreateAuction>(_CreateAuction_QNAME, CreateAuction.class, null, value);
    }

}
