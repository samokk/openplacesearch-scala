package com.sirika.openplacesearch.api.gisfeature

import com.sirika.openplacesearch.api.administrativedivision.AdministrativeEntity

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait ParentAdministrativeEntityProvider {
  def parentAdministrativeEntity: Option[AdministrativeEntity]
}