
package it.sose.soap.sleep;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.sose.soap.sleep package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.sose.soap.sleep
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddSleepTrackerRequest }
     * 
     * @return
     *     the new instance of {@link AddSleepTrackerRequest }
     */
    public AddSleepTrackerRequest createAddSleepTrackerRequest() {
        return new AddSleepTrackerRequest();
    }

    /**
     * Create an instance of {@link AddSleepTrackerResponse }
     * 
     * @return
     *     the new instance of {@link AddSleepTrackerResponse }
     */
    public AddSleepTrackerResponse createAddSleepTrackerResponse() {
        return new AddSleepTrackerResponse();
    }

    /**
     * Create an instance of {@link Last7DaysValuesRequest }
     * 
     * @return
     *     the new instance of {@link Last7DaysValuesRequest }
     */
    public Last7DaysValuesRequest createLast7DaysValuesRequest() {
        return new Last7DaysValuesRequest();
    }

    /**
     * Create an instance of {@link Last7DaysValuesResponse }
     * 
     * @return
     *     the new instance of {@link Last7DaysValuesResponse }
     */
    public Last7DaysValuesResponse createLast7DaysValuesResponse() {
        return new Last7DaysValuesResponse();
    }

    /**
     * Create an instance of {@link PrintSleepTrackerRequest }
     * 
     * @return
     *     the new instance of {@link PrintSleepTrackerRequest }
     */
    public PrintSleepTrackerRequest createPrintSleepTrackerRequest() {
        return new PrintSleepTrackerRequest();
    }

    /**
     * Create an instance of {@link PrintSleepTrackerResponse }
     * 
     * @return
     *     the new instance of {@link PrintSleepTrackerResponse }
     */
    public PrintSleepTrackerResponse createPrintSleepTrackerResponse() {
        return new PrintSleepTrackerResponse();
    }

}
