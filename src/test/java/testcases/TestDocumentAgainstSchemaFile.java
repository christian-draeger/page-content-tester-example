package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jsoup.helper.W3CDom;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import pagecontenttester.fetcher.Page;

public class TestDocumentAgainstSchemaFile {

    private Validator validator = null;

    public TestDocumentAgainstSchemaFile(final String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaUrl;
            if (xsdPath.startsWith("http")) {
                schemaUrl = new URL(xsdPath);
            } else {
                schemaUrl = getClass().getResource(xsdPath);
            }
            Schema schema = factory.newSchema(schemaUrl);
            validator = schema.newValidator();
        } catch (MalformedURLException | SAXException e) {
            throw new AssertionError("schema file is invalid: " + e.getMessage());
        }
    }

    public void check(Page fetchedPage) throws IOException, SAXException {
        if (validator != null) {
            W3CDom w3cDom = new W3CDom();
            org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(fetchedPage.getDocument());
            MyErrorHandler errorHandler = new MyErrorHandler();
            validator.setErrorHandler(errorHandler);
            validator.validate(new DOMSource(w3cDoc));
            if (errorHandler.hasErrors()) {
                throw new AssertionError("fetched page is invalid, list of errors:\n\t" + errorHandler.getErrorMessageOutput());
            }
        }
    }

    static class MyErrorHandler implements ErrorHandler {

        private List<String> errorMessages = new ArrayList<>();

        @Override
        public void fatalError( final SAXParseException e )
                throws SAXException {
            throw e;
        }

        @Override
        public void error( final SAXParseException e ) throws SAXException {
            errorMessages.add(e.getLocalizedMessage());
        }

        @Override
        public void warning( final SAXParseException e ) throws SAXException {
            //ignore warnings for now
        }

        public boolean hasErrors() {
            return !errorMessages.isEmpty();
        }

        public String getErrorMessageOutput() {
            return String.join("\n\t", errorMessages);
        }
    }

}
