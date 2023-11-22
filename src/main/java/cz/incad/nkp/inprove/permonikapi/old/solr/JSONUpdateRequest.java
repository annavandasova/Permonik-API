/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.incad.nkp.inprove.permonikapi.old.solr;

import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.common.util.ContentStreamBase;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;

/**
 * A convenience class for streaming JSON to Apache Solr's custom JSON Update Handler.
 *
 * @author bbende
 */
public class JSONUpdateRequest extends AbstractUpdateRequest {

    private final InputStream jsonInputStream;



    /**
     * Construct a new update request for the given InputStream.
     *
     * @param json
   * @throws UnsupportedEncodingException
     */
    public JSONUpdateRequest(JSONObject json) throws UnsupportedEncodingException {
      super(METHOD.POST, "/update/json/docs");
        byte[] jsonBytes = json.toString().getBytes(StandardCharsets.UTF_8);
        this.jsonInputStream = new ByteArrayInputStream(jsonBytes);
        this.setParam("json.command", "false");
    }

    /**
     * Sets the split param.
     *
     * @param jsonPath
     */
    public void setSplit(String jsonPath) {
        setParam("split", jsonPath);
    }

    @Override
    public Collection<ContentStream> getContentStreams() throws IOException {
        ContentStream jsonContentStream = new InputStreamContentStream(
                jsonInputStream, "application/json");
        return Collections.singletonList(jsonContentStream);
    }

    /**
     * A ContentStream for wrapping an InputStream.
     */
    private class InputStreamContentStream extends ContentStreamBase {

        private final InputStream inputStream;

        public InputStreamContentStream(InputStream inputStream, String contentType) {
            this.inputStream = inputStream;
            this.setContentType(contentType);
        }

        @Override
        public InputStream getStream() throws IOException {
            return inputStream;
        }
    }

}