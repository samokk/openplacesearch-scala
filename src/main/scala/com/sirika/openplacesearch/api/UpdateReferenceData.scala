package com.sirika.openplacesearch.api.language
import com.sirika.commons.scala.{LineByLineInputStreamReader, ParsingWarning}
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import com.sirika.openplacesearch.api.administrativedivision.internal.FieldExtractors
import java.io.InputStreamReader
import com.google.common.io.InputSupplier

object UpdateReferenceData {
  def main(args : Array[String]) : Unit = {
    downloadReferenceFiles
    val featureIds = extractGisFeatureIds
  }

  def downloadReferenceFiles = {
    reportProgress("Downloading reference files...")
    reportProgress("[skip] SKipping iso639Languages download: not implemented yet.")
    reportProgress("[skip] SKipping countries download: not implemented yet.")
    reportProgress("[skip] SKipping admin1Codes download: not implemented yet.")
    reportProgress("[skip] SKipping admin2Codes download: not implemented yet.")
    reportProgress("[skip] SKipping features download: not implemented yet.")
    reportProgress("[skip] SKipping alternateNames download: not implemented yet.")
  }

  def extractGisFeatureIds: List[Int] = {

    val featureIds = extractCountryGisFeatureIds ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.FirstOrderAdministrativeDivisions) ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.SecondOrderAdministrativeDivisions)

    println(featureIds)
    null
  }

  def extractCountryGisFeatureIds = {
    new LineByLineInputStreamReader(ReferenceData.Countries).map { (line, lineNumber) =>
      FieldExtractors.extractFieldsFromCountryLine(line) { l =>
        l match {
          case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
          capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
          phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode)
          =>  geonamesIdToResult(geonamesId)
        }
      }
    }
  }

  def extractAdministrativeDivisionGisFeatureIds(input: InputSupplier[InputStreamReader]) = {
    new LineByLineInputStreamReader(input).map { (line, lineNumber) =>
      FieldExtractors.extractFieldsFromAdministrativeDivisionLine(line) { l =>
        l match {
          case Array(compositeCode, name, asciiName, geonamesId)
          => geonamesIdToResult(geonamesId)
        }
      }
    }
  }

  def reportProgress(x : Any) = println(x)

  def geonamesIdToResult(id: String): Either[ParsingWarning, Int] = {
    if(correctGeonamesId(id))
      Right(id.toInt)
    else
      Left(ParsingWarning("GeonamesID is required to be a positive number, but is currently: %s".format(id)))
  }
  def correctGeonamesId(id: String): Boolean = {
    Option(id).exists {s => s.nonEmpty && s.toInt > 0}
  }
}
