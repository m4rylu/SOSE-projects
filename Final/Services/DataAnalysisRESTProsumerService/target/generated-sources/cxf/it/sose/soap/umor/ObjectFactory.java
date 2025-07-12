
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

    private static final QName _Add_QNAME = new QName("http://umor.soap.sose.it/", "add");
    private static final QName _AddResponse_QNAME = new QName("http://umor.soap.sose.it/", "addResponse");
    private static final QName _LastValues_QNAME = new QName("http://umor.soap.sose.it/", "lastValues");
    private static final QName _LastValuesResponse_QNAME = new QName("http://umor.soap.sose.it/", "lastValuesResponse");
    private static final QName _Print_QNAME = new QName("http://umor.soap.sose.it/", "print");
    private static final QName _PrintResponse_QNAME = new QName("http://umor.soap.sose.it/", "printResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.sose.soap.umor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     * @return
     *     the new instance of {@link Add }
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     * @return
     *     the new instance of {@link AddResponse }
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link LastValues }
     * 
     * @return
     *     the new instance of {@link LastValues }
     */
    public LastValues createLastValues() {
        return new LastValues();
    }

    /**
     * Create an instance of {@link LastValuesResponse }
     * 
     * @return
     *     the new instance of {@link LastValuesResponse }
     */
    public LastValuesResponse createLastValuesResponse() {
        return new LastValuesResponse();
    }

    /**
     * Create an instance of {@link Print }
     * 
     * @return
     *     the new instance of {@link Print }
     */
    public Print createPrint() {
        return new Print();
    }

    /**
     * Create an instance of {@link PrintResponse }
     * 
     * @return
     *     the new instance of {@link PrintResponse }
     */
    public PrintResponse createPrintResponse() {
        return new PrintResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Add }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LastValues }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LastValues }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "lastValues")
    public JAXBElement<LastValues> createLastValues(LastValues value) {
        return new JAXBElement<>(_LastValues_QNAME, LastValues.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LastValuesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LastValuesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "lastValuesResponse")
    public JAXBElement<LastValuesResponse> createLastValuesResponse(LastValuesResponse value) {
        return new JAXBElement<>(_LastValuesResponse_QNAME, LastValuesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Print }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Print }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "print")
    public JAXBElement<Print> createPrint(Print value) {
        return new JAXBElement<>(_Print_QNAME, Print.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PrintResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://umor.soap.sose.it/", name = "printResponse")
    public JAXBElement<PrintResponse> createPrintResponse(PrintResponse value) {
        return new JAXBElement<>(_PrintResponse_QNAME, PrintResponse.class, null, value);
    }

}
