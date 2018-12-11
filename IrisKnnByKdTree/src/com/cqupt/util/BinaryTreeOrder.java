package com.cqupt.util;

import com.cqupt.entity.Node;

public class BinaryTreeOrder {

	public void preOrder(Node root){
		if(root!=null){
			System.out.println(root.toString());
			preOrder(root.left);
			preOrder(root.right);
		}
	}

}
