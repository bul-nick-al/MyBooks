package com.example.android.mybooks.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "GoodreadsResponse")
class SearchBooksResponse {

    @set:Element
    @get:Element
    public var search: Search? = null

    @Root(strict = false)
    class Search {

        @set:ElementList
        @get:ElementList
        public var results: List<Work>? = null


        @Root(strict = false)
        class Work {

            @set:Element(name = "best_book")
            @get:Element(name = "best_book")
            public var bestBook: BestBook? = null

            @Root(strict = false)
            class BestBook {

                @set:Element
                @get:Element
                public var id: Int? = null

                @set:Element
                @get:Element
                public var title: String? = null

                @set:Element
                @get:Element
                public var author: Author? = null

                @set:Element(name = "image_url")
                @get:Element(name = "image_url")
                public var imageUrl: String? = null

                @set:Element(name = "small_image_url")
                @get:Element(name = "small_image_url")
                public var smallImageUrl: String? = null

                @Root(strict = false)
                class Author {
                    @set:Element
                    @get:Element
                    public var id: Int? = null

                    @set:Element
                    @get:Element
                    public var name: String? = null
                }

            }

        }
    }
}