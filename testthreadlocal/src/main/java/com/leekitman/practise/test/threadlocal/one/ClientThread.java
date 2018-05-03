package com.leekitman.practise.test.threadlocal.one;

/**
 * @author LeeKITMAN
 * @see 2018/5/3 17:09
 */
public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
        }
    }
}
