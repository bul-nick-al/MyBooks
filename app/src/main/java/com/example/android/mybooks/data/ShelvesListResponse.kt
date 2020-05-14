package com.example.android.mybooks.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "GoodreadsResponse")
class ShelvesListResponse {

    @set:Element
    @get:Element
    public var shelves: Shelves? = null

    @Root(strict = false)
    class Shelves {
        @set:ElementList(entry = "user_shelf", inline = true, required = false)
        @get:ElementList(entry = "user_shelf", inline = true, required = false)
        public var userShelves: List<UserShelf>? = null

        @Root(strict = false)
        class UserShelf {
            @set:Element(name = "id")
            @get:Element(name = "id")
            public var id: Int? = null

            @set:Element(name = "name")
            @get:Element(name = "name")
            public var name: String? = null
        }
    }
}