package com.example.android.mybooks.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "GoodreadsResponse")
class BookDetailsResponse {

    @set:Element
    @get:Element
    public var book: Book? = null

    @Root(strict = false)
    class Book {
        @set:Element(name = "id")
        @get:Element(name = "id")
        public var id: Int? = null

        @set:Element(name = "title")
        @get:Element(name = "title")
        public var title: String? = null

        @set:Element(name = "image_url")
        @get:Element(name = "image_url")
        public var imageUrl: String? = null

        @set:Element(name = "publication_year")
        @get:Element(name = "publication_year")
        public var year: Int? = null

        @set:Element(name = "description")
        @get:Element(name = "description")
        public var description: String? = null

        @set:Element(name = "average_rating")
        @get:Element(name = "average_rating")
        public var averageRating: Float? = null

        @set:Element(name = "num_pages")
        @get:Element(name = "num_pages")
        public var pages: Int? = null

        @set:Element(name = "ratings_count")
        @get:Element(name = "ratings_count")
        public var ratingsCount: Int? = null

        @set:Element(name = "link")
        @get:Element(name = "link")
        public var link: String? = null

        @set:Element(name = "reviews_widget")
        @get:Element(name = "reviews_widget")
        public var reviewsWidget: String? = null
    }
}