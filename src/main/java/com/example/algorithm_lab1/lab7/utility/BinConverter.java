package com.example.algorithm_lab1.lab7.utility;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab7.modelComponents.ModelSequential;

import java.io.*;
import java.nio.file.Path;

public class BinConverter implements Serializable {

	public void convertModel(ModelSequential modelSequential) throws Exception {
		String path = new File(HelloApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		path = path.substring(0, path.length() - 19) + "/saved.txt";
		FileOutputStream outputStream = new FileOutputStream(path);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(modelSequential);
		objectOutputStream.close();
	}

	public ModelSequential unConvertFile(String path) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(path);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		return (ModelSequential) objectInputStream.readObject();
	}


}