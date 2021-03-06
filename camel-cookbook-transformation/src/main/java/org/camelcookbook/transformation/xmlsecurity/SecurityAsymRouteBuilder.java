/*
 * Copyright (C) Scott Cranton and Jakub Korab
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camelcookbook.transformation.xmlsecurity;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.xml.security.encryption.XMLCipher;

public class SecurityAsymRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final String tagXPath = "/booksignings/store/address";
        final boolean secureTagContents = true;

        final KeyStoreParameters trustStoreParameters = new KeyStoreParameters();
        trustStoreParameters.setResource("truststore.jks");
        trustStoreParameters.setPassword("camelstorepass");

        final KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
        keyStoreParameters.setResource("keystore.jks");
        keyStoreParameters.setPassword("camelstorepass");

        from("direct:marshal")
            .marshal().secureXML(tagXPath, secureTagContents, "scott", XMLCipher.TRIPLEDES, XMLCipher.RSA_v1dot5, trustStoreParameters)
            .to("mock:marshalResult");

        from("direct:unmarshal")
            .unmarshal().secureXML(tagXPath, secureTagContents, "scott", XMLCipher.TRIPLEDES, XMLCipher.RSA_v1dot5, keyStoreParameters, "camelkeypass")
            .to("mock:unmarshalResult");
    }
}
