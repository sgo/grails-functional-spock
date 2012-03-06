package grails.plugin.functional.spock
/*
* Copyright 2009 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import grails.plugin.spock.test.GrailsSpecTestType
import spock.lang.Specification

class SpecTestTypeLoaderSpec extends Specification {
    private Map binding
    private SpecTestTypeLoader loader

    def setup() {
        binding = [variables: [:]]
        loader = new SpecTestTypeLoader(binding, GrailsSpecTestType)
    }

    def "given binding defines no functionalTests variable then skip execution"() {
        expect:
        loader.registerFunctionalSpecSupport() == null
    }

    def "when functionalTests do not define a spec type then add it"() {
        given:
        binding.variables = [functionalTests: []]

        when:
        loader.registerFunctionalSpecSupport()

        then:
        registeredTestType.name == 'spock'
        registeredTestType.relativeSourcePath == 'functional'
    }

    private GrailsSpecTestType getRegisteredTestType() {
        binding.variables.functionalTests.find {it in GrailsSpecTestType}
    }

    def "if type already registered skip"() {
        given:
        binding.variables = [functionalTests: []]

        when:
        loader.registerFunctionalSpecSupport()
        loader.registerFunctionalSpecSupport()

        then:
        functionalTestTypes.size() == 1
    }

    private List getFunctionalTestTypes() {
        binding.variables.functionalTests
    }
}
