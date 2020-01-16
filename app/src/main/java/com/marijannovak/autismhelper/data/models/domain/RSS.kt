package com.marijannovak.autismhelper.data.models.domain

import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss")
data class RSS (
    @field:Element(name = "channel", required = false)
    val channel: Channel,
    @field:Attribute(name = "npr", required = false)
    val npr: String,
    @field:Attribute(name = "nprml", required = false)
    val nprml: String,
    @field:Attribute(name = "itunes", required = false)
    val itunes: String,
    @field:Attribute(name = "content", required = false)
    val content: String,
    @field:Attribute(name = "dc", required = false)
    val dc: String,
    @field:Attribute(name = "version", required = false)
    val version: String
)

data class Channel (
    @field:Element(name = "title", required = false)
    val title: String,
    @field:Element(name = "link", required = false)
    val link: String,
    @field:Element(name = "description", required = false)
    val description: String,
    @field:Element(name = "language", required = false)
    val language: String,
    @field:Element(name = "copyright", required = false)
    val copyright: String,
    @field:Element(name = "generator", required = false)
    val generator: String,
    @field:Element(name = "lastBuildDate", required = false)
    val lastBuildDate: String,
    @field:Element(name = "image", required = false)
    val image: Image,
    @field:ElementList(name = "item", inline = true, required = false)
    val feedItems: MutableList<FeedItem>
) 

data class Image (
    @field:Element(name = "url", required = false)
    val url: String,
    @field:Element(name = "title", required = false)
    val title: String,
    @field:Element(name = "link", required = false)
    val link: String
)

@Root(name = "item")
data class FeedItem (
    @field:Element(name = "title", required = false)
    val title: String,
    @field:Element(name = "description", required = false)
    val description: String,
    @field:Element(name = "pubDate", required = false)
    val pubDate: String,
    @field:Element(name = "link", required = false)
    val link: String,
    @field:Element(name = "guid", required = false)
    @PrimaryKey
    val guid: String,
    @field:Element(name = "encoded", required = false)
    val encoded: String,
    @field:Element(name = "creator", required = false)
    val creator: String
)
