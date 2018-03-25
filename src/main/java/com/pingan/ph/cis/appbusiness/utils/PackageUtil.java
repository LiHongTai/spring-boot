package com.pingan.ph.cis.appbusiness.utils;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.pingan.ph.cis.appbusiness.constant.CisConstant;


public class PackageUtil {

	public PackageUtil() {

	}

	public static List<String> getClasses(String packageName) {
		List<String> classes = new ArrayList<String>();

		boolean recursive = true;
		String packageDirName = packageName.replace('.', '/');
		try {
			Enumeration<?> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			
			URL url;
			String protocol;
			do {
				if (!dirs.hasMoreElements()) {
					return classes;
				}

				url = (URL) dirs.nextElement();
				protocol = url.getProtocol();

				if (CisConstant.FILE.equals(protocol)) {
					String packagePath = URLDecoder.decode(url.getFile(), CisConstant.UTF8);
					classes = findClassPaths(packageName, packagePath, recursive);
					return classes;
				}

			} while (!CisConstant.JAR.equals(protocol));

		} catch (Exception e) {
			//TODO
		}
		return classes;
	}

	private static List<String> findClassPaths(String packageName, String packagePath, boolean recursive) {
		List<String> results = new ArrayList<String>();

		File dir = new File(packagePath);
		if (dir.exists() && dir.isDirectory()) {

			File[] dirfiles = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File arg0) {
					return recursive && arg0.isDirectory() || arg0.getName().endsWith(".class");
				}
			});

			for (File file : dirfiles) {
				if (file.isDirectory()) {
					List<String> classPaths = findClassPaths(packageName + CisConstant.SEPERATOR_DOT + file.getName(),
							file.getAbsolutePath(), recursive);
					results.addAll(classPaths);
					continue;
				}
				int end = file.getName().length() - 6;
				String className = file.getName().substring(0, end);
				results.add(packageName + CisConstant.SEPERATOR_DOT + className);
			}
		}

		return results;
	}
}
