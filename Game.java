package com.tutorial.main;

public class Game extends Canvas implements Runnable{

//need serialversion thing


public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;

private Thread thread;
private boolean running = false;



public Game(){
    new Windo w(WIDTH, HEIGHT, "Lets build a game", this);
}
    public synchronized void start(){
        thread = new Thread (this);
        thread.start ();
        running = true;

    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();

        }

    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;

            }

            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;

            }

        }
        stop();
    }

    private void tick(){

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics  g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.dispose();
        bs.show();

    }
    public static void main(String args[]){
            new Game();

    }
}

// public means the method is visible and can be called from other objects of other types. alternatives are private, protected, package and package-private.

// static means the method is associated with teh class, not a specific instance (object)of the class. this means you can call a static method without creating an object of the class.

//void means the method has no return valie. if the method returned an int you would write int instead of void.

//you see the combo of all three of these most commonly on the main method.
