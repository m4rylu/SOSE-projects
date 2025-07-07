
package it.sose.advice.mood;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.sose.advice.mood package. 
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

    private static final QName _Advice_QNAME = new QName("http://mood.advice.sose.it/", "advice");
    private static final QName _AdviceResponse_QNAME = new QName("http://mood.advice.sose.it/", "adviceResponse");
    private static final QName _ExecutionException_QNAME = new QName("http://mood.advice.sose.it/", "ExecutionException");
    private static final QName _InterruptedException_QNAME = new QName("http://mood.advice.sose.it/", "InterruptedException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.sose.advice.mood
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Advice }
     * 
     * @return
     *     the new instance of {@link Advice }
     */
    public Advice createAdvice() {
        return new Advice();
    }

    /**
     * Create an instance of {@link AdviceResponse }
     * 
     * @return
     *     the new instance of {@link AdviceResponse }
     */
    public AdviceResponse createAdviceResponse() {
        return new AdviceResponse();
    }

    /**
     * Create an instance of {@link ExecutionException }
     * 
     * @return
     *     the new instance of {@link ExecutionException }
     */
    public ExecutionException createExecutionException() {
        return new ExecutionException();
    }

    /**
     * Create an instance of {@link InterruptedException }
     * 
     * @return
     *     the new instance of {@link InterruptedException }
     */
    public InterruptedException createInterruptedException() {
        return new InterruptedException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Advice }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Advice }{@code >}
     */
    @XmlElementDecl(namespace = "http://mood.advice.sose.it/", name = "advice")
    public JAXBElement<Advice> createAdvice(Advice value) {
        return new JAXBElement<>(_Advice_QNAME, Advice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdviceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdviceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://mood.advice.sose.it/", name = "adviceResponse")
    public JAXBElement<AdviceResponse> createAdviceResponse(AdviceResponse value) {
        return new JAXBElement<>(_AdviceResponse_QNAME, AdviceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecutionException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExecutionException }{@code >}
     */
    @XmlElementDecl(namespace = "http://mood.advice.sose.it/", name = "ExecutionException")
    public JAXBElement<ExecutionException> createExecutionException(ExecutionException value) {
        return new JAXBElement<>(_ExecutionException_QNAME, ExecutionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://mood.advice.sose.it/", name = "InterruptedException")
    public JAXBElement<InterruptedException> createInterruptedException(InterruptedException value) {
        return new JAXBElement<>(_InterruptedException_QNAME, InterruptedException.class, null, value);
    }

}
