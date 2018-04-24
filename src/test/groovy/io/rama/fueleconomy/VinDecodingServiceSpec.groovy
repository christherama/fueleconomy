package io.rama.fueleconomy

import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class VinDecodingServiceSpec extends Specification {
    VinDecodingService vinDecodingService
    RestTemplate restTemplate = Mock()

    def setup() {
        vinDecodingService = new NhtsaVinDecodingService(restTemplate)
    }

    def "findByVin responds with year, make and model" () {
        given:
        restTemplate.getForObject(*_) >> new NhtsaResponse(
                results: [
                        new NhtsaResult(["value": "2012","variable": "Model Year"]),
                        new NhtsaResult(["value": "KIA","variable": "Make"]),
                        new NhtsaResult(["value": "Soul","variable": "Model"])
                ]
        )

        when:
        Vehicle vehicle = vinDecodingService.findByVin("KNDJT2A6XC7453418")

        then:
        vehicle.year == 2012
        vehicle.make.toLowerCase() == "kia"
        vehicle.model.toLowerCase() == "soul"
    }
}
