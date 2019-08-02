package org.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * @return 获取类加载器
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 
	 * @param className
	 * @param isInitialized
	 * @return 加载类
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className,isInitialized,getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("加载类失败",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	/**
	 * 获取指定包名下的所有类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>(); //创建类的集合
		try {
			//通过类加载器查找获取给定类名的类，并将.改为/，然后以url的形式返回集合
			Enumeration<URL> urls =  getClassLoader().getResources(packageName.replace(".","/"));
			/*
			 * 遍历返回的集合，如果不为空则判断该文件为file还是jar，
			 * file文件则将“20%”在中文环境下为空格，需要将他用空字符串代替
			 * 返回的字符串加入addClass中
			 */
			while(urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if(url!=null) {
					String protocol = url.getProtocol();
					if(protocol.equals("file")) {
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(classSet,packagePath,packageName);
						/*
						 * 如果是jar文件，需要打开连接去获取其中的file，
						 * 得到entry遍历file集合，获取每个jar文件的字符串名字，如果是以.class为结尾则表示为class文件，
						 * 调用doAddClass方法加入
						 * 
						 */
					}else if(protocol.equals("jar")) {
						JarURLConnection jarurlconnection = (JarURLConnection) url.openConnection();
						if(jarurlconnection!=null) {
							JarFile jarFile=  jarurlconnection.getJarFile();
							if(jarFile!=null) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while(jarEntries.hasMoreElements()) {
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName =  jarEntry.getName();
									if(jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
										doAddClass(classSet,className);
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("get class failure" , e );
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		/*
		 * 遍历包路径，过滤出以.class结尾的文件或目录
		 * 遍历file，如果是文件，拼出包名.类名加入类中。
		 */
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return (pathname.isFile()&&pathname.getName().endsWith(".class"))||pathname.isDirectory();
			}
		});
		for(File file :files) {
			String fileName =  file.getName();
			if(file.isFile()) {
				String className = fileName.substring(0,fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)) {
					className = packageName + "."+className;
				}
				doAddClass(classSet,className);
				/*
				 * 也是为了拼出包名.类名
				 */
			}else {
				String subPackagePath = fileName;
				if(StringUtil.isNotEmpty(packagePath)) {
					subPackagePath = packagePath+"/"+subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtil.isNotEmpty(packageName)) {
					subPackageName = packageName+"."+subPackageName;
				}
				addClass(classSet, packagePath, packageName);
			}
		}
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}


	
}
