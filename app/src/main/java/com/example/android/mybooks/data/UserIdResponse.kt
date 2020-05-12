package com.example.android.mybooks.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "GoodreadsResponse")
class UserIdResponse {

    @set:Element
    @get:Element
    public var user: User? = null

    @Root(strict = false)
    class User {

        @set:Attribute
        @get:Attribute
        public var id: Int? = null
    }
}