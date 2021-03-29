
package net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GetGeoIPResult" type="{http://www.webservicex.net/}GeoIP" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getGeoIPResult"
})
@XmlRootElement(name = "GetGeoIPResponse")
public class GetGeoIPResponse {

    @XmlElement(name = "GetGeoIPResult")
    protected GeoIP getGeoIPResult;

    /**
     * Gets the value of the getGeoIPResult property.
     * 
     * @return
     *     possible object is
     *     {@link GeoIP }
     *     
     */
    public GeoIP getGetGeoIPResult() {
        return getGeoIPResult;
    }

    /**
     * Sets the value of the getGeoIPResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoIP }
     *     
     */
    public void setGetGeoIPResult(GeoIP value) {
        this.getGeoIPResult = value;
    }

}
