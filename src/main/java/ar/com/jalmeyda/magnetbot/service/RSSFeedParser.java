package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.domain.FeedItem;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
public class RSSFeedParser {

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String PUB_DATE = "pubDate";
    private static final String ITEM = "item";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<FeedItem> readFeed() {
        List<FeedItem> feedItems = new ArrayList<>();
        try {
            String title = "";
            String description = "";
            String link = "";
            String pubdate = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getComplexField(event, eventReader);
                            break;
                        case LINK:
                            link = getComplexField(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedItem message = new FeedItem();
                        message.setTitle(title);
                        message.setDescription(description);
                        message.setLink(link);
                        message.setPubDate(pubdate);
                        feedItems.add(message);
                        eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feedItems;
    }

    private String getComplexField(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String content = "";
        event = eventReader.nextEvent();
        while (event instanceof Characters) {
            content += event.asCharacters().getData();
            event = eventReader.nextEvent();
        }
        return content;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
