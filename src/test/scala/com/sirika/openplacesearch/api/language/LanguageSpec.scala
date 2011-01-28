package com.sirika.openplacesearch.api.language

import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages._
import com.sirika.openplacesearch.api.language._

@RunWith(classOf[JUnitRunner])
class LanguageSpec extends Spec with ShouldMatchers {
    describe("Language") {
        describe("constructor") {
            it("should require a non-empty alpha3 code") {
                evaluating { Language(name="Hawaiian", alpha3Code="") } should produce [IllegalArgumentException]
            }

            it("should require a non-empty name") {
                evaluating { Language(name="", alpha3Code="haw") } should produce [IllegalArgumentException]
            }
            
            it("should require a non-null alpha2 code") {
                evaluating { Language(name="Hawaiian", "haw", null) } should produce [IllegalArgumentException]
            }
        }

        describe("toString") {
            it("should return name, alpha3 and alpha2 for French") {
                french.toString should be === "Language{name=French, alpha3=fra, alpha2=Some(fr)}"
            }

            it("should return name, alpha3 for Hawaiian") {
                hawaiian.toString should be === "Language{name=Hawaiian, alpha3=haw, alpha2=None}"
            }
        }
    }
    
    describe("A given language") {
            it("should be equal to another language that has the same alpha3Code") {
                french should be === french
            }

            it("should have the same hashcode as another language that has the same alpha3Code") {
                french.hashCode should be === french.hashCode
            }

            it("should not be equal to another language that has a different alpha3Code") {
                french should not be hawaiian
            }

            it("should not have the same hashcode as another language that has a different alpha3Code") {
                french.hashCode should not be hawaiian.hashCode
            }
        }
}