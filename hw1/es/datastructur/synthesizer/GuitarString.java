package es.datastructur.synthesizer;

import edu.princeton.cs.algs4.StdAudio;

import java.util.Iterator;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString<T> extends ArrayRingBuffer<T> implements BoundedQueue<T>{
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        super((int) Math.round(SR / frequency));
        int capacity = (int) Math.round(SR / frequency);
        this.buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < buffer.capacity(); i++){
            buffer.enqueue(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i = 0; i < buffer.fillCount(); i++) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double play = buffer.dequeue();
        double newDouble = DECAY  * 0.5 * (play + buffer.peek());
        buffer.enqueue(newDouble);
        // StdAudio.play(play);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}

