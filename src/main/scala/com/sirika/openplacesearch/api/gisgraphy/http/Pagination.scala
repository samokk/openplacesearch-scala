package com.sirika.openplacesearch.api.gisgraphy.http;

final case class Pagination(val firstResult: Int, val numberOfResults: Int) {
  require(firstResult >=1, "the first result is 1")
  require(numberOfResults >=1, "we cannot retrieve less than 1 result")
  require(numberOfResults <= 10, "stupid gisgraphy does not support more than 10 results at the same time")

  def endResult: Int = firstResult + numberOfResults
}

