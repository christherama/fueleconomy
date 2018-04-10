package io.rama.fueleconomy

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class AppSpec extends Specification {
    def "ApplicationContext loads"() {
        expect:
        true
    }
}
