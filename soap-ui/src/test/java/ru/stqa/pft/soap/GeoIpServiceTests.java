package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;

import org.testng.annotations.Test;




public class GeoIpServiceTests {
    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP(("194.28.29.152"));
        assert geoIP.getCountryCode().equals("RUS");
    }


}
