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

grails.release.scm.enabled = false

grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits "global"
    log "warn"

    repositories {
        grailsHome()
        grailsCentral()
    }

    plugins {
        build ":spock:0.5-groovy-1.7"
        build ":release:latest.integration", {export = false}
        build ":svn:latest.integration", {export = false}
    }

    dependencies {
        // here because the release/svn plugins can't be bothered to resolve their own dependencies properly
        build("org.tmatesoft.svnkit:svnkit:latest.integration") {
            excludes "jna", "trilead-ssh2", "sqljet"
            export = false
        }
    }
}
