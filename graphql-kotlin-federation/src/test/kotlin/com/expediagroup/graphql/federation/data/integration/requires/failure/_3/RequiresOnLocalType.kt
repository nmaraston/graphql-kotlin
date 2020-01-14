/*
 * Copyright 2020 Expedia, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.expediagroup.graphql.federation.data.integration.requires.failure._3

import com.expediagroup.graphql.federation.directives.FieldSet
import com.expediagroup.graphql.federation.directives.KeyDirective
import com.expediagroup.graphql.federation.directives.RequiresDirective
import io.mockk.mockk

/*
# example of invalid usage of @requires directive when its applied on a field from a local type
type RequiresOnLocalType @key(fields : "id") {
  description: String!
  id: String!
  shippingCost: String! @requires(fields : "weight")
  weight: Float!
}
 */
@KeyDirective(fields = FieldSet("id"))
class RequiresOnLocalType(val id: String, val description: String) {
    var weight: Double = 0.0

    @RequiresDirective(FieldSet("weight"))
    fun shippingCost(): String = "$${weight * 9.99}"
}

class RequiresOnLocalTypeQuery {
    fun requiresQuery(): RequiresOnLocalType = mockk()
}
