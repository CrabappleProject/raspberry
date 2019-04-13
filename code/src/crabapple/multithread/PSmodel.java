package crabapple.multithread;

/*
 * Copyright (c) This is zhaoxubin's Java program.
 * Copyright belongs to the crabapple organization.
 * The crabapple organization has all rights to this program.
 * No individual or organization can refer to or reproduce this program without permission.
 * If you need to reprint or quote, please post it to zhaoxubin2016@live.com.
 * You will get a reply within a week,
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PSmodel {

}


class Server implements Runnable {

    Restaurant restaurant;

    public Server(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

            while (restaurant.meal == null)
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("服务员拿到第" + restaurant.count + "份菜");
            if (restaurant.count == 10)
                restaurant.executorService.shutdownNow();
            synchronized (restaurant.chef) {
                restaurant.meal = null;
                restaurant.chef.notifyAll();
            }

        }


    }
}


class Chef implements Runnable {

    Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            while (restaurant.meal != null)
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("厨师做了第" + (++restaurant.count) + "份餐");
            restaurant.meal = new Meal();

            synchronized (restaurant.server) {
                restaurant.server.notifyAll();
            }
            if (restaurant.count == 10)
                restaurant.executorService.shutdownNow();

        }


    }
}

class Restaurant {
    int count = 0;
    Meal meal;
    Chef chef;
    Server server;
    ExecutorService executorService;

    public Restaurant() {

        this.chef = new Chef(this);
        this.server = new Server(this);
        this.executorService = Executors.newCachedThreadPool();
        executorService.execute(server);
        executorService.execute(chef);
    }
}

class Meal {

}