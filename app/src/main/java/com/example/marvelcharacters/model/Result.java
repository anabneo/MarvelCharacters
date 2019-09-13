package com.example.marvelcharacters.model;

import java.util.ArrayList;

public class Result {
        private float id;
        private String name;
        private String description;
        private String modified;
        Thumbnail ThumbnailObject;
        private String resourceURI;
        Comics ComicsObject;
        Series SeriesObject;
        Stories StoriesObject;
        Events EventsObject;
        ArrayList<Object> urls = new ArrayList<Object>();


        // Getter Methods

        public float getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getModified() {
            return modified;
        }

        public Thumbnail getThumbnail() {
            return ThumbnailObject;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public Comics getComics() {
            return ComicsObject;
        }

        public Series getSeries() {
            return SeriesObject;
        }

        public Stories getStories() {
            return StoriesObject;
        }

        public Events getEvents() {
            return EventsObject;
        }

        // Setter Methods

        public void setId( float id ) {
            this.id = id;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public void setDescription( String description ) {
            this.description = description;
        }

        public void setModified( String modified ) {
            this.modified = modified;
        }

        public void setThumbnail( Thumbnail thumbnailObject ) {
            this.ThumbnailObject = thumbnailObject;
        }

        public void setResourceURI( String resourceURI ) {
            this.resourceURI = resourceURI;
        }

        public void setComics( Comics comicsObject ) {
            this.ComicsObject = comicsObject;
        }

        public void setSeries( Series seriesObject ) {
            this.SeriesObject = seriesObject;
        }

        public void setStories( Stories storiesObject ) {
            this.StoriesObject = storiesObject;
        }

        public void setEvents( Events eventsObject ) {
            this.EventsObject = eventsObject;
        }
    }

