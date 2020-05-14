package com.example.android.mybooks.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "GoodreadsResponse")
class UserBooksResponse {

    @set:ElementList
    @get:ElementList
    public var reviews: List<Review>? = null

    @Root(strict = false)
    class Review {
        @set:Element(name = "started_at", required = false)
        @get:Element(name = "started_at", required = false)
        public var startedAt: String? = null

        @set:Element(name = "read_at", required = false)
        @get:Element(name = "read_at", required = false)
        public var readAt: String? = null

        @set:Element
        @get:Element
        public var book: Book? = null

        @Root(strict = false)
        class Book : BookResponse {
            @set:Element
            @get:Element
            public var title: String? = null

            @set:Element(name = "image_url")
            @get:Element(name = "image_url")
            public var imageUrl: String? = null

            @set:Element
            @get:Element
            public var id: Int? = null

            @set:Element
            @get:Element
            public var description: String? = null

            override fun getBookId(): Int? {
                return id
            }

            override fun getBookTitle(): String? {
                return title
            }

            override fun getBookImageUrl(): String? {
                return imageUrl
            }
        }

        @set:Element
        @get:Element
        public var shelves: Shelves? = null

        @Root(strict = false)
        class Shelves {
            @set:ElementList(entry = "shelf", inline = true, required = false)
            @get:ElementList(entry = "shelf", inline = true, required = false)
            public var shelves: List<Shelf>? = null

            @Root(strict = false)
            class Shelf {
                @get:Attribute(name = "name")
                @set:Attribute(name = "name")
                public var name: String? = null
            }
        }
    }
}