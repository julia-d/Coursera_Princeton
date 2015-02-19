package w2collections;



import edu.princeton.cs.introcs.StdIn;

public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        for (int i = 0; i < k; i++) {
            String s = StdIn.readString();
            rq.enqueue(s);

        }

        for (Object s : rq) {
            System.out.println((String) s);
        }

    }
    

}
