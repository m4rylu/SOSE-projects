
package it.sose.soap.umor;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.sose.soap.umor package. 
 * <p>An ObjectFactory allows you to programmatically 
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

    private static final QName _AddUmorTracker_QNAME = new QName("http://umor.soap.sose.it/", "addUmorTracker");
    private static final QName _AddUmorTrackerResponse_QNAME = new QName("http://umor.soap.sose.it/", "addUmorTrackerResponse");
    private static final QName _PrintUmorTracker_QNAME = new QName("http://umor.soap.sose.it/", "printUmorTracker");
    private static final QName _PrintUmorTrackerResponse_QNAME = new QName("http://umor.soap.sose.it/", "printUmorTrackerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.sose.soap.umor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddUmorTracker }
     * 
     * @return
     *     the new instance of {@link AddUmorTracker }
     */
    public AddUmorTracker createAddUmorTracker() {
        return new AddUmorTracker();
    }

    /**
     * Create an instance of {@link AddUmorTrackerResponse }
     * 
     * @return
     *     the new instance of {@link AddUmorTrackerResponse }
     */
    public AddUmorTrackerResponse createAddUmorTrackerResponse() {
        return new AddUmorTrackerResponse();
    }

    /**
     * Create an instance of {@link PrintUmorTracker }
     * 
     * @return
     *     the new instance of {@link PrintUmorTracker }
     */
    public PrintUmorTracker createPrintUmorTracker() {
        return new PrintUmorTracker();
    }

    /**
     * Create an instance of {@link PrintUmorTrackerResponse }
     * 
     * @return
     *     the new instance of {@link PrintUmorTrackerResponse }
     */
    public PrintUmorTrackerResponse createPrintUmorTrackerResponse() {
        return new PrintUmorTrackerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUmorTracker }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUmorTracker }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "addUmorTracker")
    public JAXBElement<AddUmorTracker> createAddUmorTracker(AddUmorTracker value) {
        return new JAXBElement<>(_AddUmorTracker_QNAME, AddUmorTracker.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUmorTrackerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUmorTrackerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "addUmorTrackerResponse")
    public JAXBElement<AddUmorTrackerResponse> createAddUmorTrackerResponse(AddUmorTrackerResponse value) {
        return new JAXBElement<>(_AddUmorTrackerResponse_QNAME, AddUmorTrackerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintUmorTracker }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PrintUmorTracker }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "printUmorTracker")
    public JAXBElement<PrintUmorTracker> createPrintUmorTracker(PrintUmorTracker value) {
        return new JAXBElement<>(_PrintUmorTracker_QNAME, PrintUmorTracker.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintUmorTrackerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PrintUmorTrackerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "printUmorTrackerResponse")
    public JAXBElement<PrintUmorTrackerResponse> createPrintUmorTrackerResponse(PrintUmorTrackerResponse value) {
        return new JAXBElement<>(_PrintUmorTrackerResponse_QNAME, PrintUmorTrackerResponse.class, null, value);
    }

}
