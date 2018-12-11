package com.cqupt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.cqupt.entity.Node;

public class IOUtil {

	public void SerializeRoot(Node root) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\data\\kdTree.txt"));
		oos.writeObject(root);
		oos.close();
	}
	
	public Node DeSerializeRoot() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\data\\kdTree.txt"));
		Node root =(Node) ois.readObject();
		ois.close();
		return root;
	}

}
