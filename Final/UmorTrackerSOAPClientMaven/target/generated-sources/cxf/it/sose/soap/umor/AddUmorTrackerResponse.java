
package it.sose.soap.umor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per addUmorTrackerResponse complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="addUmorTrackerResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="return" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addUmorTrackerResponse", propOrder = {
    "_return"
})
public class AddUmorTrackerResponse {

    @XmlElement(name = "return")
    protected boolean _return;

    /**
     * Recupera il valore della proprietà return.
     * 
     */
    public boolean isReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietà return.
     * 
     */
    public void setReturn(boolean value) {
        this._return = value;
    }

}
