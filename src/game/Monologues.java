package game;


import java.util.ArrayList;
import java.util.Random;

/**
 *  a class that represents the monologue and will store the monologues that Toad can execute
 *  can store its own condition
 *  String monologue
 *  and condition boolean
 *
 * @author: Ali Maskari
 * @version: 2.0
 */

public class Monologues {

    /**
     * an arrayList of type String that will resemble the messages
     */

    private ArrayList<String> messages = new ArrayList<>();

    /**
     * a function that pushes a certain monologue of the wanted actor to perform the speaking action
     * @param index Monologue position  of type int
     * @param monologue the actual monologue of type String that will be given a possition int
     */

    public void pushMonologue(int index, String monologue){
        messages.add(index, monologue);
    }

    /**
     *
     * generate a random monologue that has an upper bond of the message size
     * @return a random message/monologue
     */

    public String getMonologue(){

        Random random = new Random();
        int upperBound = messages.size();
        int rand_num = random.nextInt(upperBound);
        return messages.get(rand_num);

    }

    /**
     * this method is only used when an actor has a condition on the monologue he wants to use
     * like a constraint on their monologue
     * @param index
     */

    public void removeMonologue(int index) {
        messages.remove(index);
    }

    /**
     * a method that was used to the monologue message
     */

    public void clearMonologue() {
        messages.clear();
    }


}
