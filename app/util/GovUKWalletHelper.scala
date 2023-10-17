/*
 * Copyright 2023 HM Revenue & Customs
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

package util

import com.google.inject.Inject
import config.AppConfig
import models.{CredentialSubject, GovUKVCDocument, Name, NameParts, SocialSecurityRecord, VCDocument}

class GovUKWalletHelper @Inject()(val config: AppConfig) {

  def createGovUKVCDocument(givenName :List[String], familyName: String, nino: String): GovUKVCDocument = {
    val nameParts = NameParts(givenName, familyName)
    val name = Name(nameParts)
    val socialSecurityRecord = SocialSecurityRecord(nino)
    val credentialSubject = CredentialSubject(name, socialSecurityRecord)
    val vcDocument = VCDocument(List("VerifiableCredential", "SocialSecurityCredential"), credentialSubject)
    GovUKVCDocument(
      config.govukPassSub,
      config.govukPassNbf,
      config.govukPassIss,
      config.govukPassExp,
      config.govukPassIat,
      vcDocument
    )
  }

}