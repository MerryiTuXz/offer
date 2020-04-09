package leetcode;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        List<Integer> list = null;
        for (int num : list) {
            System.out.println(num);
        }
    }
}

class MedianFinder {

    private PriorityQueue<Integer> minHeap, maxHeap;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        // 最小堆存储大数
        minHeap = new PriorityQueue<Integer>(
                Comparator.comparingInt(o -> o)
        );
        // 最大堆存储小数
        maxHeap = new PriorityQueue<Integer>(
                (o1, o2) -> o2 - o1
        );
    }

    public void addNum(int num) {
        // 总数目为偶数时插入最小堆
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.add(num);
                minHeap.add(maxHeap.poll());
            } else {
                minHeap.add(num);
            }
        } else {  // 总数目为奇数时插入最大堆
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
            } else {
                minHeap.add(num);
            }
        }
    }

    public double findMedian() {
        int sz = maxHeap.size() + minHeap.size();
        if (sz % 2 == 1) {
            return (double) minHeap.peek();
        } else {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        }
    }
}
