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

package org.camelcookbook.ws.operation;

import java.util.concurrent.atomic.AtomicInteger;

import org.camelcookbook.ws.payment_service_v2.types.TransferRequest;
import org.camelcookbook.ws.payment_service_v2.types.TransferResponse;

public class PaymentServiceV2Impl {
    private AtomicInteger idGenerator = new AtomicInteger();

    public TransferResponse transfer(TransferRequest request) {
        TransferResponse response = new TransferResponse();

        response.setId(String.valueOf(idGenerator.incrementAndGet()));
        response.setReply("OK");

        return response;
    }
}
