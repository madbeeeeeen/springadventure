package com.madbeen.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link java.util.Observer} 示例
 *
 * @author: madbeen
 * @date: 2022/03/17/10:22 PM
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Observable observable = new EventObservable();
        // 添加 监听者/观察者
        observable.addObserver(new EventObserver());
        // 发布 消息/事件
        observable.notifyObservers("Hello World");
    }

    static class EventObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {
        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件: " + eventObject);
        }
    }
}
