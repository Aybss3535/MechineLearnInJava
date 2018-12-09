package com.cqupt.util;

import java.util.Collections;
import java.util.Comparator;

import com.cqupt.entity.Distance;
/**
 * ����Ƚ�������С��������
 *<p>title:DisComparator</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  ����11:21:05
 */
public class DisComparator implements Comparator<Distance> {

	@Override
	public int compare(Distance o1, Distance o2) {
		return o1.getDistance()>o2.getDistance()?1:-1;
	}

}
