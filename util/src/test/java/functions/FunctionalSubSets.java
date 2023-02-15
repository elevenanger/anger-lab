package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * author : anger
 * date : 2022/7/14 14:09
 * description : 求集合的子集，比较函数式和命令式思想之间的区别
 */
public class FunctionalSubSets {

    static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subAns = subsets(rest);
        List<List<Integer>> subAns2 = insertAll(first, subAns);
        return concat(subAns, subAns2);
    }

    static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subLists) {
        List<List<Integer>> results = new ArrayList<>();
        for (List<Integer> subList : subLists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(subList);
            results .add(copyList);
        }
        return results;
    }

    static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0 ,1, 2, 3, 4, 5);
        System.out.println(subsets(list));
    }
}
