package io.rama.fueleconomy

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class VehicleContractSpec extends Specification {
    Mvc mvc
    VehicleService vehicleService;

    def setup() {
        vehicleService = Mock()
        mvc = new Mvc(new VehicleController(vehicleService))
    }

    def "GET /vehicles/:vin responds with 200 OK"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/some-vin")).status == 200
    }

    def "GET /vehicles/:vin responds with year, make, and model in payload"() {
        given:
        vehicleService.findByVin(_) >> new Vehicle([
                vin  : "some-vin",
                make : "some-make",
                model: "some-model",
                year : 2017
        ])

        expect:
        def response = mvc.perform(get("/vehicles/some-vin"))
        response.json.vehicle
        response.json.vehicle.vin
        response.json.vehicle.make
        response.json.vehicle.model
        response.json.vehicle.year
    }
}
