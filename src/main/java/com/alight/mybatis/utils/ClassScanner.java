package com.alight.mybatis.utils;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ClassScanner {

    public static Set<Class<?>> scanPackage(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        String packagePath = packageName.replace('.', '/');
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if ("file".equals(url.getProtocol())) {
                    URI uri = url.toURI();
                    scanDirectory(new File(uri), packageName, classSet);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("扫描包路径失败：" + packageName, e);
        }
        return classSet;
    }

    private static void scanDirectory(File dir, String packageName, Set<Class<?>> classSet) throws ClassNotFoundException {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), classSet);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - ".class".length());
                classSet.add(Class.forName(className));
            }
        }
    }
}
