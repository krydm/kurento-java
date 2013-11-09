package com.kurento.kmf.repository;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface RepositoryItem {

	enum State {
		NEW, STORING, STORED
	}

	String getId();

	Map<String, String> getMetadata();

	void setMetadata(Map<String, String> metadata);

	void putMetadataEntry(String key, String value);

	/**
	 * Returns the state of the RepositoryItem.
	 * 
	 * @return NEW when the item has been just created and has no binary content
	 *         yet. CONTENT when the item has binary content and can be read.
	 */
	State getState();

	/**
	 * Creates an InputStream to read for the contents of the file. This
	 * operation is only valid when the item is in STORED state.
	 * 
	 * @return
	 */
	InputStream createInputStreamToRead();

	/**
	 * Creates an OutputStream to write the binary content of the file. This
	 * operation is only valid when the item is in NEW state and change the
	 * item's state to STORING. When the {@link OutputStream#close()} method is
	 * invoked, the item's state is changed to STORED. This method can be called
	 * only once because only one {@link OutputStream} can be created.
	 * 
	 * @return
	 */
	OutputStream createOutputStreamToWrite();

	/**
	 * Returns the {@link RepositoryHttpPlayer} to download the contents of the
	 * item using http protocol. This operation is only valid when the item is
	 * in STORED state.
	 * 
	 * @return
	 */
	RepositoryHttpPlayer createRepositoryHttpPlayer();

	/**
	 * Returns the {@link RepositoryHttpRecorder} to upload the contents of the
	 * item using http protocol. This operation is only valid when the item is
	 * in NEW state. When the element is used using the provided URL, the state
	 * of the item is changed to STORING.
	 * 
	 * @return
	 */
	RepositoryHttpRecorder createRepositoryHttpRecorder();

	/**
	 * Returns the {@link RepositoryHttpPlayer} to download the contents of the
	 * item using http protocol. This operation is only valid when the item is
	 * in STORED state. The parameter {@code sessionIdInURL} allows to specify
	 * the sessionId of this player used to construct the URL. The complete URL
	 * of the player can be obtained using the
	 * {@link RepositoryHttpPlayer#getURL()} in the returned object.
	 * 
	 * @param sessionIdInURL
	 *            The sessionId of this player used to construct the URL.
	 * @return
	 */
	RepositoryHttpPlayer createRepositoryHttpPlayer(String sessionIdInURL);

	/**
	 * Returns the {@link RepositoryHttpRecorder} to upload the contents of the
	 * item using http protocol. This operation is only valid when the item is
	 * in NEW state. When the element is used using the provided URL, the state
	 * of the item is changed to STORING. The parameter {@code sessionIdInURL}
	 * allows to specify the sessionId of this recorder used to construct the
	 * URL. The complete URL of the recorder can be obtained using the
	 * {@link RepositoryHttpRecorder#getURL()} in the returned object.
	 * 
	 * @param sessionIdInURL
	 *            The sessionId of this player used to construct the URL.
	 * @return
	 */
	RepositoryHttpRecorder createRepositoryHttpRecorder(String sessionIdInURL);

	/**
	 * Returns the attributes associated with this {@link RepositoryItem}. This
	 * attributes are used mainly when serving this item by means of http
	 * endpoint.
	 * 
	 * @return
	 */
	RepositoryItemAttributes getAttributes();

}