package com.company;
import java.util.*;

class Node {
    int item;
    char c;
    Node left;
    Node right;
}

class ImplementComparator implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.item - y.item;
    }
}

public class Main {
    public static String  finalString = "";
    public static  String table[] = new String[256];


    public static String decode(Node node, String c) {
        if (node.left == null && node.right == null  && node.c != '#') {
            table[((int) node.c)] = c;
            String temp = finalString += c;
            return temp;
        }
        decode(node.left, c + "0");
        decode(node.right, c + "1");
        return finalString;
    }

    public static void main(String[] args) {


        String str;
        int j;
        int counter[] = new int[256];

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        str = scanner.nextLine();
        for (j = 0; j < str.length(); j++)
        {
            counter[(int) str.charAt(j)]++;
        }

        ArrayList<Character> charArray =new ArrayList<Character>();
        ArrayList<Integer> charfreq =new ArrayList<Integer>();

        for (j = 0; j < 256; j++)
        {
            if(counter[j] != 0)
            {
                charArray.add((char)j);
                charfreq.add(counter[j]);
            }
        }

        PriorityQueue<Node> nodes = new PriorityQueue<Node>(str.length(), new ImplementComparator());

        for (int i = 0; i < charfreq.size(); i++) {
            Node hn = new Node();

            hn.c = charArray.get(i);
            hn.item = charfreq.get(i);

            hn.left = null;
            hn.right = null;

            nodes.add(hn);
        }
        int i = 0;
        Iterator it = nodes.iterator();
        while (it.hasNext()) {
            Node value = (Node) it.next();
            System.out.println(   value.c + " " + value.item);

        }


        Node root = null;

        while (nodes.size() > 1) {

            Node x = nodes.peek();
            nodes.poll();

            Node y = nodes.peek();
            nodes.poll();

            Node f = new Node();

            f.item = x.item + y.item;
            f.c = '#';
            f.left = x;
            f.right = y;
            root = f;
            nodes.add(f);
        }

        String res =  decode (nodes.peek(), "");


        for(i=0;i< 256;i++)
        {
            if(table[i]!=null)
                System.out.println((char)i+" "+table[i]);
        }


    }
}
