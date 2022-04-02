package net.milkbowl.vault.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.URL;

public class IOUtil {

	/**
	 * Reads bytes from a URL <i>(given as a string)</i>.
	 *
	 */
	public static byte[] readRemoteData(String location) throws IOException {
		// Create URL and open stream. Read all bytes and return em.
		URL url = new URL(location);
		byte[] bytes;
		try (InputStream is = url.openStream()) {
			bytes = IOUtil.toByteArray(is);
		}
		return bytes;
	}

	/**
	 * Reads a stream to a byte array.
	 *
	 */
	public static byte[] toByteArray(InputStream is) throws IOException {
		byte[] bytes;
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			bytes = buffer.toByteArray();
		}
		return bytes;
	}

	/**
	 * Checks to see how a port is used.
	 *
	 * 		<ul>
	 *         <li>3 = TCP / UDP Used</li>
	 *         <li>2 = UDP Used</li>
	 *         <li>1 = TCP Used</li>
	 *         <li>0 = Unused</li>
	 *         </ul>
	 */
	public static int getPortUsage(int port) {
		boolean tcp = false;
		boolean udp = false;
		try (ServerSocket ss = new ServerSocket(port)) {
			ss.setReuseAddress(true);
			tcp = true;
		} catch (Exception ignored) {}

		try (DatagramSocket ds = new DatagramSocket(port)) {
			ds.setReuseAddress(true);
			udp = true;
		} catch (Exception ignored) {}
		if (tcp && udp)
			return 3;
		if (udp)
			return 2;
		if (tcp)
			return 1;
		return 0;
	}

	/**
	 * Copies the data from the given input stream to the output stream.
	 *
	 */
	public static void copyStream(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}

	/**
	 * Reads the text from the given input stream.
	 *
	 */
	public static String getText(InputStream stream, String charset) throws IOException {
		return new String(IOUtil.toByteArray(stream), charset);
	}

}