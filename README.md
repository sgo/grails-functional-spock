Grails functional-spock plugin
==============================

Allows you to write and run Spock specs under the functional test scope.
For details on how to write Spock specs see the documentation for the [spock plugin](http://grails.org/plugin/spock).

Installation
------------

For grails-1.3 add the following line to the plugins section of your BuildConfig.groovy

    compile ":functional-spock:0.5-groovy-1.7"

Running Tests
-------------

    grails test-app functional:spock

