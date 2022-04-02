package net.milkbowl.vault.util;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtil {

	public static File getServerJar() {
		File cur = FileUtil.getWorkingDirectory();
		while (cur.getAbsolutePath().contains("plugins")) {
			cur = FileUtil.getParent(cur);
		}
		for (File f : Objects.requireNonNull(cur.listFiles())) {
			if (f.getName().toLowerCase().endsWith(".jar")) {
				if (getText(f, "configurations/bukkit.yml") != null) {
					return f;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the file of the plugin by the given plugin name. The plugin name
	 * is what shows up in the "plugin.yml" file. Its also the name given by
	 * bukkit's "/pl" command
	 * 
	 * @param pluginName
	 *            Name of the plugin to find.
	 * @return File of plugin, null if no plugin could be found.
	 */
	public static File getPluginJar(String pluginName) {
		File file = FileUtil.getWorkingDirectory();
		if (!file.getAbsolutePath().contains("plugins")) {
			for (File f : Objects.requireNonNull(file.listFiles())) {
				if (f.isDirectory() && f.getName().endsWith("plugins")) {
					file = f;
				}
			}
		}
		for (File plugin : Objects.requireNonNull(file.listFiles())) {
			if (plugin.isFile() && plugin.getName().endsWith(".jar")) {
				String content = getText(plugin, "plugin.yml");
				if (content != null && content.contains(pluginName)) {
					return plugin;
				}
			}
		}
		return null;
	}

	/**
	 * Return the text content of the given file in the given archive.
	 *
	 */
	public static String getText(File archive, String fileNameInArchive) {
		// Open given file as a zip (jars are basically just zip files)
		try (ZipFile zipFile = new ZipFile(archive.getAbsolutePath())) {
			// Iterate entries until the entry with the given name is detected
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().equals(fileNameInArchive)) {
					// Once the correct file has been found, read the contents
					// and return them.
					return IOUtil.getText(zipFile.getInputStream(entry), "UTF-8");
				}
			}
		} catch (Exception ignored) {}
		return null;
	}

	/**
	 * Return the raw content of the given file in the given archive.
	 *
	 */
	public static byte[] getRaw(File archive, String fileNameInArchive) {
		try (ZipFile zipFile = new ZipFile(archive.getAbsolutePath())) {
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().equals(fileNameInArchive)) {
					return IOUtil.toByteArray(zipFile.getInputStream(entry));
				}
			}
		} catch (Exception ignored) {}
		return null;
	}

	/**
	 * Checks if a jar has a file of the given name inside of it.
	 *
	 *             Could not read from jar.
	 */
	public static boolean doesJarContainFile(File jar, String file) throws IOException {
		try (JarInputStream jarFile = new JarInputStream(new FileInputStream(jar))) {
			JarEntry jarEntry;
			while ((jarEntry = jarFile.getNextJarEntry()) != null) {
				if (jarEntry.getName().equals(file)) {
					jarFile.close();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the given file is locked by another process.
	 *
	 */
	public static boolean isFileLockedByProcess(File file) {
		try {
			return !file.renameTo(file);
		} catch (Exception e) {
			return true;
		}
	}

// Method had error to lazy to fix
//=======================================================================================================================
//	/**
//	 * Replaces a given file name in the server jar with that from the download
//	 * url.
//	 *
//	 * @param originalClassFileName
//	 *            File name. Example: <i>org/bukkit/craftbukkit/Main.class"</i>
//	 * @param replaceClassURL
//	 *            URL that points to a class file bytes.
//	 * @return
//	 * 		<ul>
//	 *         <li>0 = Success</li>
//	 *         <li>-1 = Could not find server jar</li>
//	 *         <li>-2 = Server does not have file to replace</li>
//	 *         <li>-3 = Could not read from URL</li>
//	 *         <li>-4 = Could not replace in jar</li>
//	 *         </ul>
//	 */
//	public static int replaceClassInServerJar(String originalClassFileName, String replaceClassURL) throws IOException {
//		// Get server jar, check if valid
//		File serverJar = getServerJar();
//		if (serverJar == null) {
//			return -1;
//		}
//
//		// Check replace
//		if (!doesJarContainFile(serverJar, originalClassFileName)) {
//			return -2;
//		}
//
//		// Download class to replace the main class.
//		byte[] classData = IOUtil.readRemoteData(replaceClassURL);
//		if (classData == null) {
//			return -3;
//		}
//
//		// Replace class inside jar
//
//		// TODO: Counter measure for when the server jar is in use
//		// Likely will have to move this to a separate process
//		// Have this class and Util merged into a single class, download it from
//		// the plguin
//		// then java execute the new program. It'll quit when the server
//		// restarts and it has a change to replace the file.
//		Path jarPath = Paths.get(serverJar.getAbsolutePath());
//		try (FileSystem fs = FileSystems.newFileSystem(jarPath, null)) {
//			Path internalToReplace = fs.getPath(originalClassFileName);
//			Files.copy(new ByteArrayInputStream(classData), internalToReplace, StandardCopyOption.REPLACE_EXISTING);
//			return 0;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return -4;
//		}
//
//	}
//=======================================================================================================================



	/**
	 * Writes data to a file.
	 *
	 */
	public static void write(byte[] data, File file) throws IOException {
		try (FileOutputStream fw = new FileOutputStream(file)) {
			fw.write(data);
		}
	}

	/**
	 * Returns the contents of a file as a list<string>
	 * 
	 * @param f
	 *            the file.
	 * @return
	 */
	public static List<String> readFileContents(File f) {
		try {
			List<String> content = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				String line;
				while ((line = br.readLine()) != null) {
					content.add(line);
				}
			}
			return content;
		} catch (Exception e) {
			return Arrays.asList("Could not read from file '" + f.getName() + "'", "Reason: " + e.getMessage());
		}
	}

	/**
	 * Return the parent for the given file.
	 *
	 */
	public static File getParent(File fi) {
		String f = fi.getAbsolutePath();
		int i = f.lastIndexOf(File.separator);
		if (i < 0) {
			return new File(f);
		}
		String path = f.substring(0, i);
		// Windows loop prevention
		if (path.endsWith(":")) {
			path += File.separator;
		}
		// I don't know if this is needed on linux, I'll have to check
		if (path.length() == 0) {
			return new File(File.separator);
		}
		return new File(path);
	}

	/**
	 * Returns the current file directory.
	 *
	 */
	public static File getWorkingDirectory() {
		return new File(System.getProperty("user.dir"));
	}

	public static void writeByteArrayToFile(File file, byte[] data) {
		// TODO Auto-generated method stub
		
	}

}