package io.rama.fueleconomy

import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class VehicleContractSpec extends Specification {
    Mvc mvc
    VinDecodingService vinDecodingService

    def setup() {
        vinDecodingService = Mock()
        mvc = new Mvc(new VehicleController(vinDecodingService))
    }

    def "GET /vehicles/:vin responds with 200 OK"() {
        expect:
        mvc.perform(get("/vehicles/some-vin")).status == 200
    }

    def "GET /vehicles/:vin responds with year, make, and model in payload"() {
        given:
        vinDecodingService.findByVin(_) >> Vehicle.builder()
                .vin("some-vin")
                .make("some-make")
                .model("some-model")
                .year(2017)
                .build()

        expect:
        def response = mvc.perform(get("/vehicles/some-vin"))
        response.json.vehicle
        response.json.vehicle.vin == "some-vin"
        response.json.vehicle.make == "some-make"
        response.json.vehicle.model == "some-model"
        response.json.vehicle.year == 2017
    }

    def "GET /vehicles/:vin/mpg responds with 200 OK"() {
        expect:
        mvc.perform(get("/vehicles/some-vin/mpg")).status == 200
    }

    def "GET /vehicles/:vin/mpg responds with models"() {
        given:
        vinDecodingService.findByVin(_) >>  Vehicle.builder()
                .vin("some-vin")
                .make("some-make")
                .model("some-model")
                .year(2017)
                .build()

        expect:
        def response = mvc.perform(get("/vehicles/some-vin/mpg"))
        response.json.models instanceof Collection
    }
}
