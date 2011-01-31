package com.sirika.openplacesearch.api.gisfeature

import org.joda.time.DateTimeZone

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
trait TimeZoneProvider {
  def timeZone : Option[DateTimeZone]
}