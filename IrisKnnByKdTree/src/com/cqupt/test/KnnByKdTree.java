package com.cqupt.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqupt.entity.Node;
import com.cqupt.util.BinaryTreeOrder;
import com.cqupt.util.DataSetUtil;
import com.cqupt.util.IOUtil;

public class KnnByKdTree {

	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		DataSetUtil dataSetUtil = new DataSetUtil();
		List<Node> trainSets = new ArrayList<>();
		List<Node> testSets = new ArrayList<>();
		try {
			trainSets = dataSetUtil.getIris("D:\\data\\irisTrain.txt");
			testSets = dataSetUtil.getIris("D:\\data\\irisTest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		KnnByKdTree kdTree = new KnnByKdTree();
		Node root = kdTree.buildKdTree(trainSets, 0);
//		IOUtil io = new IOUtil();
//		Node root=null;
//		try {
////			io.SerializeRoot(root);//序列化，将树存储与硬盘中
//				root=io.DeSerializeRoot();//反序列化，将树从硬盘中读取
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// new BinaryTreeOrder().preOrder(root);//前序遍历
		double error=0.0;
		for (Node testNode : testSets) {
			List<Node> knnList = kdTree.searchKnn(root, testNode, 3);
//			for (Node node : knnList) {
//				System.out.println(node.toString());
//			}
			String type = kdTree.getMaxType(knnList);
			System.out.println("将点("+testNode.getData(0)+","+testNode.getData(1)
				+","+testNode.getData(2)+","+testNode.getData(3)+")预测为：" + type+",实际为："+testNode.type);
			if(!type.equals(testNode.type)){
				error++;
			}
		}
		System.out.println("错误率为："+(error/testSets.size()));
		long endTime = System.currentTimeMillis();
		System.out.println("共用时"+(endTime-startTime)+"ms");

	}

	public String getMaxType(List<Node> knnList) {
		Map<String, Integer> type = new HashMap<String, Integer>();
		for (Node node : knnList) {
			if (type.get(node.type) == null) {
				type.put(node.type, 1);
			} else {
				type.put(node.type, type.get(node.type) + 1);
			}
		}
		String maxType = null;
		int maxNum = 0;
		for (Map.Entry<String, Integer> entry : type.entrySet()) {
			if (entry.getValue() > maxNum) {
				maxNum = entry.getValue();
				maxType = entry.getKey();
			}
		}
		return maxType;
	}

	/**
	 * 构建KD树
	 * 
	 * @param nodeList
	 * @param index
	 * @return
	 */
	public Node buildKdTree(List<Node> nodeList, int index) {
		if (nodeList == null || nodeList.size() == 0) {
			return null;
		}
		quickSortForMedian(nodeList, index, 0, nodeList.size() - 1);
		Node root = nodeList.get(nodeList.size() / 2);
		root.dim = index;
		List<Node> leftNode = new ArrayList<Node>();
		List<Node> rightNode = new ArrayList<Node>();
		for (Node node : nodeList) {
			if (node != root) {
				if (node.getData(index) <= root.getData(index)) {
					leftNode.add(node);
				} else {
					rightNode.add(node);
				}
			}
		}
		int newIndex = (index + 1) % root.data.length;
		root.left = buildKdTree(leftNode, newIndex);
		root.right = buildKdTree(rightNode, newIndex);
		if (root.left != null) {
			root.left.parent = root;
		}
		if (root.right != null) {
			root.right.parent = root;
		}
		return root;
	}

	/**
	 * 快速排序
	 * 
	 * @param nodeList
	 * @param index
	 * @param left
	 * @param right
	 */
	private void quickSortForMedian(List<Node> nodeList, int index, int left, int right) {
		if (left >= right || nodeList.size() <= 0) {
			return;
		}
		Node kn = nodeList.get(left);
		double k = kn.getData(index);
		int i = left, j = right;
		while (i < j) {
			while (nodeList.get(j).getData(index) >= k && i < j)
				j--;
			nodeList.set(i, nodeList.get(j));
			while (nodeList.get(i).getData(index) <= k && i < j)
				i++;
			nodeList.set(j, nodeList.get(i));
		}
		nodeList.set(i, kn);

		// 只排序至找到中位数
		if (i == nodeList.size() / 2) {
			return;
		} else if (i < nodeList.size() / 2) {
			quickSortForMedian(nodeList, index, i + 1, right);
		} else if (i > nodeList.size() / 2) {
			quickSortForMedian(nodeList, index, left, j - 1);
		}

		// 全部进行排序
		// if(i>left){
		// quickSortForMedian(nodeList,index,left,j-1);
		// }
		// if(j<right){
		// quickSortForMedian(nodeList,index,i+1,right);
		// }
	}

	/**
	 * 查询与测试点最近的K个点
	 * 
	 * @param root
	 * @param q
	 * @param k
	 * @return
	 */
	public List<Node> searchKnn(Node root, Node q, int k) {
		List<Node> knnList = new ArrayList<Node>();
		searchNearest(knnList, root, q, k);
		return knnList;
	}

	/**
	 * 搜寻最近的点，将其存入knnList
	 * 
	 * @param knnList
	 * @param root
	 * @param q
	 * @param k
	 */
	public void searchNearest(List<Node> knnList, Node root, Node q, int k) {
		Node leaf = searchLeafNode(root, q);
		double curD = q.computeDistance(leaf);
		leaf.distance = curD;
		maintainMaxHeap(knnList, leaf, k);
		while (leaf != root) {
			if (getBrother(leaf) != null) {
				Node brother = getBrother(leaf);
				if (curD > Math.abs(q.getData(leaf.parent.dim) - leaf.getData(leaf.parent.dim)) || knnList.size() < k) {
					searchNearest(knnList, brother, q, k);
				}
			}
			leaf = leaf.parent;
			double rootD = q.computeDistance(leaf);
			leaf.distance = rootD;
			maintainMaxHeap(knnList, leaf, k);
		}
	}

	/**
	 * 返回兄弟节点
	 * 
	 * @param node
	 * @return
	 */
	public Node getBrother(Node node) {
		if (node == node.parent.left) {
			return node.parent.right;
		} else {
			return node.parent.left;
		}
	}

	/**
	 * 根据测试点搜寻叶节点
	 * 
	 * @param root
	 * @param q
	 * @return
	 */
	public Node searchLeafNode(Node root, Node q) {
		Node leaf = root, next;
		int index = 0;
		while (leaf.left != null || leaf.right != null) {
			if (q.getData(index) < leaf.getData(index)) {
				next = leaf.left;
			} else if (q.getData(index) > leaf.getData(index)) {
				next = leaf.right;
			} else {
				if (leaf.left != null && leaf.right != null) {
					if (q.computeDistance(leaf.left) <= q.computeDistance(leaf.right)) {
						next = leaf.left;
					} else {
						next = leaf.right;
					}
				} else if (leaf.left == null) {
					next = leaf.right;
				} else {
					next = leaf.left;
				}

			}
			if (next != null) {
				leaf = next;
				index = (index + 1) % root.data.length;
			} else {
				break;
			}
		}
		return leaf;
	}

	/**
	 * 维持大顶堆
	 * 
	 * @param knnList
	 * @param leafNode
	 * @param k
	 */
	public void maintainMaxHeap(List<Node> knnList, Node leafNode, int k) {
		if (knnList.size() < k) {
			maxHeapFixUp(knnList, leafNode);// 不足k个堆 直接向上修复
		} else if (leafNode.distance < knnList.get(0).distance) {
			// 比对顶小 还需向下修复 覆盖对顶
			maxHeapFixDown(knnList, leafNode);
		}
	}

	/**
	 * 从下往上修复
	 * 
	 * @param knnList
	 * @param leafNode
	 */
	private void maxHeapFixUp(List<Node> knnList, Node leafNode) {
		knnList.add(leafNode);
		int i = knnList.size() - 1;
		int j = (i + 1) / 2 - 1;// j是i的父节点
		while (j >= 0) {
			if (knnList.get(i).distance <= knnList.get(j).distance) {
				break;
			}
			Node t = knnList.get(i);
			knnList.set(i, knnList.get(j));
			knnList.set(j, t);
			i = j;
			j = (i + 1) / 2 - 1;
		}

	}

	/**
	 * 向下修复
	 * 
	 * @param knnList
	 * @param leaf
	 */
	private void maxHeapFixDown(List<Node> knnList, Node leaf) {
		knnList.set(0, leaf);
		int i = 0;
		int j = 2 * i + 1;
		while (j < knnList.size()) {
			if (j + 1 < knnList.size() && knnList.get(j).distance < knnList.get(j + 1).distance) {
				j++;
			}
			if (knnList.get(j).distance < knnList.get(i).distance) {
				break;
			}
			Node t = knnList.get(i);
			knnList.set(i, knnList.get(j));
			knnList.set(j, t);
			i = j;
			j = 2 * i + 1;
		}
	}
}
