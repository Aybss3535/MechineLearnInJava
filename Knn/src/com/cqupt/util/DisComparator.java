package com.cqupt.util;

import java.util.Collections;
import java.util.Comparator;

import com.cqupt.entity.Distance;
/**
 * 距离比较器，从小到大排序
 *<p>title:DisComparator</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  上午11:21:05
 */
public class DisComparator implements Comparator<Distance> {

	@Override
	public int compare(Distance o1, Distance o2) {
		return o1.getDistance()>o2.getDistance()?1:-1;
	}

}
