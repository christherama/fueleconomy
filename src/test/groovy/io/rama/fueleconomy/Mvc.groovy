package io.rama.fueleconomy

import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Encapsulates the logic of Spring's MockMvc object and allows for the
 * capture of HTTP response details (e.g. status and payload)
 */
class Mvc {
    MockMvc mockMvc

    Mvc(Object controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    /**
     * Performs a MockMvc request and captures the response status and payload
     * @param request Request to perform
     * @return Object with `status` and `json` properties
     */
    def perform(MockHttpServletRequestBuilder request) {
        def response = mockMvc.perform(request).andReturn().response
        def body
        try {
            body = new JsonSlurper().parseText(response.contentAsString)
        } catch (IllegalArgumentException ex) {
            body = null
        }
        return [status: response.status, json: body]
    }
}
