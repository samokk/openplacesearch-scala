package com.sirika.openplacesearch.api.language

import com.google.common.base.Objects
import com.google.common.base.Strings

object Language {
    def apply(name : String, alpha3Code : String, alpha2Code : Option[String]=None) = new Language(name, alpha3Code, alpha2Code)
    def unapply(l : Language) = Some(l.name, l.alpha3Code, l.alpha2Code )
}

/**
 * ISO 639 Language (639-1 -alpha2- and 639-2 -alpha3-)
 * 
 * Codes are written lowercase !!! And ISO 639-2 codes are not considered here
 * 
 * @param alpha2Code <a href="http://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1</a>
 * @param alpha3Code <a href="http://en.wikipedia.org/wiki/ISO_639-1">ISO 639-3</a>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 * @see <a href="http://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1</a>
 * @see <a href="http://en.wikipedia.org/wiki/ISO_639-3">ISO 639-3</a>
 */
final class Language(val name : String, val alpha3Code : String, val alpha2Code : Option[String]) {
    require(Option(name) exists (_.nonEmpty), "name is required")
    require(Option(alpha3Code) exists (_.nonEmpty), "alpha3Code is required")
    require(alpha2Code != null, "alpha2Code must be a non-null Option")
    
    override def hashCode(): Int = Objects.hashCode(name, alpha3Code, alpha2Code)
    
    override def equals(other: Any): Boolean = other match {
        case Language(`name`, `alpha3Code`, `alpha2Code`) => true
        case _ => false
    }
    
    override def toString() : String = Objects.toStringHelper(this)
        .add("name", name)    
        .add("alpha3", alpha3Code)
        .add("alpha2", alpha2Code)
        .toString()
}