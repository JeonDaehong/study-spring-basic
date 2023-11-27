package com.example.studyspring.singleton;

public class SingletonService {
    private SingletonService() {}

    // static 내부 클래스를 이용
    // Holder 로 만들어, 클래스가 메모리에 로드되지 않고 getInstance 메서드가 호출되어야 로드됨
    private static class SingleInstanceHolder {
        private static final SingletonService INSTANCE = new SingletonService();
    }

    public static SingletonService getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public void logic() {
        System.out.println("싱슬턴 객체 로직을 호출했다.");
    }
}
